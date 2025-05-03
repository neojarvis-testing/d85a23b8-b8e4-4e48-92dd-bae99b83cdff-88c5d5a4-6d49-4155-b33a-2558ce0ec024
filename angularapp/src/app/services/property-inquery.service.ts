import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PropertyInquiry } from '../models/property-inquiry.model';
import { apiUrl } from '../constant/ApiUrl';

@Injectable({
  providedIn: 'root'
})
export class PropertyInquiryService {

  /*
    Constructor injects the HttpClient service for making API requests.
  */
  constructor(private readonly http: HttpClient) {}

  /*
    Adds a new property inquiry.
    Sends inquiry data to the API and returns an observable containing the created inquiry.
  */
  addPropertyInquiry(propertyInquiry: PropertyInquiry): Observable<PropertyInquiry> {
    return this.http.post<PropertyInquiry>(`${apiUrl}/inquiries`, propertyInquiry);
  }

  /*
    Retrieves all property inquiries.
    Returns an observable containing a list of inquiries fetched from the API.
  */
  getAllPropertyInquiry(): Observable<PropertyInquiry[]> {
    return this.http.get<PropertyInquiry[]>(`${apiUrl}/inquiries`);
  }

  /*
    Fetches a specific property inquiry by its ID.
    Returns an observable containing the inquiry details.
  */
  getPropertyInquiryById(inquiryId: number): Observable<PropertyInquiry> {
    return this.http.get<PropertyInquiry>(`${apiUrl}/inquiries/${inquiryId}`);
  }

  /*
    Updates an existing property inquiry by its ID.
    Sends updated inquiry data to the API and returns an observable containing the modified inquiry.
  */
  editPropertyInquiryById(inquiryId: number, propertyInquiry: PropertyInquiry): Observable<PropertyInquiry> {
    console.log(inquiryId);
    console.log(propertyInquiry);
    return this.http.put<PropertyInquiry>(`${apiUrl}/inquiries/${inquiryId}`, propertyInquiry);
  }

  /*
    Deletes a property inquiry by its ID.
    Sends a delete request to the API and returns an observable to confirm deletion.
  */
  deletePropertyInquiryById(inquiryId: number): Observable<void> {
    return this.http.delete<void>(`${apiUrl}/inquiries/${inquiryId}`);
  }

  /*
    Retrieves all inquiries submitted by a specific user.
    Returns an observable containing the list of inquiries filtered by user ID.
  */
  getInquiryByUserId(userId: number): Observable<PropertyInquiry[]> {
    return this.http.get<PropertyInquiry[]>(`${apiUrl}/inquiries/user/${userId}`);
  }

}
