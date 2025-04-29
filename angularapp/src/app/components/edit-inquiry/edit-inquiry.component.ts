import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';

@Component({
  selector: 'app-edit-inquiry',
  templateUrl: './edit-inquiry.component.html',
  styleUrls: ['./edit-inquiry.component.css']
})
export class EditInquiryComponent implements OnInit {

  inquiryForm:FormGroup
  inquiryId:number
  userId:number
  propertyId:number
  constructor(private service:PropertyInquiryService,private fb:FormBuilder,private activatedRoute:ActivatedRoute) { 
  this.inquiryForm = fb.group({
  message:['',[Validators.required]],
  status:['',[Validators.required]],
  inquiryDate:['',[Validators.required]],
  responseDate:['',[Validators.required]],
  adminResponse:['',[Validators.required]],
  priority:['',[Validators.required]],
  contactDetails:['',[Validators.required]]
  })

  }
  ngOnInit(): void {
    this.inquiryId = this.activatedRoute.snapshot.params['id']
    this.service.getPropertyInquiryById(this.inquiryId).subscribe((data)=>{
      this.inquiryForm.patchValue({...data})
    })
  }


  save(){
    this.inquiryForm.value.userId = 1
    this.inquiryForm.value.propertyId = 2
    this.service.editPropertyInquiryById(this.inquiryId,this.inquiryForm.value).subscribe((data)=>{
      alert("edited inquiry!")
    })
  }
}
