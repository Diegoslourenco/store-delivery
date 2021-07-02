import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {
    
    private roles: string[];
    isLoggedIn = false;
    showLojaPage = false;
    showClientePage = false;
    email: string;

    constructor(private tokenStorageService: TokenStorageService) { }

    ngOnInit(): void {
        this.isLoggedIn = !!this.tokenStorageService.getToken();
      
        if (this.isLoggedIn) {
            const user = this.tokenStorageService.getUser();
            this.roles = user.roles;
        
            this.showLojaPage = this.roles.includes('ROLE_LOJA');
            this.showClientePage = this.roles.includes('ROLE_CLIENTE');
        
            this.email = user.email;
        }
    }

    logout(): void {
        this.tokenStorageService.signOut();
        window.location.reload();
    }

}
