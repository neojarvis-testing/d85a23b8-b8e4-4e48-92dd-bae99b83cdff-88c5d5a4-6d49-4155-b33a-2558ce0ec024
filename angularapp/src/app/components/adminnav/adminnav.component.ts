import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {
logout() {
  this.service.loggedOut();
  this.router.navigate(["/login"])

}

  constructor(public readonly service:AuthService,private readonly router:Router) { }

  ngOnInit(): void {
    throw new Error("notImplemented()")
  }

}
