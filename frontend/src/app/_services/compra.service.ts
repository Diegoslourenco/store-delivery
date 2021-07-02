import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

const COMPRAS_ENDPOINT = "http://localhost:8080/api/compras";

@Injectable({
    providedIn: 'root'
})
export class CompraService {

    constructor(private http: HttpClient) { }

    getCompras() : Observable<any> {
        return this.http
            .get<any>(COMPRAS_ENDPOINT);
    }
    
};
