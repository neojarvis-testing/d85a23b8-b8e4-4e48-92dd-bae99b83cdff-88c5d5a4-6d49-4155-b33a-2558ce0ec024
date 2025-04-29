import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Import Router for navigation
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property.model';

@Component({
  selector: 'app-admin-add-property',
  templateUrl: './admin-add-property.component.html',
  styleUrls: ['./admin-add-property.component.css']
})
export class AdminAddPropertyComponent implements OnInit {
  newProperty: Property = {
    propertyId: null,
    title: '',
    description: '',
    location: '',
    price: 0,
    type: '',
    status: ''
  };

  constructor(private propertyService: PropertyService, private router: Router) {} // Inject Router

  ngOnInit(): void {}

  addProperty(): void {
    this.propertyService.addProperty(this.newProperty).subscribe(
      (response) => {
        console.log('Property added successfully:', response);
        alert('Property added successfully!');
        
        // Redirect to Admin View Property page
        this.router.navigate(['/admin-view-property']);
      },
      (error) => {
        console.error('Error adding property:', error);
        alert('Failed to add property!');
      }
    );
  }
}