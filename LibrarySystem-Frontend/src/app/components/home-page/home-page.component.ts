import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import Flickity from 'flickity';

@Component({
  selector: 'app-home',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomeComponent implements OnInit {

  user: any = {};
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

        setTimeout(() => this.initFlickity(), 0);

      },
      error => {
        console.error('Errore nel caricamento dei dati', error);  // Gestisci l'errore
      }
    );
  }

  initFlickity() {
    const carousel = document.querySelector('.main-carousel');
    if (carousel) {
      new Flickity(carousel, {
        cellAlign: 'left',
        contain: true,
        wrapAround: true
      });
    }
  }
}
