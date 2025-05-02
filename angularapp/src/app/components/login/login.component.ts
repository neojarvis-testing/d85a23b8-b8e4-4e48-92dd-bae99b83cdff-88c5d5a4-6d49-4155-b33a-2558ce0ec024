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
  
  form: FormGroup;
  showPopup: boolean = false; // Tracks whether popup is visible

  constructor(private readonly service: AuthService, private readonly fb: FormBuilder, private readonly router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Email validation added
      password: ['', [Validators.required, Validators.minLength(6)]] // Password must be at least 6 characters
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.service.login(this.form.value).subscribe((response: any) => {
        if (response.token) {
          // Store the token securely
          localStorage.setItem('token', response.token);
          localStorage.setItem('username', response.username);
          localStorage.setItem('userRole', response.userRole);
          localStorage.setItem('userId', response.userId);

          this.showPopup = true; // Show popup on successful login
        }
      }, () => {
        alert("Login failed. Please check your credentials.");
      });
    } else {
      alert("Invalid user input");
    }
  }

  closePopup() {
    this.showPopup = false; // Close popup when "OK" button is clicked
    this.router.navigate(['/']); // Redirect to homepage after closing popup
  }

  ngOnInit(): void {
    throw new Error("notImplemented() cannot be performed because ...");

  }
}
