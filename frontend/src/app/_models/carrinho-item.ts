import { Produto } from './produto';

export class CarrinhoItem {
    id: number;
    produtoId: number;
    produtoName: string;
    quantidade: number;

    constructor(id: number, produto: Produto, quantidade = 1) {
        this.id = id;
        this.produtoId = produto.id;
        this.produtoName = produto.name;
        this.quantidade = quantidade;
    }

}
