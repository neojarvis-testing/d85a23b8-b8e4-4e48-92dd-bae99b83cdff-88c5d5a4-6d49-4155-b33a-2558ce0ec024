import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PropertyInquiry } from '../models/property-inquiry.model';
import { apiUrl } from '../constant/app.constant';

@Injectable({
  providedIn: 'root'
})
export class PropertyInquiryService {

  constructor(private http:HttpClient) { }
  addPropertyInquiry(propertyInquiry:PropertyInquiry):Observable<PropertyInquiry>{
    return this.http.post<PropertyInquiry>(`${apiUrl}/inquiries`,propertyInquiry)
  }

  getAllPropertyInquiry():Observable<PropertyInquiry[]>{
    return this.http.get<PropertyInquiry[]>(`${apiUrl}/inquiries`)
  }

  getPropertyInquiryById(inquiryId:number):Observable<PropertyInquiry>{
    return this.http.get<PropertyInquiry>(`${apiUrl}/inquiries/${inquiryId}`)
  }

  editPropertyInquiryById(inquiryId:number,propertyInquiry:PropertyInquiry):Observable<PropertyInquiry>{
    console.log(inquiryId)
    console.log(propertyInquiry)
    return this.http.put<PropertyInquiry>(`${apiUrl}/inquiries/${inquiryId}`,propertyInquiry)
  }
  
  deletePropertyInquiryById(inquiryId:number):Observable<void>{
    return this.http.delete<void>(`${apiUrl}/inquiries/${inquiryId}`)
  }

}
