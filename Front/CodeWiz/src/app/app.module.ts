import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { CircleSvgComponent } from './acasa/circle-svg.component';
import { AppComponent } from './app.component';
import { AcasaComponent } from './acasa/acasa.component'; // Asigură-te că ai importat AcasaComponent
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    AcasaComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    CircleSvgComponent,
    // ... alte module
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }