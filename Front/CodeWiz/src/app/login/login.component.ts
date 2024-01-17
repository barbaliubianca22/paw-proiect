import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';
//import { GatewayService } from '../Back/GatewayService/gatewayService';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule,FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  myForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthenticationService, private router: Router) {
    this.myForm = this.formBuilder.group({
      username: '',
      password: '',
    });
  }

  onSubmit() {
    const userData = this.myForm.value;
    console.log('Datele introduse:', this.myForm.value);
    this.authService.login(userData).subscribe(
      (response: any) => {
        // Gestionează succesul autentificării
        this.router.navigate(['/logged-exploring']);
        console.log('Autentificare reușită:', response);
      },
      (error: any) => {
        // Gestionează eroarea autentificării
        console.error('Eroare la autentificare:', error);
        alert('Eroare la autentificare. Verifică datele introduse și încearcă din nou.');
      }
    );
  }
}
