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
  property: Property | null = null;
  propertyId: number = 0;
  showSuccessPopup: boolean = false; // Controls success message display

  constructor(
    private propertyService: PropertyService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.propertyId = Number(this.route.snapshot.paramMap.get('id'));
    this.getPropertyDetails();
  }

  // Fetch property details
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

  // Update property details
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

  // Close success popup and navigate back
  closePopup(): void {
    this.showSuccessPopup = false;
    this.router.navigate(['/admin-view-property']);
  }
}