import { Injectable } from '@angular/core';
import { Observable, ReplaySubject } from 'rxjs';
import { JwtUser } from './user.model';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class UserStateService {

  public currentUser: Observable<JwtUser | undefined>;
  private currentUserSubject = new ReplaySubject<JwtUser | undefined>(1);

  constructor(private tokenService: TokenService) {
    this.currentUser = this.currentUserSubject.asObservable();
    const user = tokenService.getUser();
    if (user) {
      this.currentUserSubject.next(user);
    }
  }

  onLogin(token: string): void {
    this.tokenService.saveToken(token);
    const user = this.tokenService.getUser();
    this.currentUserSubject.next(user);
  }

  onLogout(): void {
    this.tokenService.removeToken();
    this.currentUserSubject.next(undefined);
  }
}
