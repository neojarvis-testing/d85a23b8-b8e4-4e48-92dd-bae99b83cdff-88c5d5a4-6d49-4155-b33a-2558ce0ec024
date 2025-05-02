import { Component, OnInit } from '@angular/core';
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-view-properties',
  templateUrl: './user-view-properties.component.html',
  styleUrls: ['./user-view-properties.component.css']
})
export class UserViewPropertiesComponent implements OnInit {
  properties: Property[] = []; // Stores all properties
  filteredProperties: Property[] = []; // Properties displayed after filtering
  searchLocation: string = ''; // Search field for location filtering
  filterType: string = 'All'; // Selected type filter
  filterStatus: string = 'All'; // Selected status filter
  propertyTypes: string[] = ['All', 'Residential', 'Commercial']; // Property type filter options
  propertyStatuses: string[] = ['All', 'Available', 'Sold']; // Property status filter options

  constructor(private readonly propertyService: PropertyService, private readonly router: Router) {}


  ngOnInit(): void {
    this.loadProperties();
  }

  /**
   * Fetches all properties and applies default filtering.
   */
  loadProperties(): void {
    this.propertyService.getAllProperties().subscribe(
      (data) => {
        this.properties = data.filter(property => property.deleted === 0);
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
   * Ensures case-insensitive matching for location search.
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
   this.router.navigate(['user-add-inquiry',propertyId])
    // Implement inquiry logic here (e.g., open inquiry form or send request)
  }
}
