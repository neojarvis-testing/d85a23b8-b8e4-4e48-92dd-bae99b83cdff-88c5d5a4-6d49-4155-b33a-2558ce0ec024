import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PropertyInquiry } from 'src/app/models/property-inquiry.model';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';

@Component({
  selector: 'app-my-inquiry',
  templateUrl: './my-inquiry.component.html',
  styleUrls: ['./my-inquiry.component.css']
})


export class MyInquiryComponent implements OnInit { 


  inquiries: PropertyInquiry[] = [];
  userId:number
  constructor(private readonly service:PropertyInquiryService) {}

  ngOnInit(): void {
    this.loadInquiries();
  }

  loadInquiries(): void {
    // Fetch the user's inquiries from the service
    this.userId = +localStorage.getItem('userId')
    this.service.getInquiryByUserId(this.userId).subscribe(
      (data: PropertyInquiry[]) => {
        this.inquiries = data;
      },
      (error) => {
        console.error('Error fetching inquiries', error);
      }
    );
    }
  }
