import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { PropertyInquiry } from 'src/app/models/property-inquiry.model';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';

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
  statuses = ['All', 'Pending', 'Resolved'];

  filterPriority = 'All';
  filterStatus = 'All';
  searchInquiry = ''; // Search field for filtering inquiries dynamically

  constructor(
    private readonly inquiryService: PropertyInquiryService,
    private readonly fb: FormBuilder
  ) {
    this.responseForm = this.fb.group({
      adminResponse: [''],
      contactDetails: ['']
    });
  }

  ngOnInit(): void {
    this.loadInquiries();
  }

  /**
   * Fetches all inquiries for admin view.
   * Populates both inquiries and filteredInquiries on successful retrieval.
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

  /**
   * Applies filters for priority, status, and search.
   */
  applyFilters(): void {
    this.filteredInquiries = this.inquiries.filter(inquiry =>
      (this.filterPriority === 'All' || inquiry.priority === this.filterPriority) &&
      (this.filterStatus === 'All' || inquiry.status === this.filterStatus) &&
      inquiry.property.title.toLowerCase().includes(this.searchInquiry.toLowerCase())
    );
  }

  /**
   * Opens the inquiry response modal.
   * @param inquiry The selected inquiry for response.
   */
  openResponseModal(inquiry: PropertyInquiry): void {
    this.selectedInquiry = inquiry;
    this.responseForm.reset();
  }

  /**
   * Submits an admin response to the selected inquiry.
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

  /**
   * Deletes an inquiry by ID.
   * @param id The ID of the inquiry to be deleted.
   */
  deleteInquiry(id: number): void {
    this.inquiryService.deletePropertyInquiryById(id).subscribe(() => {
      this.loadInquiries(); // Refresh list after deletion
    });
  }
}
