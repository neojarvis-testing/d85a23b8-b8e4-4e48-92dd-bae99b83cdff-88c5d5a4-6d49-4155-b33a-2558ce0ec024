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
  allFeedbacks:any[]=[]
  totalProperties:any[]=[]
  allProperties=this.totalProperties.length
  unresolvedInquiries:any[]=[]
  allUnresolvedInquiries=this.unresolvedInquiries.length
  highPriorityInquiries:any[]=[]
  allHighPriorityInquiries=this.highPriorityInquiries.length

  getAllPropertyInquiries(){
    this.service.getAllPropertyInquiry().subscribe((data)=>{
      this.inquiries=data;
    })
  }
  getAllFeedbacks(){
    this.feedbackService
  }
  
  getHighPriorityInquiries(){
    this.highPriorityInquiries=this.inquiries.filter((i)=>i.priority=="High")
  }

  getUnresolvedInquiries(){
    this.unresolvedInquiries=this.inquiries.filter((i)=>i.status=="Pending")
  }

  getAllProperties(){
    this.propertyService.getAllProperties().subscribe((data)=>{
      this.totalProperties=data;
    })
  }
  constructor(private service:PropertyInquiryService,private feedbackService:FeedbackService,private propertyService:PropertyService) { }

  ngOnInit(): void {
    this.getAllPropertyInquiries()
    this.getAllFeedbacks()
    this.getAllProperties()
  }

}
