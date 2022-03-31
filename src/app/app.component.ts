import { Component, OnInit } from '@angular/core';
import { JwtUser } from './core/user.model';
import { UserStateService } from './core/user-state.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  title = 'ebiznes';
  user?: JwtUser;

  constructor(private userStateService: UserStateService) {
  }

  ngOnInit(): void {
    this.userStateService.currentUser.subscribe(
      (user) => {
        this.user = user;
        console.log(user);
      }
    );
  }


  logout() {
    this.userStateService.onLogout();
  }
}
