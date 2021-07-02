import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { ItemVenda } from "../_models/item-venda";


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const PRODUTOS_ENDPOINT = "http://localhost:8080/api/produtos";
const VENDAS_ENDPOINT = "http://localhost:8080/api/vendas";

@Injectable({
    providedIn: 'root'
})
export class ProdutoService {

    constructor(private http: HttpClient) { }

    getProdutos() : Observable<any> {
        return this.http
            .get<any>(PRODUTOS_ENDPOINT);
    }

    createVenda(itens: ItemVenda[]) : void  {
        
        console.log({itens});

        this.http.post(VENDAS_ENDPOINT, {
            itens: itens 
        }, httpOptions)
            .subscribe(
                response => {
                    console.log(response);
                },
                error => {
                    console.log(error);
                }
            );
    }
    
};
