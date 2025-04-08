import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

import { AbstractControl, ReactiveFormsModule, ValidationErrors } from '@angular/forms';
import { AuthRoutingModule } from './auth-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { TopicModule } from '../topic/topic.module';
import { ProfileComponent } from './components/profile/profile.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from 'src/app/interceptors/jwt.interceptor';

@NgModule({
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  declarations: [
    RegisterComponent,
    LoginComponent,
    ProfileComponent
  ],
  imports: [
    ReactiveFormsModule,CommonModule,AuthRoutingModule,TopicModule,
SharedModule  ],
})
export class AuthModule { }


export function passwordValidator(control: AbstractControl): ValidationErrors | null {
  const password = control.value;

  if (!password) {
    return { required: true };
  }

  // Vérifie si le mot de passe respecte les conditions
  const hasMinLength = password.length >= 8;
  const hasUpperCase = /[A-Z]/.test(password);
  const hasLowerCase = /[a-z]/.test(password);
  const hasNumber = /\d/.test(password);
  const hasSpecialChar = /[\W_]/.test(password);

  const isValid = hasMinLength && hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar;

  return isValid
    ? null
    : {
        passwordStrength: {
          hasMinLength,
          hasUpperCase,
          hasLowerCase,
          hasNumber,
          hasSpecialChar,
        },
      };
}
