import { Fornecedor } from "./fornecedor";
import { ItemVendaPrice } from "./item-venda-price";

export class Compra {
    id: number;
    fornecedor: Fornecedor;
    itens: ItemVendaPrice[];
}