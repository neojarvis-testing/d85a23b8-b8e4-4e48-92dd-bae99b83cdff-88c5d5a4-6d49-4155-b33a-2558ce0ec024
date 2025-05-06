import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PropertyService } from 'src/app/services/property.service';
import { FeedbackService } from 'src/app/services/feedback.service';
import { Property } from 'src/app/models/property.model';
import { Feedback } from 'src/app/models/feedback.model';
import { User } from 'src/app/models/user.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-add-feedback',
  templateUrl: './user-add-feedback.component.html',
  styleUrls: ['./user-add-feedback.component.css']
})
export class UserAddFeedbackComponent implements OnInit {

  /*
    Stores the list of available properties for selection when submitting feedback.
  */
  properties: Property[] = [];

  /*
    Holds the selected property ID for which feedback is being submitted.
  */
  propertyId: number = 0;

  /*
    Stores messages to inform the user about success or errors.
  */
  successMessage: string = '';
  errorMessage: string = '';

  /*
    Controls the visibility of the popup message upon successful feedback submission.
  */
  showPopup: boolean = false;

  /*
    Represents the feedback data model, initialized with default values.
    Includes associated property and user details.
  */
  feedback: Feedback = {
    feedbackText: '',
    category: '',
    property: { propertyId: 0 } as Property,
    user: { userId: 0 } as User
  };

  /*
    Constructor injects necessary services:
    - `Router` for navigation.
    - `FeedbackService` for handling feedback API interactions.
    - `PropertyService` for retrieving available properties.
  */
  constructor(
    private readonly router: Router,
    private readonly feedbackService: FeedbackService,
    private readonly propertyService: PropertyService
  ) {}

  /*
    Lifecycle hook executed when the component initializes.
    Fetches the list of properties.
    Retrieves the logged-in user ID from localStorage.
    Displays an error message if the user is not logged in.
  */
  ngOnInit(): void {
    this.getProperties();
    const userId = Number(localStorage.getItem('userId'));
    if (!isNaN(userId) && userId > 0) {
      this.feedback.user.userId = userId;
    } else {
      this.errorMessage = 'User not logged in. Please log in to submit feedback.';
    }
  }

  /*
    Fetches all available properties from the server.
    Updates the `properties` list upon successful retrieval.
    Displays an error message if the request fails.
  */
  getProperties(): void {
    this.propertyService.getAllProperties().subscribe({
      next: (data) => {
        this.properties = data;
      },
      error: () => {
        this.errorMessage = 'Failed to load property list.';
      }
    });
  }

  /*
    Opens the success popup.
  */
  openPopup(): void {
    this.showPopup = true;
  }

  /*
    Closes the success popup.
  */
  closePopup(): void {
    this.showPopup = false;
  }

  /*
    Confirms and submits the feedback form.
    Ensures a property is selected before proceeding.
    Sends feedback data to the server and resets the form upon success.
    Displays appropriate success or error messages.
  */
  confirmSubmit(feedbackForm: NgForm): void {
    if (!this.propertyId) {
      this.errorMessage = 'Please select a property before submitting feedback.';
      return;
    }

    const feedbackData = {
      ...feedbackForm.value,
      userId: this.feedback.user.userId,
      propertyId: this.propertyId
    };

    console.log("Submitting Feedback: ", feedbackData); // Debugging log

    this.feedbackService.sendFeedback(feedbackData).subscribe({
      next: () => {
        console.log("Feedback submitted successfully!"); // Debugging log
        this.successMessage = '✅ Feedback submitted successfully!';
        this.showPopup = false;
        feedbackForm.reset();
        this.router.navigate(['/user-view-feedback']);
      },
      error: (err) => {
        console.error("Failed to submit feedback:", err); // Debugging log
        this.errorMessage = '❌ Failed to submit feedback. Please try again.';
        this.showPopup = false;
      }
    });
}

}
