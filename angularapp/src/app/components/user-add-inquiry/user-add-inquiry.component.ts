import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'; // Import Router for navigation
import { PropertyInquiryService } from 'src/app/services/property-inquery.service';

declare let bootstrap: any; // Ensure Bootstrap JS is accessible globally

@Component({
  selector: 'app-user-add-inquiry',
  templateUrl: './user-add-inquiry.component.html',
  styleUrls: ['./user-add-inquiry.component.css']
})
export class UserAddInquiryComponent implements OnInit {

  /*
    Form group to handle inquiry submission.
    Includes fields for user input related to the inquiry.
  */
  inquiryForm: FormGroup;

  /*
    Stores the property ID retrieved from route parameters.
  */
  propertyId: number;

  /*
    Constructor injects necessary services:
    - `PropertyInquiryService` for handling API interactions.
    - `FormBuilder` for managing form creation and validation.
    - `ActivatedRoute` to fetch property ID from the route.
    - `Router` for navigation after inquiry submission.
  */
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

  /*
    Lifecycle hook executed when the component initializes.
    Retrieves the property ID from route parameters.
  */
  ngOnInit(): void {
    this.propertyId = this.activatedRoute.snapshot.params['id'];
  }

  /*
    Submits an inquiry related to a property.
    Retrieves the user ID from localStorage and assigns default values.
    Logs inquiry data and triggers popup upon successful submission.
  */
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

  /*
    Displays a success popup using Bootstrap modal functionality.
  */
  showPopup(): void {
    let modal = new bootstrap.Modal(document.getElementById('successModal'));
    modal.show();
  }

  /*
    Navigates to the user's inquiry page after submission.
  */
  navigateToInquiries(): void {
    this.router.navigate(['/myinquiries']);
  }
}
