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

  constructor(private propertyService: PropertyService, private router:Router) {}

  ngOnInit(): void {
    this.getAllProperties(); // Load properties when component initializes
  }

  // Fetch all properties
  getAllProperties(): void {
    this.propertyService.getAllProperties().subscribe(
      (response) => {
        console.log(response)
        this.properties = response;
        console.log(this.properties)
        this.properties=this.properties.filter(property=>property.deleted==0)
        console.log(this.properties)
      },
      (error) => {
        console.error('Error fetching properties:', error);
        alert('Failed to load properties.');
      }
    );
  }

  // View details of a property
  viewProperty(propertyId: number): void {
    this.propertyService.getPropertyById(propertyId).subscribe(
      (response) => {
        this.selectedProperty = response;
      },
      (error) => {
        console.error('Error fetching property details:', error);
        alert('Failed to load property details.');
      }
    );
  }

  // Navigate to update property form
 

  // Delete a property
  deleteProperty(propertyId: number): void {
    if (confirm('Are you sure you want to delete this property?')) {
      this.propertyService.deleteProperty(propertyId).subscribe(
        (data) => {
          alert('Property deleted successfully.');
          data=this.getAllProperties(); // Refresh property list
        },
        (error) => {
          console.error('Error deleting property:', error);
          alert('Failed to delete property.');
        }
      );
    }
  }
  editProperty(propertyId: number): void {
    this.router.navigate(['admin-edit-property', propertyId]); // Redirect to admin-edit-property with ID
  }

  

  // Close property details
  closeDetails(): void {
    this.selectedProperty = null;
  }
}