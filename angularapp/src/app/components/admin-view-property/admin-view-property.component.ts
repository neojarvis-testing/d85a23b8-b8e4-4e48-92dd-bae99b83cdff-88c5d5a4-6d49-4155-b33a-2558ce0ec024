import { Component, OnInit } from '@angular/core';
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-view-property',
  templateUrl: './admin-view-property.component.html',
  styleUrls: ['./admin-view-property.component.css']
})
export class AdminViewPropertyComponent implements OnInit {
  properties: Property[] = [];
  selectedProperty: Property | null = null;
  deletePropertyId: number | null = null; // To store the property ID for deletion

  constructor(private propertyService: PropertyService, private router: Router) {}

  ngOnInit(): void {
    this.getAllProperties(); // Load properties when component initializes
  }

  // Fetch all properties
  getAllProperties(): void {
    this.propertyService.getAllProperties().subscribe(
      (response) => {
        console.log(response);
        this.properties = response.filter(property => property.deleted === 0);
      },
      (error) => {
        console.error('Error fetching properties:', error);
      }
    );
  }

  // Open confirmation modal for delete
  confirmDelete(propertyId: number): void {
    this.deletePropertyId = propertyId;
  }

  // Delete a property after confirmation
  deleteProperty(): void {
    if (this.deletePropertyId !== null) {
      this.propertyService.deleteProperty(this.deletePropertyId).subscribe(
        () => {
          this.getAllProperties(); // Refresh property list
          this.deletePropertyId = null; // Reset delete state
        },
        (error) => {
          console.error('Error deleting property:', error);
        }
      );
    }
  }

  // Cancel delete action
  cancelDelete(): void {
    this.deletePropertyId = null;
  }

  // Edit a property (Navigate to edit property page)
  editProperty(propertyId: number): void {
    this.router.navigate(['admin-edit-property', propertyId]); // Redirect to edit property page
  }

  // Close property details
  closeDetails(): void {
    this.selectedProperty = null;
  }
}