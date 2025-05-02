import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-user-view-feedback',
  templateUrl: './user-view-feedback.component.html',
  styleUrls: ['./user-view-feedback.component.css']
})
export class UserViewFeedbackComponent implements OnInit {

  feedbacks: Feedback[] = [];
  filteredFeedbacks: Feedback[] = [];
  paginatedFeedbacks: Feedback[] = [];
  userId: any = localStorage.getItem('userId');
  pageSize = 5;
  currentPage = 1;
  totalPages: number[] = [];
  searchText = '';
  selectedCategory = '';

  constructor(private readonly feedbackService: FeedbackService) {}

  ngOnInit(): void {
    this.getFeedbacksByUser();
  }

  getFeedbacksByUser(): void {
    this.feedbackService.getAllFeedbacksByUserId(this.userId).subscribe(
      (data) => {
        this.feedbacks = data;
        this.filterFeedbacks();
      },
      (error) => {
        console.error('Error fetching user feedback:', error);
        this.feedbacks = [];
      }
    );
  }

  filterFeedbacks(): void {
    this.filteredFeedbacks = this.feedbacks.filter(feedback => {
      const matchesSearch = feedback.feedbackText.toLowerCase().includes(this.searchText.toLowerCase());
      const matchesCategory = this.selectedCategory ? feedback.category === this.selectedCategory : true;
      return matchesSearch && matchesCategory;
    });

    this.setupPagination();
  }

  setupPagination(): void {
    this.totalPages = Array(Math.ceil(this.filteredFeedbacks.length / this.pageSize))
                        .fill(0)
                        .map((_, i) => i + 1);
    this.updatePaginatedFeedbacks();
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages.length) {
        this.currentPage = page;
        this.updatePaginatedFeedbacks();
    }
  }

  updatePaginatedFeedbacks(): void {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    this.paginatedFeedbacks = this.filteredFeedbacks.slice(startIndex, startIndex + this.pageSize);
  }

  deleteFeedback(feedbackId: number): void {
    this.feedbackService.deleteFeedback(feedbackId).subscribe(
      () => {
        this.feedbacks = this.feedbacks.filter(f => f.feedbackId !== feedbackId);
        this.filterFeedbacks();
      },
      (error) => {
        console.error('Error deleting feedback:', error);
      }
    );
  }
}
