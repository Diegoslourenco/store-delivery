import { Cliente } from "./cliente";
import { ItemVendaPrice } from "./item-venda-price";

export class Venda {
    id: number;
    cliente: Cliente;
    status: string;
    itens: ItemVendaPrice[];
}