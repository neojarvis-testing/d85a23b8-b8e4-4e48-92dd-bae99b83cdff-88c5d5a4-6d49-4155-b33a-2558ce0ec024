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

  login(login:Login):Observable<Login>{
    return this.http.post<Login>(`${apiUrl}/login`,login);
  }
  register(user:User):Observable<User>{
    return this.http.post<User>(`${apiUrl}/register`,user);
  }
  constructor(private http:HttpClient) { }
}
