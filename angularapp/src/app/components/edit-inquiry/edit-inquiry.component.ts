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

  /*
    Form group that holds inquiry details for editing.
    Includes validation rules for required fields.
  */
  form: FormGroup;

  /*
    Stores inquiry ID fetched from route parameters.
  */
  inquiryId: number;

  /*
    Stores user and property IDs (currently hardcoded for simplicity).
  */
  userId: number;
  propertyId: number;

  /*
    Constructor injects necessary services:
    - `PropertyInquiryService` for API interactions related to inquiries.
    - `FormBuilder` for creating and managing inquiry form.
    - `ActivatedRoute` to retrieve route parameters.
  */
  constructor(
    private readonly service: PropertyInquiryService,
    private readonly fb: FormBuilder,
    private readonly activatedRoute: ActivatedRoute
  ) { 
    this.form = fb.group({
      message: ['', [Validators.required]],
      status: ['', [Validators.required]],
      inquiryDate: ['', [Validators.required]],
      responseDate: ['', [Validators.required]],
      adminResponse: ['', [Validators.required]],
      priority: ['', [Validators.required]],
      contactDetails: ['', [Validators.required]]
    });
  }

  /*
    Lifecycle hook executed when the component initializes.
    Fetches inquiry ID from route parameters and loads inquiry details.
  */
  ngOnInit(): void {
    this.inquiryId = this.activatedRoute.snapshot.params['id'];
    this.service.getPropertyInquiryById(this.inquiryId).subscribe((data) => {
      this.form.patchValue({ ...data });
    });
  }

  /*
    Saves the updated inquiry details.
    Hardcodes `userId` and `propertyId` values for demonstration.
    Sends updated inquiry data to the server and displays an alert upon success.
  */
  save(): void {
    this.form.value.userId = 1;
    this.form.value.propertyId = 2;

    this.service.editPropertyInquiryById(this.inquiryId, this.form.value).subscribe(() => {
      alert("Edited inquiry!");
    });
  }
}
