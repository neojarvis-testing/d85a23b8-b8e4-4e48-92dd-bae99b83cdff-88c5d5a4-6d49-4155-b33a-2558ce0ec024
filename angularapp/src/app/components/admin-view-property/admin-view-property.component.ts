import { Component, OnInit } from '@angular/core';
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property.model';
import { Router } from '@angular/router';

declare let bootstrap: any; // Ensure Bootstrap JS is accessible

@Component({
  selector: 'app-admin-view-property',
  templateUrl: './admin-view-property.component.html',
  styleUrls: ['./admin-view-property.component.css']
})
export class AdminViewPropertyComponent implements OnInit {
  properties: Property[] = [];


  selectedProperty: Property | null = null;

  filteredProperties: Property[] = [];
  searchTerm: string = '';
  selectedType: string = '';
  deletePropertyId: number | null = null;

  constructor(private readonly propertyService: PropertyService, private readonly router: Router) {}

  ngOnInit(): void {
    this.getAllProperties();
  }

  // Fetch all properties
  getAllProperties(): void {
    this.propertyService.getAllProperties().subscribe(
      (response) => {
        this.properties = response.filter(property => property.deleted === 0);
        this.filteredProperties = [...this.properties];
      },
      (error) => {
        console.error('Error fetching properties:', error);
      }
    );
  }

  // Filter properties based on search and type
  filterProperties(): void {
    this.filteredProperties = this.properties.filter(property =>
      (this.selectedType === '' || property.type === this.selectedType) &&
      property.title.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  // Edit property
  editProperty(propertyId: number): void {
    this.router.navigate(['admin-edit-property', propertyId]);
  }

  // Trigger modal for delete confirmation
  confirmDelete(propertyId: number): void {
    this.deletePropertyId = propertyId;
    const modalElement = document.getElementById('deleteConfirmModal');
    if (modalElement) {
      new bootstrap.Modal(modalElement).show();
    }
  }

  // Delete property after confirmation
  deleteProperty(): void {
    if (this.deletePropertyId !== null) {
      this.propertyService.deleteProperty(this.deletePropertyId).subscribe(
        () => {
          this.getAllProperties();
          this.deletePropertyId = null;

          // Close modal after deletion
          const modalElement = document.getElementById('deleteConfirmModal');
          if (modalElement) {
            bootstrap.Modal.getInstance(modalElement)?.hide();
          }
        },
        (error) => {
          console.error('Error deleting property:', error);
        }
      );
    }
  }
}