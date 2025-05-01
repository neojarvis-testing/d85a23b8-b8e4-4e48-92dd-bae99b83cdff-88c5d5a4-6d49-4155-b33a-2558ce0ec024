import { Component, OnInit } from '@angular/core';
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property.model';

@Component({
  selector: 'app-user-view-properties',
  templateUrl: './user-view-properties.component.html',
  styleUrls: ['./user-view-properties.component.css']
})
export class UserViewPropertiesComponent implements OnInit {
  properties: Property[] = []; // Stores all properties
  filteredProperties: Property[] = []; // Properties displayed after filtering
  searchLocation: string = ''; // Search input field for location
  filterType: string = 'All'; // Selected type filter
  filterStatus: string = 'All'; // Selected status filter
  propertyTypes = ['All', 'Residential', 'Commercial']; // Property type options
  propertyStatuses = ['All', 'Available', 'Sold']; // Status options

  constructor(private propertyService: PropertyService) {}

  ngOnInit(): void {
    this.loadProperties();
  }

  /**
   * Fetches all properties and applies default filtering.
   */
  loadProperties(): void {
    this.propertyService.getAllProperties().subscribe(
      (data) => {
        this.properties = data;
        this.applyFilters(); // Apply filters after fetching properties
      },
      (error) => {
        console.error('Error fetching properties:', error);
        this.properties = [];
        this.filteredProperties = [];
      }
    );
  }

  /**
   * Filters properties based on location, type, and status selection.
   */
  applyFilters(): void {
    this.filteredProperties = this.properties.filter(property =>
      (this.filterType === 'All' || property.type === this.filterType) &&
      (this.filterStatus === 'All' || property.status === this.filterStatus) &&
      property.location.toLowerCase().includes(this.searchLocation.toLowerCase())
    );
  }

  /**
   * Handles inquiry for a selected property.
   * @param propertyId The ID of the property to inquire about.
   */
  inquireProperty(propertyId: number): void {
    console.log(`User inquiring about property ID: ${propertyId}`);
    // Implement inquiry logic here (e.g., open inquiry form or send request)
  }
}
