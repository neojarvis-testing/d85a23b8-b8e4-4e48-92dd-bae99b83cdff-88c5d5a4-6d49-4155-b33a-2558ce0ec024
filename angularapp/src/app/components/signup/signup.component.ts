import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  form: FormGroup
  constructor(private service: AuthService, private fb: FormBuilder,) {
    this.form = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
      username: ['', [Validators.required]],
      number: ['', [Validators.required]],
      userRole: ['USER', [Validators.required]]
    })
  }

  onSubmit() {
    if (this.form.valid) {
      this.service.register(this.form.value).subscribe(() => {
        alert("Registration successful");
        // this.router.navigate(['/login'])
      }, (error) => {
        alert("Registration failed")
      })
    }
    else {
      alert("Invalid user Input")
    }
  }

  ngOnInit(): void {
  }
  
}


