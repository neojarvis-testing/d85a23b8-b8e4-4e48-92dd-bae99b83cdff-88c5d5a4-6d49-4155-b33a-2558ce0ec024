import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {
  
  constructor(
    public readonly service: AuthService,
    private readonly router: Router,
    private readonly cdRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {}

  logout(): void {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will be logged out!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#28a745', // Green
      cancelButtonColor: '#dc3545', // Red
      confirmButtonText: 'Yes, Logout!',
      cancelButtonText: 'No, Stay!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.loggedOut(); // Clears authentication state
        localStorage.removeItem('userToken'); // Remove token if stored in localStorage
        this.router.navigate(['/login']); // Redirect to login
        this.cdRef.detectChanges(); // Force UI refresh
      }
    });
  }
}
