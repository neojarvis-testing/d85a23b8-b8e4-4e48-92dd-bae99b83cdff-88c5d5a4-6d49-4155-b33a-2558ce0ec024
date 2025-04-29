import { Component, OnInit } from '@angular/core';
import { PropertyService } from 'src/app/services/property.service';// Import PropertyService
import { Property } from 'src/app/models/property.model'; // Import Property interface



@Component({
  selector: 'app-admin-add-property',
  templateUrl: './admin-add-property.component.html',
  styleUrls: ['./admin-add-property.component.css'],
})
export class AdminAddPropertyComponent implements OnInit {
  newProperty: Property = {
    propertyId: null, // Optional, depending on how the backend handles IDs
    title: '',
    description: '',
    location: '',
    price: 0,
    type: '',
    status: '',
  };

  constructor(private propertyService: PropertyService) {}

  ngOnInit(): void {
    // Initialization logic if needed
  }

  addProperty(): void {
    this.propertyService.addProperty(this.newProperty).subscribe(
      (response) => {
        console.log('Property added successfully:', response);
        alert('Property added successfully!');
        // Reset form or prepare for another property addition
        this.newProperty = {
          propertyId: null,
          title: '',
          description: '',
          location: '',
          price: 0,
          type: '',
          status: '',
        };
      },
      (error) => {
        console.error('Error adding property:', error);
        alert('Failed to add property!');
      }
    );
  }
}