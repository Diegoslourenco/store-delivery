import { Routes } from "@angular/router";
import { ContatoComponent } from "./components/institucional/contato/contato.component";
import { SobreComponent } from "./components/institucional/sobre/sobre.component";
import { LoginComponent } from "./components/login/login.component";
import { HomeComponent } from "./components/navegacao/home/home.component";
import { ProdutoComponent } from "./components/produto/produto.component";
import { RegisterComponent } from "./components/register/register.component";
import { EstoqueComponent } from "./components/estoque/estoque.component";
import { FornecedorComponent } from "./components/fornecedor/fornecedor.component";
import { ClienteComponent } from "./components/cliente/cliente.component";
import { CompraComponent } from "./components/compra/compra.component";
import { VendaComponent } from "./components/venda/venda.component";

export const routeRouterConfig: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'contato', component: ContatoComponent },
    { path: 'sobre', component: SobreComponent },
    { path: 'produtos', component: ProdutoComponent },
    { path: 'estoques', component: EstoqueComponent },
    { path: 'fornecedores', component: FornecedorComponent },
    { path: 'clientes', component: ClienteComponent },
    { path: 'compras', component: CompraComponent },
    { path: 'vendas', component: VendaComponent }
];
