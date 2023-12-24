// import { Routes } from '@angular/router';
import { AcasaComponent } from './acasa/acasa.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
    { path: '', redirectTo: 'acasa', pathMatch: 'full' },
    { path: 'acasa', component: AcasaComponent },
    { path: 'login', component: LoginComponent },
    { path: '**',component: AcasaComponent },
];
