import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form:FormGroup

  constructor(private service:AuthService,private fb:FormBuilder) { 
    this.form=this.fb.group({
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required]]
    })
  }

  onSubmit(){
    if (this.form.valid) {
      this.service.login(this.form.value).subscribe((response: any) => {
        if (response.token) {
          // Store the token securely (e.g., in local storage or a cookie)
          localStorage.setItem('token', response.token);
          alert("Login successful");
          // Navigate to a different route if needed
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