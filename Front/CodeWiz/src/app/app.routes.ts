// import { Routes } from '@angular/router';
import { AcasaComponent } from './acasa/acasa.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


export const routes: Routes = [
    { path: '', redirectTo: 'acasa', pathMatch: 'full' },
    { path: 'acasa', component: AcasaComponent },
    { path: '**',component: AcasaComponent },
];
