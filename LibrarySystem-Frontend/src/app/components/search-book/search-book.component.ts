import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-search-book',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './search-book.component.html',
  styleUrls: ['./search-book.component.css']
})
export class SearchBookComponent {
  searchForm: FormGroup;
  isbn: string = '';
  title: string = '';
  author: string = '';
  ebooksOnly: boolean = false;
  results: any[] = [];
  message: string = '';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private router: Router,
    ) {
      this.searchForm = this.fb.group({
        isbn: ['', [Validators.required]],
        title: ['', [Validators.required]],
        author: ['', [Validators.required]],
        ebooksOnly: [false],
      },);
    }

    onSubmit() {
    
      const { isbn, title, author, ebooksOnly } = this.searchForm.value;

      const bookPayload: any = {
        isbn: isbn?.trim() || null,
        title: title?.trim() || null,
        author: author?.trim() || null,
      };

  
      const params = new HttpParams().set('ebooksOnly', ebooksOnly.toString());
    
      this.http.post<any>('/api/searchBook/check/', bookPayload, {params}).subscribe({
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
