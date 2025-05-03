import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../models/login.model';
import { User } from '../models/user.model';
import { apiUrl } from '../constant/ApiUrl';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  /*
    Stores the base API URL for authentication-related requests.
  */
  apiUrl: any = apiUrl;

  /*
    Sends login credentials to the API and returns an observable containing the login response.
  */
  login(login: Login): Observable<Login> {
    return this.http.post<Login>(`${this.apiUrl}/login`, login);
  }

  /*
    Registers a new user by sending user details to the API.
    Returns an observable containing the registered user details.
  */
  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/register`, user);
  }

  /*
    Logs out the user by removing authentication details from localStorage.
  */
  loggedOut(): void {
    localStorage.removeItem("userId");
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
    localStorage.removeItem("username");
  }

  /*
    Checks if a user is logged in based on the presence of a role in localStorage.
  */
  isLoggedUser(): boolean {
    let role = localStorage.getItem("userRole");
    return role != null;
  }

  /*
    Retrieves user details by user ID from the API.
    Returns an observable containing the user data.
  */
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${apiUrl}/user/${id}`);
  }

  /*
    Gets the logged-in user's role from localStorage.
  */
  getRole(): string | null {
    return localStorage.getItem('userRole');
  }

  /*
    Gets the logged-in user's username from localStorage.
  */
  getUsername(): string | null {
    return localStorage.getItem('username');
  }

  /*
    Checks if the logged-in user has an admin role.
  */
  isAdmin(): boolean {
    let role = localStorage.getItem("userRole");
    return role == 'ADMIN';
  }

  /*
    Checks if the logged-in user has a regular user role.
  */
  isUser(): boolean {
    let role = localStorage.getItem("userRole");
    return role == 'USER';
  }

  /*
    Constructor injects the `HttpClient` service for making API requests.
  */
  constructor(private readonly http: HttpClient) { }
}
