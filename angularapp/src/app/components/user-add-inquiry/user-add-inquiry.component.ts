import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';

@Component({
  selector: 'app-user-add-inquiry',
  templateUrl: './user-add-inquiry.component.html',
  styleUrls: ['./user-add-inquiry.component.css']
})
export class UserAddInquiryComponent implements OnInit {
  inquiryForm:FormGroup
  userId:number
  propertyId:number
  constructor(private readonly service:PropertyInquiryService,private readonly fb:FormBuilder,private readonly activatedRoute:ActivatedRoute,private readonly router:Router) { 
  this.inquiryForm = fb.group({
  message:[''],
  status:[''],
  inquiryDate:[''],
  responseDate:[''],
  adminResponse:[''],
  priority:[''],
  contactDetails:['']
  })

  }

  ngOnInit(): void {
  this.propertyId= this.activatedRoute.snapshot.params['id']
  }
  addInquiry(){
    this.inquiryForm.value.userId = localStorage.getItem('userId')
    this.inquiryForm.value.propertyId = this.propertyId
    this.inquiryForm.value.status = 'Pending'
    this.inquiryForm.value.inquiryDate = new Date()
    console.log(this.inquiryForm.value)
    this.service.addPropertyInquiry(this.inquiryForm.value).subscribe((data)=>{
      alert("Inquiry posted successfully!")
      this.router.navigate(['/user-view-property'])
    })
  }
}
