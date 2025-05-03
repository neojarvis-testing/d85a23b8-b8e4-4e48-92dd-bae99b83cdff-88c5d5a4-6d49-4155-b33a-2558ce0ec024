import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property.model';

@Component({
  selector: 'app-admin-edit-property',
  templateUrl: './admin-edit-property.component.html',
  styleUrls: ['./admin-edit-property.component.css']
})
export class AdminEditPropertyComponent implements OnInit {
  
  /*
    Holds the property details for editing.
    Initially set to null until data is retrieved.
  */
  property: Property | null = null;
  
  /*
    Stores the property ID fetched from route parameters.
  */
  propertyId: number = 0;
  
  /*
    Controls the visibility of the success message popup.
  */
  showSuccessPopup: boolean = false;

  /*
    Constructor to inject necessary services:
    - `PropertyService` for API calls related to properties.
    - `ActivatedRoute` to fetch route parameters.
    - `Router` to navigate between components.
  */
  constructor(
    private readonly propertyService: PropertyService,
    private readonly route: ActivatedRoute,
    private readonly router: Router
  ) {}

  /*
    Lifecycle hook that runs when the component is initialized.
    Retrieves the property ID from route parameters and fetches property details.
  */
  ngOnInit(): void {
    this.propertyId = Number(this.route.snapshot.paramMap.get('id'));
    this.getPropertyDetails();
  }

  /*
    Fetches the details of the property based on `propertyId`.
    Updates the `property` variable with the retrieved data.
  */
  getPropertyDetails(): void {
    this.propertyService.getPropertyById(this.propertyId).subscribe(
      (response) => {
        this.property = response;
      },
      (error) => {
        console.error('Error fetching property details:', error);
      }
    );
  }

  /*
    Updates the property details.
    Sends the updated property data to the server.
    Displays a success popup upon successful update.
  */
  updateProperty(): void {
    if (this.property) {
      this.propertyService.updateProperty(this.propertyId, this.property).subscribe(
        () => {
          this.showSuccessPopup = true; // Show popup message
        },
        (error) => {
          console.error('Error updating property:', error);
        }
      );
    }
  }

  /*
    Closes the success popup and navigates back to the property view page.
  */
  closePopup(): void {
    this.showSuccessPopup = false;
    this.router.navigate(['/admin-view-property']);
  }
}
