import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  form: FormGroup;

  constructor(private service: AuthService, private fb: FormBuilder) {
    this.form = this.fb.group(
      {
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required]],
        confirmPassword: ['', [Validators.required]],
        username: ['', [Validators.required]],
        number: ['', [Validators.required, Validators.pattern('^[0-9]*$')]], // Allows only numeric values
        userRole: ['USER', [Validators.required]]
      },
      { validators: this.passwordMatchValidator } // Custom validator for password matching
    );
  }

  // Custom validator for matching passwords
  passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    return password && confirmPassword && password !== confirmPassword
      ? { passwordMismatch: true }
      : null;
  }

  onSubmit() {
    if (this.form.valid) {
      this.service.register(this.form.value).subscribe(
        () => {
          alert("Registration successful");
          // Navigate to login or another page if needed
          // this.router.navigate(['/login']);
        },
        (error) => {
          alert("Registration failed");
        }
      );
    } else {
      alert("Invalid user input");
    }
  }

  ngOnInit(): void { }
}
