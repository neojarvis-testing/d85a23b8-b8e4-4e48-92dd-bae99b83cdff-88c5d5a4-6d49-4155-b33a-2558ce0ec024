import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';

declare let bootstrap: any;

@Component({
  selector: 'app-user-add-inquiry',
  templateUrl: './user-add-inquiry.component.html',
  styleUrls: ['./user-add-inquiry.component.css']
})
export class UserAddInquiryComponent implements OnInit {
  inquiryForm: FormGroup;
  propertyId: number;

  constructor(
    private readonly service: PropertyInquiryService,
    private readonly fb: FormBuilder,
    private readonly activatedRoute: ActivatedRoute,
    private readonly router: Router
  ) {
    this.inquiryForm = this.fb.group({
      message: [''],
      status: [''],
      inquiryDate: [''],
      responseDate: [''],
      adminResponse: [''],
      priority: [''],
      contactDetails: ['']
    });
  }

  ngOnInit(): void {
    this.propertyId = this.activatedRoute.snapshot.params['id'];
  }

  addInquiry(): void {
    this.inquiryForm.value.userId = +localStorage.getItem('userId');
    this.inquiryForm.value.propertyId = +this.propertyId;
    this.inquiryForm.value.status = 'Pending';
    this.inquiryForm.value.inquiryDate = new Date();

    console.log(this.inquiryForm.value);

    this.service.addPropertyInquiry(this.inquiryForm.value).subscribe(() => {
      this.showPopup();
    });
  }

  showPopup(): void {
    let modal = new bootstrap.Modal(document.getElementById('successModal'));
    modal.show();
  }

  navigateToInquiries(): void {
    this.router.navigate(['/myinquiries']);
  }
}
