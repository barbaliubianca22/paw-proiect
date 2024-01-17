import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-solve',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './solve.component.html',
  styleUrl: './solve.component.scss'
})
export class SolveComponent {
  myForm: FormGroup;

  constructor(private router: Router,private formBuilder: FormBuilder) {
    this.myForm = this.formBuilder.group({
      code: '',
    });
  }

  postSolution() {
    //logica pentru postarea soluției
    console.log('Datele introduse:', this.myForm.value);
    this.router.navigate(['/logged-exploring']);
    alert('Solutia a fost publicata!');
  }

}
