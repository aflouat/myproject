import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import {  RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import {  provideHttpClient } from '@angular/common/http';
import { HeaderComponent } from './shared/components/header/header.component';
import { SharedModule } from './shared/shared.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [AppComponent,HeaderComponent],
  imports: [ BrowserModule,ReactiveFormsModule,RouterModule,AppRoutingModule, SharedModule,NoopAnimationsModule],
  bootstrap: [AppComponent],
  providers:[ provideHttpClient()]
})
export class AppModule {}