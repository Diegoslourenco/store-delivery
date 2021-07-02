import { Component, OnInit } from '@angular/core';
import { ItemVenda } from 'src/app/_models/item-venda';

import { Produto } from 'src/app/_models/produto';
import { ProdutoService } from 'src/app/_services/produtos.service';

@Component({
    selector: 'app-produtos',
    templateUrl: './produto.component.html'
})
export class ProdutoComponent implements OnInit {

    constructor(private produtoService: ProdutoService) { }

    public produtos: Produto[] = [];
    public itens: ItemVenda[] = [];

    ngOnInit() : void {

      this.produtoService.getProdutos()
        .subscribe(
            response => {
                this.produtos = response._embedded.produtos;
                console.log(response);
            },

            error => {
                console.log(error);
            }
        );
    }

    addProduto(produto: Produto) {

        if (this.itens.some(item => item.produto.id === produto.id)) {
            var index = this.itens.findIndex(item => item.produto.id ===    produto.id);
            this.itens[index].quantity += 1;
        }
        else {
            
            let newProduto = new Produto(produto.id, produto.name, produto.description, produto.unit);
            
            let itemVenda = new ItemVenda(newProduto, 1);

            this.itens.push(itemVenda);
        }  
    }

    submitCompra(itens: ItemVenda[]) {
        this.produtoService.createVenda(itens);
        this.limpaCarrinho();
    }

    limpaCarrinho() {
        this.itens = [];
    }

}
