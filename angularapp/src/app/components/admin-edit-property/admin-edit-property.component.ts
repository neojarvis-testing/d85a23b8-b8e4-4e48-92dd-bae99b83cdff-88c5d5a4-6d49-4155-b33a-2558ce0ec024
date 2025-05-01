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

  constructor(
    private propertyService: PropertyService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Extract property ID from the route
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
          alert('Property updated successfully.');
          this.router.navigate(['/admin-view-property']); // Redirect to view page
        },
        (error) => {
          console.error('Error updating property:', error);
          alert('Failed to update property.');
        }
      );
    } else {
      alert('Property details are missing!');
    }
  }
}