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

  form: FormGroup

  constructor(private service: AuthService, private fb: FormBuilder, private router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    })
  }

  onSubmit() {
    if (this.form.valid) {
      this.service.login(this.form.value).subscribe((response: any) => {
        if (response.token) {
          // Store the token securely (e.g., in local storage or a cookie)
          localStorage.setItem('token', response.token);
          localStorage.setItem('username', response.username);
          localStorage.setItem('userRole', response.userRole);
          localStorage.setItem('userId', response.userId);
          alert("Login successful");
          if (response.userRole === 'ADMIN')
            this.router.navigate(['/adminnav'])
          else if (response.userRole === 'USER')
            this.router.navigate(['/usernav'])
        }
      }, (error) => {
        alert("Login failed");
      });
    } else {
      alert("Invalid user input");
    }
  }

  ngOnInit(): void {
  }

}