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

  /*
    Stores the list of properties fetched from the service.
    This list is used to display all available properties that have not been deleted.
  */
  properties: Property[] = [];

  /*
    Stores the currently selected property for potential operations like editing.
    Initially set to null until a selection is made.
  */
  selectedProperty: Property | null = null;

  /*
    Holds the filtered list of properties based on user input filters such as search term and type.
  */
  filteredProperties: Property[] = [];

  /*
    Stores the user's search term for dynamic filtering.
  */
  searchTerm: string = '';

  /*
    Stores the selected property type for filtering.
  */
  selectedType: string = '';

  /*
    Stores the ID of a property that is selected for deletion.
    Initially set to null until the delete confirmation is triggered.
  */
  deletePropertyId: number | null = null;

  /*
    Constructor injects necessary services:
    - `PropertyService` for fetching, updating, and deleting properties.
    - `Router` for navigation between different components.
  */
  constructor(private readonly propertyService: PropertyService, private readonly router: Router) {}

  /*
    Lifecycle hook executed when the component initializes.
    Fetches all properties from the service.
  */
  ngOnInit(): void {
    this.getAllProperties();
  }

  /*
    Fetches all properties from the server.
    Filters out deleted properties before assigning them to the main list.
    Also initializes the filtered list to match the fetched properties.
  */
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

  /*
    Applies filters to properties based on search term and selected property type.
  */
  filterProperties(): void {
    this.filteredProperties = this.properties.filter(property =>
      (this.selectedType === '' || property.type === this.selectedType) &&
      property.title.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  /*
    Navigates to the edit property page for the specified property ID.
  */
  editProperty(propertyId: number): void {
    this.router.navigate(['admin-edit-property', propertyId]);
  }

  /*
    Displays the delete confirmation modal for the selected property.
    Uses Bootstrap's modal functionality.
  */
  confirmDelete(propertyId: number): void {
    this.deletePropertyId = propertyId;
    const modalElement = document.getElementById('deleteConfirmModal');
    if (modalElement) {
      new bootstrap.Modal(modalElement).show();
    }
  }

  /*
    Deletes a property after confirmation.
    Refreshes the property list and closes the confirmation modal upon successful deletion.
  */
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
