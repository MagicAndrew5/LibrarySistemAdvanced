import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup, AbstractControl } from '@angular/forms';

import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register-user',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register-user.component.html',
  styleUrl: './register-user.component.css'
})

export class RegisterUserComponent {
  registerForm: FormGroup;
  message: string = '';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]],
      phoneNumber: ['', [Validators.required]],
      city: ['', [Validators.required]],
    }, { validators: this.passwordsMatchValidator });
  }

  passwordsMatchValidator(group: AbstractControl): { [key: string]: boolean } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordsMismatch: true };
  }

  onSubmit(): void {
    if (this.registerForm.invalid) {
      console.log("Form is invalid", this.registerForm.value);
      return;
    }
  
    const user = this.registerForm.value;
  
    const accountDTO = {
      user: {
        name: user.name,
        surname: user.surname,
        username: user.username,
        email: user.email,
        password: user.password,
        phoneNumber: user.phoneNumber,
        city: user.city
      },
      libraryMember: {}  // opzionale, il backend lo riempie da solo
    };
  
    this.http.post<any>('/api/auth/saveaccount/', accountDTO)
      .subscribe({
        next: (response) => {
          this.message = response.message;
          localStorage.setItem('username', response.username); // se vuoi salvarlo
          this.router.navigate(['home']);
        },
        error: (err) => {
          this.message = err.error?.message || 'Register failed';
        }
      });
  }
  
}


