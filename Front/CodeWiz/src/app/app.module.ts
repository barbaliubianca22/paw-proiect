import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';

import { AppComponent } from './app.component';
import { AcasaComponent } from './acasa/acasa.component'; // Asigură-te că ai importat AcasaComponent

@NgModule({
  declarations: [
    AppComponent,
    AcasaComponent, // Asigură-te că AcasaComponent este inclus aici
    // ... alte componente
  ],
  imports: [
    BrowserModule,
    CommonModule,
    // ... alte module
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }