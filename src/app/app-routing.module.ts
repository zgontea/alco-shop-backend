import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { MainViewComponent } from './main-view/main-view.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RegisterPageComponent } from './register-page/register-page.component';

const routes: Routes = [
  {
    path: 'login', component: LoginPageComponent,
  },
  {
    path: 'register', component: RegisterPageComponent,
  },
  {
    path: 'navigation', component: NavigationComponent,
  },
  {
    path: 'mainView', component: MainViewComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
