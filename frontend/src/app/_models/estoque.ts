import { Produto } from "./produto";

export class Estoque {
    id: number;
    produto: Produto;
    quantity: number;
    sellingPrice: number;
}