import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/_services/cliente.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    
    form: any = {};
    isSuccessful = false;
    isSignUpFailed = false;
    errorMessage = '';

    constructor(private clienteService: ClienteService) { }

    ngOnInit(): void { }

    onSubmit(): void {
        this.clienteService.register(this.form).subscribe(
            data => {
                console.log(data);
                this.isSuccessful = true;
                this.isSignUpFailed = false;
            },
            
            err => {
                this.errorMessage = err.error.message;
                this.isSignUpFailed = true;
            }
        );
    }

}
