import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

const FORNECEDORES_ENDPOINT = "http://localhost:8080/api/fornecedores";

@Injectable({
    providedIn: 'root'
})
export class FornecedorService {

    constructor(private http: HttpClient) { }

    getFornecedores() : Observable<any> {
        return this.http
            .get<any>(FORNECEDORES_ENDPOINT);
    }
    
};