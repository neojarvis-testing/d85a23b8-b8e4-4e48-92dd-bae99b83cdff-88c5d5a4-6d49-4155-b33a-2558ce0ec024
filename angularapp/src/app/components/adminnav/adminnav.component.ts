import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {
 
  ngOnInit(): void {
    throw new Error("notImplemented() cannot be performed because ...");
  }
 


  constructor(
    public readonly service: AuthService,
    private readonly router: Router,
    private readonly cdRef: ChangeDetectorRef
  ) {}



  logout(): void {
    this.service.loggedOut();  // Clears authentication state
    localStorage.removeItem('userToken'); // Remove token if stored in localStorage
    this.router.navigate(['/login']);
    this.cdRef.detectChanges(); // Force UI refresh
  }

}
 
 