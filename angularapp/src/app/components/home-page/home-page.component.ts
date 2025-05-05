import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  isLoading: boolean = true;
  constructor() { }

  ngOnInit(): void {
    setTimeout(() => {
      this.isLoading = false; // Hide loader after content loads
    }, 2000);
  }
}