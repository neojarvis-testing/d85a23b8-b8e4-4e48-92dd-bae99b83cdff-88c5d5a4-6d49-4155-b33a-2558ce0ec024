import { Component, OnInit } from '@angular/core';
import { PropertyInquiry } from 'src/app/models/property-inquiry.model';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';

@Component({
  selector: 'app-my-inquiry',
  templateUrl: './my-inquiry.component.html',
  styleUrls: ['./my-inquiry.component.css']
})
export class MyInquiryComponent implements OnInit { 

  inquiries: PropertyInquiry[] = [];
  filteredInquiries: PropertyInquiry[] = [];
  paginatedInquiries: PropertyInquiry[] = [];
  userId: number;
  pageSize = 5;
  currentPage = 1;
  totalPages: number[] = [];
  searchText = '';
  selectedPriority = '';

  constructor(private readonly service: PropertyInquiryService) {}

  ngOnInit(): void {
    this.loadInquiries();
  }

  /**
   * Fetches inquiries submitted by the user
   * Retrieves data using the user ID and updates lists.
   */
  loadInquiries(): void {
    this.userId = +localStorage.getItem('userId');
    this.service.getInquiryByUserId(this.userId).subscribe(
      (data: PropertyInquiry[]) => {
        this.inquiries = data;
        this.filterInquiries();
      },
      (error) => {
        console.error('Error fetching inquiries', error);
        this.inquiries = [];
      }
    );
  }

  /**
   * Filters inquiries based on search text and selected priority.
   * Updates pagination accordingly.
   */
  filterInquiries(): void {
    this.filteredInquiries = this.inquiries.filter(inquiry => {
      const matchesSearch =  inquiry.property.title.toLowerCase().includes(this.searchText.toLowerCase());
      const matchesPriority = this.selectedPriority ? inquiry.priority === this.selectedPriority : true;
      return matchesSearch && matchesPriority;
    });

    this.setupPagination();
  }

  /**
   * Initializes pagination based on filtered inquiries.
   */
  setupPagination(): void {
    this.totalPages = Array(Math.ceil(this.filteredInquiries.length / this.pageSize))
                        .fill(0)
                        .map((_, i) => i + 1);
    this.updatePaginatedInquiries();
  }

  /**
   * Changes page when user clicks pagination buttons.
   */
  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages.length) {
        this.currentPage = page;
        this.updatePaginatedInquiries();
    }
  }

  /**
   * Updates displayed inquiries based on pagination.
   */
  updatePaginatedInquiries(): void {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    this.paginatedInquiries = this.filteredInquiries.slice(startIndex, startIndex + this.pageSize);
  }
}
