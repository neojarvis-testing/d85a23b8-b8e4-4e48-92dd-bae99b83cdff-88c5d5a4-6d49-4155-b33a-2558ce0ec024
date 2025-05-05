import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(private readonly service: AuthService, private readonly fb: FormBuilder, private readonly router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]], 
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.service.login(this.form.value).subscribe((response: any) => {
        if (response.token) {
          localStorage.setItem('token', response.token);
          localStorage.setItem('username', response.username);
          localStorage.setItem('userRole', response.userRole);
          localStorage.setItem('userId', response.userId);

          Swal.fire({
            title: 'Login Successful!',
            text: 'Welcome back!',
            icon: 'success',
            confirmButtonColor: '#ffcc00'
          }).then(() => {
            this.router.navigate(['/']);
          });
        }
      }, () => {
        Swal.fire({
          title: 'Login Failed',
          text: 'Invalid credentials, please try again.',
          icon: 'error',
          confirmButtonColor: '#dc3545'
        });
      });
    } else {
      Swal.fire({
        title: 'Invalid Input',
        text: 'Please enter valid credentials.',
        icon: 'warning',
        confirmButtonColor: '#ffcc00'
      });
    }
  }

  ngOnInit(): void {}
}
