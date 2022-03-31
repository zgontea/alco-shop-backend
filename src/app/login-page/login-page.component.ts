import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { filter, Subject, take, takeUntil } from 'rxjs';
import { RouterModule, Routes } from '@angular/router';


@Component({
    selector: 'login-page',
    templateUrl: 'login-page.component.html',
    styleUrls: ['login-page.component.css'],
})

export class LoginPageComponent implements OnInit, OnDestroy {
  public loginValid = true;
  public username = '';
  public password = '';

  private router = Router;

  public ngOnInit(): void {
  }

  public ngOnDestroy(): void {
  }

  public onSubmit(): void {
  }
}