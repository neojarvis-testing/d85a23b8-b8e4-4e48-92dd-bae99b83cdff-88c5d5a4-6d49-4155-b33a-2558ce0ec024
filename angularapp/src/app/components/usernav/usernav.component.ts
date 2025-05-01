import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-usernav',
  templateUrl: './usernav.component.html',
  styleUrls: ['./usernav.component.css']
})
export class UsernavComponent implements OnInit {
logout() {
  this.service.loggedOut();
  this.router.navigate(["/login"])

}

  constructor(private readonly router:Router,public readonly service:AuthService) { }

  ngOnInit(): void {
    throw new Error("not implemented!")
  }

}
