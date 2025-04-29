import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property.model';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-user-add-feedback',
  templateUrl: './user-add-feedback.component.html',
  styleUrls: ['./user-add-feedback.component.css']
})
export class UserAddFeedbackComponent implements OnInit {

  feedbackForm!: FormGroup;
  properties: Property[] = []; // Stores available properties added by admin

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private feedbackService:FeedbackService, // Inject Feedback Service
    private propertyService: PropertyService // Inject Property Service
  ) {
    // Initialize form
    this.feedbackForm = this.fb.group({
      message: ['', [Validators.required, Validators.minLength(10)]], // Message is required & min 10 chars
      date: ['', Validators.required], // Date is required
      category: ['', Validators.required], // Must select a category
      property: ['', Validators.required] // Must select a property
    });

  }

  ngOnInit(): void {
    // Fetch properties from Property component
    this.getProperties();
  }

  // Getter for easy access to form controls
  get f() { return this.feedbackForm.controls; }

  /**
   * Fetches properties added by the admin from the Property component
   */
  getProperties(): void {
    // this.propertyService.getAllProperties().subscribe(
    //   (data) => {
    //     this.properties = data; // Populate dropdown with available properties
    //   },
    //   (error) => {
    //     console.error('Error fetching properties:', error);
    //   }
    // );
  }

  /**
   * Handles form submission
   */
  sendNewFeedback(): void {
    if (this.feedbackForm.valid) {
      const feedback: Feedback = this.feedbackForm.value;
      
      this.feedbackService.sendFeedback(feedback).subscribe(
        () => {
          alert('Feedback Submitted Successfully!');
          this.router.navigate(['/user-view-feedback']); // Redirect to feedback list
        },
        (error) => {
          console.error('Error submitting feedback:', error);
          alert('Failed to submit feedback. Please try again.');
        }
      );
    }
  }
}
