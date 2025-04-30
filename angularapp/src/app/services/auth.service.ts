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
 
  apiUrl:any=apiUrl
 
  login(login:Login):Observable<Login>{
    return this.http.post<Login>(`${this.apiUrl}/login`,login);
  }
  register(user:User):Observable<User>{
    return this.http.post<User>(`${this.apiUrl}/register`,user);
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
  getUserById(id:number){
    this.http.get(`${apiUrl}/user/${id}`)
  }

  isAdmin():boolean{
   let role = localStorage.getItem("userRole")
   return role=='ADMIN'
  }

  isUser():boolean{
    let role = localStorage.getItem("userRole")
    return role=='USER'
   }
  constructor(private http:HttpClient) { }
}
 
 