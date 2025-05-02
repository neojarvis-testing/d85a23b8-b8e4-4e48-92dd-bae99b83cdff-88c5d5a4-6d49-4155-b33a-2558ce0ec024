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

  form: FormGroup;
  showPopup = false; // Controls popup visibility

  constructor(private readonly service: AuthService, private readonly fb: FormBuilder, private readonly router: Router) {
    this.form = this.fb.group(
      {
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', [Validators.required]],
        username: ['', [Validators.required, Validators.minLength(3)]],
        number: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
        userRole: ['USER', [Validators.required]]
      },
      { validators: this.passwordMatchValidator }
    );
  }

  // Custom validator for password matching
  passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    return password && confirmPassword && password !== confirmPassword
      ? { passwordMismatch: true }
      : null;
  }

  onSubmit() {
    if (this.form.valid) {
      this.service.register(this.form.value).subscribe(() => {
        this.showPopup = true; // Show popup on successful registration
      }, () => {
        alert("Registration failed. Please try again.");
      });
    } else {
      alert("Invalid user input");
    }
  }

  closePopup() {
    this.showPopup = false;
    this.router.navigate(['/login']); // Navigate to login page after closing popup
  }

  ngOnInit(): void {
    throw new Error("notImplemented() cannot be performed because ...");

  }
}
