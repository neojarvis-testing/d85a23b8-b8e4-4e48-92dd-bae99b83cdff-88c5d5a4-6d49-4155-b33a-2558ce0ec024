import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  /*
    Form group for managing login credentials.
    Includes validation rules for required fields, email format, and password length.
  */
  form: FormGroup;

  /*
    Tracks whether the success popup is visible.
  */
  showPopup: boolean = false;

  /*
    Constructor injects necessary services:
    - `AuthService` for handling authentication API requests.
    - `FormBuilder` to create and manage the login form.
    - `Router` to navigate between components.
  */
  constructor(private readonly service: AuthService, private readonly fb: FormBuilder, private readonly router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Ensures valid email format
      password: ['', [Validators.required, Validators.minLength(6)]] // Password must be at least 6 characters long
    });
  }

  /*
    Handles the login form submission.
    Validates user input before sending login credentials to the authentication service.
    Stores received authentication details securely in localStorage.
    Displays a success popup upon successful login.
    Shows an alert if login fails.
  */
  onSubmit(): void {
    if (this.form.valid) {
      this.service.login(this.form.value).subscribe((response: any) => {
        if (response.token) {
          // Securely store authentication details in localStorage
          localStorage.setItem('token', response.token);
          localStorage.setItem('username', response.username);
          localStorage.setItem('userRole', response.userRole);
          localStorage.setItem('userId', response.userId);

          this.showPopup = true; // Show success popup upon login
        }
      }, () => {
        alert("Login failed. Please check your credentials.");
      });
    } else {
      alert("Invalid user input");
    }
  }

  /*
    Closes the success popup and navigates to the homepage.
  */
  closePopup(): void {
    this.showPopup = false;
    this.router.navigate(['/']);
  }

  /*
    Lifecycle hook executed when the component initializes.
    This version contains an intentional error to indicate a missing implementation.
  */
  ngOnInit(): void {
    throw new Error("notImplemented() cannot be performed because ...");
  }

}
