import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Property } from '../models/property.model';
import { Observable } from 'rxjs';
import { apiUrl } from '../constant/ApiUrl';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  /*
    Stores the base API URL for property-related requests.
  */
  apiUrl: any = apiUrl;

  /*
    Constructor injects the `HttpClient` service for making API requests.
  */
  constructor(private readonly http: HttpClient) {}

  /*
    Fetches all available properties from the server.
    Returns an observable containing a list of properties.
  */
  getAllProperties(): Observable<Property[]> {
    return this.http.get<Property[]>(`${this.apiUrl}/properties`);
  }

  /*
    Fetches a specific property by its ID.
    Returns an observable containing the property details.
  */
  getPropertyById(propertyId: number): Observable<Property> {
    return this.http.get<Property>(`${this.apiUrl}/properties/${propertyId}`);
  }

  /*
    Adds a new property.
    Sends property data to the API and returns an observable containing the created property.
  */
  addProperty(property: Property): Observable<Property> {
    return this.http.post<Property>(`${this.apiUrl}/properties`, property);
  }

  /*
    Updates an existing property by its ID.
    Sends updated property data to the API and returns an observable containing the modified property.
  */
  updateProperty(propertyId: number, property: Property): Observable<Property> {
    return this.http.put<Property>(`${this.apiUrl}/properties/${propertyId}`, property);
  }

  /*
    Deletes a property by its ID.
    Sends a delete request to the API and returns an observable confirming deletion.
  */
  deleteProperty(propertyId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/properties/${propertyId}`); 
  }

}
