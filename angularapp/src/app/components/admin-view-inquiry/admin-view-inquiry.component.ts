import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { PropertyInquiry } from 'src/app/models/property-inquiry.model';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-view-inquiry',
  templateUrl: './admin-view-inquiry.component.html',
  styleUrls: ['./admin-view-inquiry.component.css']
})
export class AdminViewInquiryComponent implements OnInit {

  /*
    Stores all inquiries retrieved from the service.
    Also maintains a filtered list of inquiries based on user-selected filters.
  */
  inquiries: PropertyInquiry[] = [];
  filteredInquiries: PropertyInquiry[] = [];

  /*
    Form group used to manage the admin's response to an inquiry.
  */
  responseForm: FormGroup;

  /*
    Stores the currently selected inquiry for response.
    Initially set to null until an inquiry is selected.
  */
  selectedInquiry: PropertyInquiry | null = null;

  /*
    Available priority levels and statuses used for filtering inquiries.
  */
  priorities = ['All', 'Low', 'Medium', 'High'];
  statuses = ['All', 'Pending', 'Resolved'];

  /*
    Stores filter selections for priority and status.
    Also includes a search field for dynamically filtering inquiries.
  */
  filterPriority = 'All';
  filterStatus = 'All';
  searchInquiry = '';

  /*
    Constructor injects necessary services:
    - `PropertyInquiryService` for fetching, updating, and deleting inquiries.
    - `FormBuilder` to manage inquiry response form creation.
  */
  constructor(
    private readonly inquiryService: PropertyInquiryService,
    private readonly fb: FormBuilder
  ) {
    this.responseForm = this.fb.group({
      adminResponse: [''],
      contactDetails: ['']
    });
  }

  /*
    Lifecycle hook executed when the component initializes.
    Loads inquiries for display.
  */
  ngOnInit(): void {
    this.loadInquiries();
  }

  /*
    Fetches all inquiries from the service.
    Updates both `inquiries` and `filteredInquiries` upon successful retrieval.
  */
  loadInquiries(): void {
    this.inquiryService.getAllPropertyInquiry().subscribe(
      (data) => {
        this.inquiries = data;
        this.applyFilters(); // Apply filters to update the view
      },
      (error) => {
        console.error('Error fetching inquiries:', error);
        this.inquiries = [];
        this.filteredInquiries = [];
      }
    );
  }

  /*
    Applies filters to the inquiries based on priority, status, and search field.
  */
  applyFilters(): void {
    this.filteredInquiries = this.inquiries.filter(inquiry =>
      (this.filterPriority === 'All' || inquiry.priority === this.filterPriority) &&
      (this.filterStatus === 'All' || inquiry.status === this.filterStatus) &&
      inquiry.property.title.toLowerCase().includes(this.searchInquiry.toLowerCase())
    );
  }

  /*
    Opens the inquiry response modal for the selected inquiry.
    Resets the response form before displaying the modal.
  */
  openResponseModal(inquiry: PropertyInquiry): void {
    this.selectedInquiry = inquiry;
    this.responseForm.reset();
  }

  /*
    Submits the admin response to the selected inquiry.
    Updates the inquiry status to "Resolved" and refreshes the inquiry list.
  */
  submitResponse(): void {
    if (this.selectedInquiry) {
      const updatedInquiry: PropertyInquiry = {
        ...this.selectedInquiry,
        ...this.responseForm.value,
        status: 'Resolved',
        responseDate: new Date()
      };
  
      this.inquiryService.editPropertyInquiryById(updatedInquiry.inquiryId, updatedInquiry).subscribe(() => {
        this.selectedInquiry = null;
        this.loadInquiries(); // Refresh the inquiry list
      });
    }
  }

  /*
    Deletes an inquiry by its ID.
    Prompts for confirmation before proceeding with deletion.
    Refreshes the list upon successful deletion.
  */
    deleteInquiry(id: number): void {
      Swal.fire({
        title: 'Are you sure?',
        text: 'This inquiry will be permanently deleted!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#dc3545', // Red for delete confirmation
        cancelButtonColor: '#6c757d', // Gray for cancel
        confirmButtonText: 'Yes, Delete!',
        cancelButtonText: 'No, Keep it'
      }).then((result) => {
        if (result.isConfirmed) {
          this.inquiryService.deletePropertyInquiryById(id).subscribe(() => {
            Swal.fire({
              title: 'Deleted!',
              text: 'The inquiry has been removed.',
              icon: 'success',
              confirmButtonColor: '#ffcc00' // Yellow for success confirmation
            });
            this.loadInquiries(); // Refresh inquiry list
          });
        }
      });
    }
}
