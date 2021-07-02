import { Component, OnInit } from '@angular/core';
import { Fornecedor } from 'src/app/_models/fornecedor';
import { FornecedorService } from 'src/app/_services/fornecedor.service';

@Component({
    selector: 'app-fornecedor',
    templateUrl: './fornecedor.component.html',
})
export class FornecedorComponent implements OnInit {

    constructor(private fornecedorService: FornecedorService) { }

    public fornecedores: Fornecedor[] = []

    ngOnInit(): void {

        this.fornecedorService.getFornecedores()
            .subscribe(
                response => {
                    this.fornecedores = response._embedded.fornecedores;
                },

                error => {
                    console.log(error);
                }
            );
    }

}
