import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { MenuComponent } from './components/navegacao/menu/menu.component';
import { HomeComponent } from './components/navegacao/home/home.component';
import { FooterComponent } from './components/navegacao/footer/footer.component';
import { SobreComponent } from './components/institucional/sobre/sobre.component';
import { ContatoComponent } from './components/institucional/contato/contato.component';
import { routeRouterConfig } from './app.routes';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { ClienteComponent } from './components/cliente/cliente.component';
import { CompraComponent } from './components/compra/compra.component';
import { EstoqueComponent } from './components/estoque/estoque.component';
import { FornecedorComponent } from './components/fornecedor/fornecedor.component';
import { ProdutoComponent } from './components/produto/produto.component';
import { VendaComponent } from './components/venda/venda.component';

import { CompraService } from './_services/compra.service';
import { EstoqueService } from './_services/estoque.service';
import { FornecedorService } from './_services/fornecedor.service';
import { ProdutoService } from './_services/produtos.service';
import { VendaService } from './_services/venda.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MenuComponent,
    HomeComponent,
    FooterComponent,
    SobreComponent,
    ContatoComponent,
    ProdutoComponent,
    EstoqueComponent,
    FornecedorComponent,
    ClienteComponent,
    CompraComponent,
    VendaComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
   [RouterModule.forRoot(routeRouterConfig, { useHash: false })]
  ],
  providers: [
    ProdutoService,
    EstoqueService,
    FornecedorService,
    CompraService,
    VendaService,
    authInterceptorProviders,
    { provide: APP_BASE_HREF, useValue: '/' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
