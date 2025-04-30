import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-user-view-feedback', // Defines the selector for this component
  templateUrl: './user-view-feedback.component.html', // Links the HTML template
  styleUrls: ['./user-view-feedback.component.css'] // Links the CSS styles
})
export class UserViewFeedbackComponent implements OnInit {

  feedbacks: Feedback[] = []; // Stores feedbacks specific to the logged-in user
  userId:any= localStorage.getItem('userId')// Stores the user ID for fetching relevant feedback

  constructor(private feedbackService: FeedbackService) {} // Injects the FeedbackService for API calls

  ngOnInit(): void {
    this.getFeedbacksByUser(); // Calls method to fetch user-specific feedback when component initializes
  }

  /**
   * Fetch feedbacks submitted by the logged-in user.
   * Retrieves feedback using the user ID and populates the feedbacks array.
   * Displays an error message in the console if data retrieval fails.
   */
  getFeedbacksByUser(): void {
    this.feedbackService.getAllFeedbacksByUserId(this.userId).subscribe(
      (data) => {
        this.feedbacks = data; // Assign fetched feedback to feedbacks array
      },
      (error) => {
        console.error('Error fetching user feedback:', error);
        this.feedbacks = []; // Clears the feedback list and ensures "No Data Found" appears in UI
      }
    );
  }
}
