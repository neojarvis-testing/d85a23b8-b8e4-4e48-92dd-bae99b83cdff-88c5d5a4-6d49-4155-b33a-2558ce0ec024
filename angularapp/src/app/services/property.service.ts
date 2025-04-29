import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Property } from '../models/property.model';
import { Observable } from 'rxjs';
import { ApiUrl } from '../constant/ApiUrl';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {
  apiUrl: string = ApiUrl;

  constructor(private http: HttpClient) {}

  // Fetch all properties
  getAllProperties(): Observable<Property[]> {
    return this.http.get<Property[]>(`${this.apiUrl}/properties`);
  }

  // Get property by ID
  getPropertyById(propertyId: number): Observable<Property> {
    return this.http.get<Property>(`${this.apiUrl}/properties/${propertyId}`);
  }

  // Add new property
  addProperty(property: Property): Observable<Property> {
    return this.http.post<Property>(`${this.apiUrl}/properties`, property);
  }

  // Update property based on its ID
  updateProperty(propertyId: number, property: Property): Observable<Property> {
    return this.http.put<Property>(`${this.apiUrl}/properties/${propertyId}`, property);
  }

  // Delete property (Handling logical deletion)
  deleteProperty(propertyId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/properties/${propertyId}`); // Using DELETE request properly
  }

}