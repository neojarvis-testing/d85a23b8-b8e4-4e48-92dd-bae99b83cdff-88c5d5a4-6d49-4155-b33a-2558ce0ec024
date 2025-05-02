import { Component, OnInit } from '@angular/core';
import { FeedbackService } from 'src/app/services/feedback.service';
import { Feedback } from 'src/app/models/feedback.model';
import { PropertyService } from 'src/app/services/property.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-admin-view-feedback',
  templateUrl: './admin-view-feedback.component.html',
  styleUrls: ['./admin-view-feedback.component.css']
})

export class AdminViewFeedbackComponent implements OnInit {

  feedbacks: Feedback[] = []; // Stores all feedbacks submitted by users
  filteredFeedbacks: Feedback[] = []; // Stores filtered feedbacks based on category selection & search input
  selectedCategory: string = 'All'; // Default filter selection
  searchTerm: string = ''; // Search input field
  categories: string[] = ['Service', 'Pricing', 'Quality']; // Predefined categories
  selectedUser: any = null; // Stores selected user details for modal display
  selectedProperty: any = null; // Stores selected property details for modal display

  constructor(
    private readonly feedbackService: FeedbackService,
    private readonly authService: AuthService,
    private readonly propertyService: PropertyService
  ) {}

  ngOnInit(): void {
    this.getAllFeedbacks(); // Load feedbacks when component initializes
  }

  /**
   * Fetches all feedbacks for admin view.
   * If data is retrieved successfully, it populates both feedbacks and filteredFeedbacks.
   * If an error occurs, it clears the feedback lists.
   */
  getAllFeedbacks(): void {
    this.feedbackService.getFeedbacks().subscribe(
      (data) => {
        console.log(data);
        this.feedbacks = data;
        this.filteredFeedbacks = data; // Default to displaying all feedbacks
      },
      (error) => {
        console.error('Error fetching feedback:', error);
        this.feedbacks = [];
        this.filteredFeedbacks = [];
      }
    );
  }

  /**
   * Filters feedback based on the selected category and search term.
   */
  filterFeedbacks(): void {
    let filteredByCategory = this.selectedCategory === 'All' 
      ? this.feedbacks 
      : this.feedbacks.filter(f => f.category === this.selectedCategory);

    this.filteredFeedbacks = filteredByCategory.filter(f => 
      f.feedbackText.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  /**
   * Filters feedback based on the selected category.
   * If "All" is selected, displays all feedbacks; otherwise, filters by category.
   */
  filterByCategory(): void {
    this.filterFeedbacks(); // Calls search filter method to combine search and category selection
  }

  /**
   * Fetches and displays user details when "Show Profile" is clicked.
   * If data retrieval fails, an error message is logged.
   * @param userId - Unique identifier for the user
   */
  showUserDetails(userId: number): void {
    this.authService.getUserById(userId).subscribe(
      (user) => {
        console.log("Fetched User Data:", user); // Debugging user data
        this.selectedUser = user ?? null;
      },
      (error) => console.error('Error fetching user details:', error)
    );
  }

  /**
   * Fetches and displays property details when "View Property Info" is clicked.
   * If data retrieval fails, an error message is logged.
   * @param propertyId - Unique identifier for the property
   */
  showPropertyDetails(propertyId: number): void {
    this.propertyService.getPropertyById(propertyId).subscribe(
      (property) => {
        console.log("Fetched Property Data:", property); // Debugging property data
        this.selectedProperty = property;
      },
      (error) => console.error('Error fetching property details:', error)
    );
  }

  /**
   * Closes the user details modal.
   */
  closeUserDetails(): void {
    this.selectedUser = null;
  }

  /**
   * Closes the property details modal.
   */
  closePropertyDetails(): void {
    this.selectedProperty = null;
  }
}
