import { Produto } from "./produto";

export class ItemVenda {
    produto: Produto;
    quantity: number;

    constructor(produto: Produto, quantity: number) {
        this.produto = produto;
        this.quantity = quantity;
    }
}
