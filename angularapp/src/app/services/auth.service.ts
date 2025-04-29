import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../models/login.model';
import { ApiUrl } from '../constant/ApiUrl';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl:string = 'https://8080-aacbecbaefbdfedbeebaccddfebdfacdadbaceec.premiumproject.examly.io/api'

  login(login:Login):Observable<Login>{
    return this.http.post<Login>(`${this.apiUrl}/login`,login);
  }
  register(user:User):Observable<User>{
    return this.http.post<User>(`${this.apiUrl}/register`,user);
  }
  constructor(private http:HttpClient) { }
}
