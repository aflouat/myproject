import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthGuard } from '../../guards/auth.guard';
import { UnauthGuard } from '../../guards/unauth.guard';
import { BoardComponent } from './components/board/board.component';


const routes: Routes = [
  { title: 'Login', path: 'login', component: LoginComponent , canActivate: [UnauthGuard]},
  { title: 'Register', path: 'register', component: RegisterComponent, canActivate: [UnauthGuard] },
  { path: 'profile', component: ProfileComponent,canActivate: [AuthGuard]},
  { path: 'my-board', component: BoardComponent,canActivate: [AuthGuard]},



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
