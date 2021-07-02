import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/_models/cliente';
import { ClienteService } from 'src/app/_services/cliente.service';

@Component({
    selector: 'app-cliente',
    templateUrl: './cliente.component.html',
})
export class ClienteComponent implements OnInit {

    constructor(private clienteService: ClienteService) { }

    public clientes: Cliente[] = [];

    ngOnInit(): void {

        this.clienteService.getClientes()
            .subscribe(
                response => {
                    this.clientes = response._embedded.clientes;
                },

                error => {
                    console.log(error);
                }
            );
    }

}
