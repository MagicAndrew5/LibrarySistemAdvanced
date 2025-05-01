import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StartPageComponent } from './components/start-page/start-page.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { SearchBookComponent } from './components/search-book/search-book.component';
import { HomeComponent } from './components/home-page/home-page.component';

import { RouterModule } from '@angular/router';
import { SearchBookAdvancedComponent } from './components/search-book-advanced/search-book-advanced.component';


@NgModule({
  declarations: [
    AppComponent,
    StartPageComponent,
    BookListComponent,
    BookDetailsComponent,
    SearchBookAdvancedComponent,
  ],
  imports: [
    SignInComponent,
    RegisterUserComponent,
    HomeComponent,
    SearchBookComponent,
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration(withEventReplay())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
