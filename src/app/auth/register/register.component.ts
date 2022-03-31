import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService, RegisterRequest } from '../../../core/openapi';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  error = '';
  loading = false;
  submitted = false;

  model: RegisterRequest = {
    email: '',
    firstName: '',
    lastName: '',
    password: ''
  }

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router) {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  register() {
    this.submitted = true;

    if(this.registerForm.invalid) {
      return;
    }

    this.loading = true;

    this.authService.register(this.model).subscribe({
        next: _ => {
          this.router.navigate(["/auth/login"])
        },
        error: err => {
          if (err?.error?.message === 'User exists') {
            this.error = 'User exists!';
          }
          this.loading = false;
        }
      })
  }
}
