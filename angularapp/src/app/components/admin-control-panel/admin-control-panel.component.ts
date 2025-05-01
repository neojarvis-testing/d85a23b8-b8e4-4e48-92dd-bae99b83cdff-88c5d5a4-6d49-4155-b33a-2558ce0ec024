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

  inquiries:any[]=[]

  allInquiries=this.inquiries.length
  totalFeedbacks:any[]=[]
  allFeedbacks=this.totalFeedbacks.length
  totalProperties:any[]=[]
  allProperties=this.totalProperties.length
  unresolvedInquiries:any[]=[]
  allUnresolvedInquiries=this.unresolvedInquiries.length
  highPriorityInquiries:any[]=[]
  allHighPriorityInquiries=this.highPriorityInquiries.length

  getAllPropertyInquiries(){
    this.service.getAllPropertyInquiry().subscribe((data)=>{
      this.inquiries=data;
      this.allInquiries=this.inquiries.length
    })
  }
  getAllFeedbacks(){
    this.feedbackService.getFeedbacks().subscribe((data)=>{
      this.totalFeedbacks=data
      this.allFeedbacks=this.totalFeedbacks.length
    })
  }
  
  getHighPriorityInquiries(){
    this.highPriorityInquiries=this.inquiries.filter((i)=>i.priority=="High")
    this.allHighPriorityInquiries=this.highPriorityInquiries.length
  }

  getUnresolvedInquiries(){
    this.unresolvedInquiries=this.inquiries.filter((i)=>i.status=="Pending")
    this.allUnresolvedInquiries = this.unresolvedInquiries.length;
  }

  getAllProperties(){
    this.propertyService.getAllProperties().subscribe((data)=>{
      this.totalProperties=data;
      this.allProperties=this.totalProperties.length
    })
  }
  constructor(private readonly service:PropertyInquiryService,private readonly feedbackService:FeedbackService,private readonly propertyService:PropertyService) { }

  ngOnInit(): void {
    this.getAllPropertyInquiries()
    this.getAllFeedbacks()
    this.getAllProperties()
  }

}
