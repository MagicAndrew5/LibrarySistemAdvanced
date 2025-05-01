import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {
  loginForm: FormGroup;
  message: string = '';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(45)]],
      password: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(45)]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) return;
    this.http.post('/api/auth/checkaccount/', this.loginForm.value)
      .subscribe({
        next: () => this.router.navigate(['home']),
        error: (err) => this.message = err.error.message || 'Login failed'
      });
  }
}
