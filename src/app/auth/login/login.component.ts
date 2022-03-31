import { Component, OnInit } from '@angular/core';
import { AuthService, FacebookLoginRequest, LoginRequest } from '../../../core/openapi';
import { UserStateService } from '../../core/user-state.service';
import { FacebookLoginProvider, SocialAuthService } from 'angularx-social-login';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loading = false;
  error = '';
  loginData: LoginRequest = {
    password: '',
    username: ''
  }

  constructor(private authService: AuthService,
              private userStateService: UserStateService,
              private socialAuthService: SocialAuthService,
              private router: Router) {
  }

  login() {
    if (!this.loginData.password || !this.loginData.username) {
      return;
    }

    this.loading = true;

    this.authService.login(this.loginData).subscribe({
      next: response => {
        this.loading = false;
        this.userStateService.onLogin(response.token);
        this.router.navigate(['/main']);
      },
      error: err => {
        if (err.status === 401) {
          this.error = 'Invalid credentials!';
        } else {
          this.error = 'Error occured! Please try again.'
        }
        this.loading = false;
      }
    })
  }

  loginWithFacebook() {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID);
    this.socialAuthService.authState.subscribe((user) => {
      if (!user) {
        return ;
      }
      const loginRequest: FacebookLoginRequest = {
        accessToken: user.authToken,
        email: user.email,
        firstName: user.firstName,
        lastName: user.lastName
      };
      this.authService.loginFacebook(loginRequest).subscribe({
        next: response => {
          this.userStateService.onLogin(response.token);
          this.router.navigate(['/main']);
        },
        error: _ => {
          this.error = 'Error occured! Please try again.'
        }
      });
    });
  }
}
