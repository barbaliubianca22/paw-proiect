import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { CircleSvgComponent } from './acasa/circle-svg.component';
import { AppComponent } from './app.component';
import { AcasaComponent } from './acasa/acasa.component'; // Asigură-te că ai importat AcasaComponent
import { LoginComponent } from './login/login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { StartExploringComponent } from './start-exploring/start-exploring.component';
import { LoggedExploringComponent } from './logged-exploring/logged-exploring.component';
import { SolveComponent } from './solve/solve.component';
import { AdminComponent } from './admin/admin.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    AcasaComponent,
    LoginComponent,
    CreateAccountComponent,
    StartExploringComponent,
    LoggedExploringComponent,
    SolveComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    CircleSvgComponent,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
    // ... alte module
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }