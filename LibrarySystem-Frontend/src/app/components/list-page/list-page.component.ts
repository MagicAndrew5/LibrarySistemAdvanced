import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-list-page',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './list-page.component.html',
  styleUrl: './list-page.component.css'
})
export class ListPageComponent {
  
  listAllBooks: any[] = [];
  genres: string[] = [];

  constructor(
    private http: HttpClient,
    private router: Router,
    ) {
      const nav = this.router.getCurrentNavigation();
      const state = nav?.extras?.state;

      if (state && state['results']) {
        this.listAllBooks = state['results'];
        this.genres = state['genres'] || [];
      } else {
        // Se non ci sono risultati, possiamo anche reindirizzare o mostrare un messaggio
        console.warn('No results found in navigation state.');
      }
  }
  
  addBook(book: any): void {
    // TODO: call backend to add the book to personal list
    this.http.post<any>(`/api/addBook/${book.isbn}/`, book).subscribe({
      next: () => {
        //this.results = response.foundBooks;
        //console.log('Found books:', this.book);
        console.log('Book added successfully!');
        this.router.navigate(['home']);
      },
      error: () => {
        console.error('Error adding book');
      }
    });
  }
}
