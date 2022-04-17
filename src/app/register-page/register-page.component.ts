import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from '../register.service';
import { User } from '../user';

@Component({
    selector: 'app-register-page',
    templateUrl: './register-page.component.html',
    styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit, OnDestroy {
    user: User = new User();

    constructor(private registerService: RegisterService) { }

    public ngOnInit(): void {
    }

    public ngOnDestroy(): void {
    }

    public registerUser(): void {
        this.registerService.registerUser(this.user).subscribe({
            complete: () => { alert("Successfully User register") },
            error: () => { alert("Sorry User not register") }
        });
    }
} 
