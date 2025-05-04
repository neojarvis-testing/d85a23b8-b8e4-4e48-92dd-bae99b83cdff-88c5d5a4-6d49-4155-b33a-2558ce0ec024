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
 
  showSuccessModal = false;
  showErrorModal = false;
 
  constructor(private readonly propertyService: PropertyService, private readonly router: Router) {}
 
  ngOnInit(): void {
    throw new Error("notImplemented()")
  }
 
  addProperty(): void {
    if (!this.newProperty.title || !this.newProperty.location || this.newProperty.price <= 0) {
        this.showErrorModal = true;
        return;
    }
 
    this.propertyService.addProperty(this.newProperty).subscribe(
        (response) => {
            console.log('Property added successfully:', response);
            this.showSuccessModal = true; // Show success modal
 
            setTimeout(() => {
                this.closeModal();
                this.router.navigate(['admin-view-property']); // Navigate after showing the message
            }, 2000); // Display success message for 2 seconds before navigation
        },
        (error) => {
            console.error('Error adding property:', error);
            this.showErrorModal = true; // Show error modal
        }
    );
}
 
 
  closeModal(): void {
    this.showSuccessModal = false;
    this.showErrorModal = false;
    this.newProperty = { propertyId: null, title: '', description: '', location: '', price: 0, type: '', status: '' };
  }
}
 