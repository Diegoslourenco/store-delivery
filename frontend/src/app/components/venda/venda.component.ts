import { Component, OnInit } from '@angular/core';
import { Venda } from 'src/app/_models/venda';
import { VendaService } from 'src/app/_services/venda.service';

@Component({
    selector: 'app-venda',
    templateUrl: './venda.component.html',
})
export class VendaComponent implements OnInit {

    constructor(private vendaService: VendaService) { }

    public vendas: Venda[] = [];

    ngOnInit(): void {

        this.vendaService.getVendas()
            .subscribe(
                response => {
                    this.vendas = response._embedded.vendas;
                },

                error => {
                    console.log(error);
                }
            );
    }

}
