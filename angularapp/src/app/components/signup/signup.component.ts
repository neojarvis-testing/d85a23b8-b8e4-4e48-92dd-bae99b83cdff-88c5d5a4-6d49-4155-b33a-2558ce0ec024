import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  /*
    Form group for managing user signup data.
    Includes validation rules for required fields, email format, password strength, and phone number format.
  */
  form: FormGroup;

  /*
    Controls the visibility of the success popup upon registration.
  */
  showPopup = false;

  /*
    Constructor injects necessary services:
    - `AuthService` for handling user registration API requests.
    - `FormBuilder` for creating and managing the signup form.
    - `Router` for navigating to different routes after registration.
  */
  constructor(private readonly service: AuthService, private readonly fb: FormBuilder, private readonly router: Router) {
    this.form = this.fb.group(
      {
        email: ['', [Validators.required, Validators.email]], // Ensures valid email format
        password: ['', [Validators.required,Validators.pattern("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,}$")]], // Password must be at least 6 characters long
        confirmPassword: ['', [Validators.required]], // Confirms password input
        username: ['', [Validators.required, Validators.minLength(3)]], // Username must be at least 3 characters
        number: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]], // Validates phone number format (10 digits)
        userRole: ['USER', [Validators.required]] // Assigns a default role of "USER"
      },
      { validators: this.passwordMatchValidator }
    );
  }

  /*
    Custom validator to check if the password and confirm password fields match.
    Returns an error object if they don't.
  */
  passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    return password && confirmPassword && password !== confirmPassword
      ? { passwordMismatch: true }
      : null;
  }

  /*
    Handles the signup form submission.
    Validates user input before sending registration data to the authentication service.
    Displays a success popup upon successful registration.
    Shows an alert if registration fails.
  */
  onSubmit(): void {
    if (this.form.valid) {
      this.service.register(this.form.value).subscribe(() => {
        this.showPopup = true; // Show popup upon successful registration
      }, () => {
        alert("Registration failed. Please try again.");
      });
    } else {
      alert("Invalid user input");
    }
  }

  /*
    Closes the success popup and navigates to the login page.
  */
  closePopup(): void {
    this.showPopup = false;
    this.router.navigate(['/login']);
  }

  /*
    Lifecycle hook executed when the component initializes.
    This version contains an intentional error to indicate a missing implementation.
  */
  ngOnInit(): void {
    throw new Error("notImplemented() cannot be performed because ...");
  }
}
