import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Property } from '../models/property.model';
import { Observable } from 'rxjs';
import { ApiUrl } from '../constant/ApiUrl';
@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  apiUrl: string = ApiUrl.apiUrl;

  constructor(private http: HttpClient) {}

  getAllProperties(): Observable<Property[]> {
    return this.http.get<Property[]>(`${this.apiUrl}/properties`);
  }

  getPropertyById(propertyId: number): Observable<Property> {
    return this.http.get<Property>(`${this.apiUrl}/properties/${propertyId}`);
  }

  addProperty(property: Property): Observable<Property> {
    return this.http.post<Property>(`${this.apiUrl}/properties`, property);
  }

  updateProperty(propertyId: number, property: Property): Observable<Property> {
    return this.http.put<Property>(`${this.apiUrl}/properties/${propertyId}`, property);
  }

  deleteProperty(propertyId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/properties/${propertyId}`);
  }

}
