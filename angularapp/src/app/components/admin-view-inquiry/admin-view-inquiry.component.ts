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

  inquiries: PropertyInquiry[] = [];
  filteredInquiries: PropertyInquiry[] = [];

  responseForm: FormGroup;
  selectedInquiry: PropertyInquiry | null = null;

  priorities = ['All', 'Low', 'Medium', 'High'];
  statuses = ['All', 'Resolved', 'Pending', 'Unresolved']; // ✅ Updated status options

  filterPriority = 'All';
  filterStatus = 'All';
  searchInquiry = '';

  constructor(
    private readonly inquiryService: PropertyInquiryService,
    private readonly fb: FormBuilder
  ) {
    this.responseForm = this.fb.group({
      adminResponse: [''],
      contactDetails: [''],
      status: ['Unresolved'] // ✅ Default status selection
    });
  }

  ngOnInit(): void {
    this.loadInquiries();
  }

  loadInquiries(): void {
    this.inquiryService.getAllPropertyInquiry().subscribe(
      (data) => {
        this.inquiries = data;
        this.applyFilters();
      },
      (error) => {
        console.error('Error fetching inquiries:', error);
        this.inquiries = [];
        this.filteredInquiries = [];
      }
    );
  }

  applyFilters(): void {
    this.filteredInquiries = this.inquiries.filter(inquiry =>
      (this.filterPriority === 'All' || inquiry.priority === this.filterPriority) &&
      (this.filterStatus === 'All' || inquiry.status === this.filterStatus) &&
      inquiry.property.title.toLowerCase().includes(this.searchInquiry.toLowerCase())
    );
  }

  openResponseModal(inquiry: PropertyInquiry): void {
    this.selectedInquiry = inquiry;
    this.responseForm.reset();
    this.responseForm.patchValue({ status: inquiry.status }); // ✅ Ensure status dropdown is pre-filled
  }

  submitResponse(): void {
    if (this.selectedInquiry) {
      const updatedInquiry: PropertyInquiry = {
        ...this.selectedInquiry,
        ...this.responseForm.value,
        responseDate: new Date()
      };

      this.inquiryService.editPropertyInquiryById(updatedInquiry.inquiryId, updatedInquiry).subscribe(() => {
        this.selectedInquiry = null;
        this.loadInquiries();
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

