import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-book-advanced',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './search-book-advanced.component.html',
  styleUrl: './search-book-advanced.component.css'
})
export class SearchBookAdvancedComponent {

  searchForm: FormGroup;

  publisher: string = '';
  authorNationality: string = '';
  results: any[] = [];
  message: string = '';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private router: Router,
    ) 
    {
    this.searchForm = this.fb.group({
      publisher: ['', [Validators.required]],
      authorNationality: ['', [Validators.required]],
    });
  }

  onSubmit(): void {
    const formValue = this.searchForm.value;
  
    console.log('Form value:', formValue);
    this.http.post<any>('/api/searchBookAdvanced/check/', formValue).subscribe({
      next: (response) => {
        this.results = response.foundBooks;

        console.log('Found books:', this.results);

        this.message = response.message || '';
        this.router.navigate(['list-book'], {
          state: {
            results: this.results,
            genres: response.genres || [],
          }
        });
      },
      error: () => {
        this.message = "Error during search.";
      }
    });
  }

}
