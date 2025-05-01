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
  properties: Property[] = [];
  propertyId: number = 0;
  successMessage: string = '';
  errorMessage: string = '';
  showPopup: boolean = false; // Controls popup visibility

  feedback: Feedback = {
    feedbackText: '',
    category: '',
    property: { propertyId: 0 } as Property,
    user: { userId: 0 } as User
  };

  constructor(
    private router: Router,
    private feedbackService: FeedbackService,
    private propertyService: PropertyService
  ) {}

  ngOnInit(): void {
    this.getProperties();
    const userId = Number(localStorage.getItem('userId'));
    if (!isNaN(userId) && userId > 0) {
      this.feedback.user.userId = userId;
    } else {
      this.errorMessage = 'User not logged in. Please log in to submit feedback.';
    }
  }

  getProperties(): void {
    this.propertyService.getAllProperties().subscribe({
      next: (data) => {
        this.properties = data || [];
      },
      error: () => {
        this.errorMessage = 'Failed to load property list.';
      }
    });
  }

  openPopup(): void {
    this.showPopup = true;
  }

  closePopup(): void {
    this.showPopup = false;
  }

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

    this.feedbackService.sendFeedback(feedbackData).subscribe({
      next: () => {
        this.successMessage = '✅ Feedback submitted successfully!';
        this.showPopup = false;
        feedbackForm.reset();
      },
      error: () => {
        this.errorMessage = '❌ Failed to submit feedback. Please try again.';
        this.showPopup = false;
      }
    });
  }
}
