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
  constructor(private readonly propertyService: PropertyService, private router:Router) {}
  filteredProperties: Property[] = [];
  searchTerm: string = ''; // Stores user input for search
  selectedType: string = ''; // Stores selected type for filtering
  deletePropertyId: number | null = null; // Fix: Proper declaration

  constructor(private readonly propertyService: PropertyService, private readonly router: Router) {}


  ngOnInit(): void {
    this.getAllProperties();
  }

  // Fetch all properties
  getAllProperties(): void {
    this.propertyService.getAllProperties().subscribe(
      (response) => {
        this.properties = response.filter(property => property.deleted === 0);
        this.filteredProperties = [...this.properties]; // Initialize filtered properties
      },
      (error) => {
        console.error('Error fetching properties:', error);
      }
    );
  }

  // **Updated Filter Function** (Ensures proper filtering)
  filterProperties(): void {
    this.filteredProperties = this.properties.filter(property =>
      (this.selectedType === '' || property.type === this.selectedType) &&
      property.title.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  // Edit a property
  editProperty(propertyId: number): void {
    this.router.navigate(['admin-edit-property', propertyId]);
  }

  // Confirm delete action
  confirmDelete(propertyId: number): void {
    this.deletePropertyId = propertyId;
  }

  // Delete property after confirmation
  deleteProperty(): void {
    if (this.deletePropertyId !== null) {
      this.propertyService.deleteProperty(this.deletePropertyId).subscribe(
        () => {
          this.getAllProperties();
          this.deletePropertyId = null; // Reset delete state
        },
        (error) => {
          console.error('Error deleting property:', error);
        }
      );
    }
  }
}