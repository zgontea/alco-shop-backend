import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit, OnDestroy {
    public loginValid = true;
    public username = '';
    public password = '';
    public confirmPassword = '';
    public phone = '';
    public firstName = '';
    public lastName = '';
  
    public ngOnInit(): void {
    }
  
    public ngOnDestroy(): void {
    }
  
    public onSubmit(): void {
        if(this.password.match(this.confirmPassword)) {
            console.log('zenada');
        } else {
            console.log('najs');
        }
    }
} 
