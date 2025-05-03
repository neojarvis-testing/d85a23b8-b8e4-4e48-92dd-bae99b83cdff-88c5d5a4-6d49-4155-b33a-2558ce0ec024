import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-user-view-feedback',
  templateUrl: './user-view-feedback.component.html',
  styleUrls: ['./user-view-feedback.component.css']
})
export class UserViewFeedbackComponent implements OnInit {

  /*
    Stores the list of feedbacks retrieved from the service.
  */
  feedbacks: Feedback[] = [];

  /*
    Maintains a filtered list of feedbacks based on user-selected filters.
  */
  filteredFeedbacks: Feedback[] = [];

  /*
    Stores paginated feedbacks to display in chunks based on the page size.
  */
  paginatedFeedbacks: Feedback[] = [];

  /*
    Retrieves the logged-in user ID from localStorage for filtering user-specific feedback.
  */
  userId: any = localStorage.getItem('userId');

  /*
    Defines the number of feedback items displayed per page.
  */
  pageSize = 5;

  /*
    Tracks the current page number for pagination.
  */
  currentPage = 1;

  /*
    Stores the total number of pages available for pagination.
  */
  totalPages: number[] = [];

  /*
    Stores the user's search input for dynamic feedback filtering.
  */
  searchText = '';

  /*
    Holds the selected feedback category for filtering.
  */
  selectedCategory = '';

  /*
    Constructor injects necessary services:
    - `FeedbackService` for handling API interactions related to feedback retrieval and deletion.
  */
  constructor(private readonly feedbackService: FeedbackService) {}

  /*
    Lifecycle hook executed when the component initializes.
    Fetches feedbacks associated with the logged-in user.
  */
  ngOnInit(): void {
    this.getFeedbacksByUser();
  }

  /*
    Fetches feedbacks for the logged-in user and applies filters.
    Displays an error message in the console if retrieval fails.
  */
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

  /*
    Filters feedbacks based on search text and selected category.
    Updates pagination settings after filtering.
  */
  filterFeedbacks(): void {
    this.filteredFeedbacks = this.feedbacks.filter(feedback => {
      const matchesSearch = feedback.feedbackText.toLowerCase().includes(this.searchText.toLowerCase());
      const matchesCategory = this.selectedCategory ? feedback.category === this.selectedCategory : true;
      return matchesSearch && matchesCategory;
    });

    this.setupPagination();
  }

  /*
    Sets up pagination based on the filtered feedback count.
    Generates the total number of pages dynamically.
  */
  setupPagination(): void {
    this.totalPages = Array(Math.ceil(this.filteredFeedbacks.length / this.pageSize))
                        .fill(0)
                        .map((_, i) => i + 1);
    this.updatePaginatedFeedbacks();
  }

  /*
    Changes the current page when the user navigates through pagination.
    Updates displayed feedbacks accordingly.
  */
  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages.length) {
        this.currentPage = page;
        this.updatePaginatedFeedbacks();
    }
  }

  /*
    Updates feedback items to display only those within the selected page range.
  */
  updatePaginatedFeedbacks(): void {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    this.paginatedFeedbacks = this.filteredFeedbacks.slice(startIndex, startIndex + this.pageSize);
  }

  /*
    Deletes a feedback entry by its ID.
    Updates the feedback list and filters after deletion.
  */
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
