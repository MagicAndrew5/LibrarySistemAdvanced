import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup, AbstractControl } from '@angular/forms';

import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-search-book',
  standalone: true,
  imports: [CommonModule],
  //imports: [FormBuilder, CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './search-book.component.html',
  styleUrls: ['./search-book.component.css']
})
export class SearchBookComponent {
  isbn: string = '';
  title: string = '';
  author: string = '';
  ebooksOnly: boolean = false;
  results: any[] = [];
  message: string = '';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    ) {}

  searchBooks() {
    let params = new HttpParams()
      .set('ebooksOnly', this.ebooksOnly)
      .set('isbn', this.isbn)
      .set('title', this.title)
      .set('author', this.author);

    this.http.get<any[]>('/api/books/search', { params }).subscribe({
      next: res => {
        this.results = res;
        this.message = res.length === 0 ? "No books found." : "";
      },
      error: err => {
        console.error(err);
        this.message = "Error during search.";
      }
    });
  }
}
