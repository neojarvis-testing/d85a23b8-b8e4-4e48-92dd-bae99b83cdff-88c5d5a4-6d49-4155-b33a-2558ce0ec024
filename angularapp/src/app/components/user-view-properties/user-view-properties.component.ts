import { Component, OnInit } from '@angular/core';
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property.model';

@Component({
  selector: 'app-user-view-properties',
  templateUrl: './user-view-properties.component.html',
  styleUrls: ['./user-view-properties.component.css']
})
export class UserViewPropertiesComponent implements OnInit {
  properties: Property[] = [];

  constructor(private propertyService: PropertyService) {}

  ngOnInit(): void {
    this.getAllProperties();
  }

  getAllProperties(): void {
    this.propertyService.getAllProperties().subscribe(
      (response) => {
        this.properties = response;
        this.properties=this.properties.filter(property=>property.deleted==0)
      },
      (error) => {
        console.error('Error fetching properties:', error);
        alert('Failed to load properties.');
      }
    );
  }
}