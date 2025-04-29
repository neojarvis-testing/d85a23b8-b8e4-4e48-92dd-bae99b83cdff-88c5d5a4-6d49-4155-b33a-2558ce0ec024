import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PropertyInquiry } from 'src/app/models/property-inquiry.model';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';

@Component({
  selector: 'app-admin-view-inquiry',
  templateUrl: './admin-view-inquiry.component.html',
  styleUrls: ['./admin-view-inquiry.component.css']
})
export class AdminViewInquiryComponent implements OnInit {

  constructor(private service:PropertyInquiryService,private router:Router) { }
  inquiries:PropertyInquiry[]=[]
  ngOnInit(): void {
   this.service.getAllPropertyInquiry().subscribe((data)=>{
    this.inquiries=data
   })
  }
   
  deleteInquiry(inquiryId:number){
    this.service.deletePropertyInquiryById(inquiryId).subscribe((data)=>{
      alert("deleted successfully!")
    })
  }

  updateInquiry(id:number){
    console.log(id)
    this.router.navigate(['/editInquiry',id])
  }
}
