import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

const ESTOQUES_ENDPOINT = "http://localhost:8080/api/estoques";

@Injectable({
    providedIn: 'root'
})
export class EstoqueService {

    constructor(private http: HttpClient) { }

    getEstoques() : Observable<any> {
        return this.http
            .get<any>(ESTOQUES_ENDPOINT);
    }
    
};
