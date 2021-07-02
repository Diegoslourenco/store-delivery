import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

const VENDAS_ENDPOINT = "http://localhost:8080/api/vendas";

@Injectable({
    providedIn: 'root'
})
export class VendaService {

    constructor(private http: HttpClient) { }

    getVendas() : Observable<any> {
        return this.http
            .get<any>(VENDAS_ENDPOINT);
    }
    
};
