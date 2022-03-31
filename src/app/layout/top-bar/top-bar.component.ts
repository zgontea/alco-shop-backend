import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { JwtUser } from '../../core/user.model';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.scss']
})
export class TopBarComponent {

  @Input()
  user?: JwtUser

  @Output()
  logoutEvent: EventEmitter<void> = new EventEmitter<void>();

}
