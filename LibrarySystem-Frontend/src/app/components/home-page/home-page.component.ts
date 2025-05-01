import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, ReactiveFormsModule, Validators, FormGroup, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomeComponent implements OnInit {
  user: any = {}
  borrowedBooks: any[] = [];
  historianBooks: any[] = [];
  listAllBooks: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any>('/api/homepage/').subscribe(
      response => {
        console.log(response);  // Aggiungi questo per debug
        this.user = response.user;
        this.borrowedBooks = response.borrowedBooks;
        this.historianBooks = response.historianBooks;
        this.listAllBooks = response.listAllBooks;
      },
      error => {
        console.error('Errore nel caricamento dei dati', error);  // Gestisci l'errore
      }
    );
  }
}
