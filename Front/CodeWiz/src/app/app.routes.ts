// import { Routes } from '@angular/router';
import { AcasaComponent } from './acasa/acasa.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { StartExploringComponent } from './start-exploring/start-exploring.component';
import { LoggedExploringComponent } from './logged-exploring/logged-exploring.component';
import { SolveComponent } from './solve/solve.component';
import { AdminComponent } from './admin/admin.component';

export const routes: Routes = [
    { path: '', redirectTo: 'acasa', pathMatch: 'full' },
    { path: 'acasa', component: AcasaComponent },
    { path: 'login', component: LoginComponent },
    { path: 'create-account', component: CreateAccountComponent },
    { path: 'start-exploring', component: StartExploringComponent },
    { path: 'logged-exploring', component: LoggedExploringComponent },
    { path: 'solve', component: SolveComponent },
    { path: 'admin', component: AdminComponent},
    { path: '**',component: AcasaComponent },
];
