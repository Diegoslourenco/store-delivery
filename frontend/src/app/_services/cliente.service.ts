import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Cliente } from "../_models/cliente";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const CLIENTES_ENDPOINT = "http://localhost:8080/api/clientes";

@Injectable({
    providedIn: 'root'
})
export class ClienteService {

    constructor(private http: HttpClient) { }

    getClientes() : Observable<any> {
        return this.http
            .get<any>(CLIENTES_ENDPOINT);
    }

    register(user: Cliente): Observable<any> {
        return this.http.post(CLIENTES_ENDPOINT, {
            cpf: user.cpf,
            name: user.name,
            phone: user.phone,
            email: user.email,
            password: user.password,
            address: {
                street: user.street,
                number: user.number,
                complement: user.complement,
                district: user.district,
                cep: user.cep,
                city: user.city,
                state: user.state 
            }               
        }, httpOptions);
    }
    
};