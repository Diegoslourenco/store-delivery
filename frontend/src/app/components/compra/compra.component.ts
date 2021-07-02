import { Component, OnInit } from '@angular/core';
import { Compra } from 'src/app/_models/compra';
import { CompraService } from 'src/app/_services/compra.service';

@Component({
    selector: 'app-compra',
    templateUrl: './compra.component.html',
})
export class CompraComponent implements OnInit {

    constructor(private compraService: CompraService) { }

    public compras: Compra[] = [];

    ngOnInit(): void {

        this.compraService.getCompras()
            .subscribe(
                response => {
                    this.compras = response._embedded.compras;
                },

                error => {
                    console.log(error);
                }
            );
    }

}
