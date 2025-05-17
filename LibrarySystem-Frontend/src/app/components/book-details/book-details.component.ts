import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-book-details',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  bookDetails: any = null;
  isbn: string | null = null;

  constructor(
    private http: HttpClient, 
    private route: ActivatedRoute,
    private router: Router
    ) {
      const nav = this.router.getCurrentNavigation();
     }

  ngOnInit() {
    // Prendi l'ISBN dai parametri della route (assumendo route configurata con :isbn)
    this.isbn = this.route.snapshot.paramMap.get('isbn');

    if (this.isbn) {
      this.http.get<any>(`/api/detailBooksBorrowed/${this.isbn}/`).subscribe(
        response => {
          this.bookDetails = response;
          console.log(this.bookDetails);
        },
        error => {
          console.error('Errore nel caricamento dei dati', error);
        }
      );
    } else {
      console.error('ISBN non fornito nella route!');
    }
  }

  addBook(book: any): void {
    // TODO: call backend to add the book to personal list
    this.http.post<any>(`/api/addBook/${this.isbn}/`, book).subscribe({
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
