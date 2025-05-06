import { Component, OnInit } from '@angular/core';
import { FeedbackService } from 'src/app/services/feedback.service';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';
import { PropertyService } from 'src/app/services/property.service';

@Component({
  selector: 'app-admin-control-panel',
  templateUrl: './admin-control-panel.component.html',
  styleUrls: ['./admin-control-panel.component.css']
})
export class AdminControlPanelComponent implements OnInit {

  /*
    Arrays to store inquiries, feedbacks, and properties
    along with counters to keep track of their counts.
  */
  inquiries: any[] = [];
  allInquiries = this.inquiries.length;
  
  totalFeedbacks: any[] = [];
  allFeedbacks = this.totalFeedbacks.length;
  
  totalProperties: any[] = [];
  allProperties = this.totalProperties.length;
  
  unresolvedInquiries: any[] = [];
  allUnresolvedInquiries = this.unresolvedInquiries.length;
  
  highPriorityInquiries: any[] = [];
  allHighPriorityInquiries = this.highPriorityInquiries.length;

  /*
    Fetch all property inquiries and update the count.
  */
  getAllPropertyInquiries() {
    this.service.getAllPropertyInquiry().subscribe((data) => {
      this.inquiries = data;
      this.allInquiries = this.inquiries.length;
    });
  }

  /*
    Fetch all feedbacks and update the count.
  */
  getAllFeedbacks() {
    this.feedbackService.getFeedbacks().subscribe((data) => {
      this.totalFeedbacks = data;
      this.allFeedbacks = this.totalFeedbacks.length;
    });
  }

  /*
    Filter inquiries to get only high-priority ones.
  */
  getHighPriorityInquiries() {
    this.service.getAllPropertyInquiry().subscribe((data)=>{
     this.highPriorityInquiries = this.inquiries.filter((i) => i.priority === "High");
    this.allHighPriorityInquiries = this.highPriorityInquiries.length;
    })
   
  }

  /*
    Filter inquiries to get only unresolved ones.
  */
  getUnresolvedInquiries() {
    this.unresolvedInquiries = this.inquiries.filter((i) => i.status === "Pending");
    this.allUnresolvedInquiries = this.unresolvedInquiries.length;
  }

  /*
    Fetch all properties and update the count.
  */
  getAllProperties() {
    this.propertyService.getAllProperties().subscribe((data) => {
      this.totalProperties = data;
      this.allProperties = this.totalProperties.length;
    });
  }

  /*
    Constructor to inject required services for fetching data.
  */
  constructor(
    private readonly service: PropertyInquiryService,
    private readonly feedbackService: FeedbackService,
    private readonly propertyService: PropertyService
  ) {}

  /*
    On component initialization, fetch all necessary data.
  */
  ngOnInit(): void {
    this.getAllPropertyInquiries();
    this.getAllFeedbacks();
    this.getAllProperties();
    this.getHighPriorityInquiries();
    this.getUnresolvedInquiries();
  }

}
