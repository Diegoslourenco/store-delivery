import { Component, OnInit } from '@angular/core';
import { Estoque } from 'src/app/_models/estoque';
import { EstoqueService } from 'src/app/_services/estoque.service';

@Component({
    selector: 'app-estoque',
    templateUrl: './estoque.component.html',
})
export class EstoqueComponent implements OnInit {

    constructor(private estoqueService: EstoqueService) { }

    public estoques: Estoque[] = [];

    ngOnInit(): void {

        this.estoqueService.getEstoques()
            .subscribe(
                response => {
                    this.estoques = response._embedded.estoques;
                },

                error => {
                    console.log(error);
                }
            );
    }

}
