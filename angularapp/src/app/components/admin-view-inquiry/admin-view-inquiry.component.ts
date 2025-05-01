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
  searchProperty = '';

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
    this.inquiryService.getAllPropertyInquiry().subscribe((data) => {
      this.inquiries = data;
      console.log(data)
      this.applyFilters();
    });
  }

  applyFilters(): void {
    this.filteredInquiries = this.inquiries.filter(inquiry =>
      (this.filterPriority === 'All' || inquiry.priority === this.filterPriority) &&
      (this.filterStatus === 'All' || inquiry.status === this.filterStatus) &&
      inquiry.property.title.toLowerCase().includes(this.searchProperty.toLowerCase())
    );
  }

  openResponseModal(inquiry: PropertyInquiry): void {
    this.selectedInquiry = inquiry;
    this.responseForm.reset();
  }

  submitResponse(): void {
    if (this.selectedInquiry) {
      const updatedInquiry: PropertyInquiry = {
        ...this.selectedInquiry,
        ...this.responseForm.value,
        inquiryId: this.selectedInquiry.inquiryId, // Ensuring ID is included
        status: 'Resolved',
        responseDate: new Date()
      };
  
      this.inquiryService.editPropertyInquiryById(updatedInquiry.inquiryId,updatedInquiry).subscribe(() => {
        this.selectedInquiry = null;
        this.ngOnInit(); // Refresh list
      });
    }
  }

  deleteInquiry(id: number): void {
    this.inquiryService.deletePropertyInquiryById(id).subscribe(() => this.ngOnInit());
  }
}
