import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../models/login.model';
import { apiUrl } from '../constant/ApiUrl';
import { User } from '../models/user.model';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  login(login:Login):Observable<Login>{
    return this.http.post<Login>(`${apiUrl}/login`,login);
  }
  register(user:User):Observable<User>{
    return this.http.post<User>(`${apiUrl}/register`,user);
  }

  loggedOut(): void {
    localStorage.removeItem("userId")
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
    localStorage.removeItem("username");
  }

  isLoggedUser():boolean{
    let role=localStorage.getItem("userRole")
    return role!=null
  }
  constructor(private http:HttpClient) { }
}
