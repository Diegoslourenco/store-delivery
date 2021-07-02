import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_ENDPOINT = 'http://localhost:8080/api/auth';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

    constructor(private http: HttpClient) { }

    login(credentials: { email: any; password: any; }): Observable<any> {
        return this.http.post(AUTH_ENDPOINT, {
            email: credentials.email,
            password: credentials.password
        }, httpOptions);
    }   

}
