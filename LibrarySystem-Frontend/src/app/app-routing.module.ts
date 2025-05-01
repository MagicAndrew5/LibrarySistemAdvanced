import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StartPageComponent } from './components/start-page/start-page.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { HomeComponent } from './components/home-page/home-page.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { SearchBookComponent } from './components/search-book/search-book.component';
import { SearchBookAdvancedComponent } from './components/search-book-advanced/search-book-advanced.component';

const routes: Routes = [
  { path: '', component: StartPageComponent },
  { path: 'login', component: SignInComponent },
  { path: 'register', component: RegisterUserComponent },
  { path: 'home', component: HomeComponent},
  { path: 'searchBook', component: SearchBookComponent }, // Search page
  { path: 'searchBookFull', component: SearchBookAdvancedComponent }, // Advanced search page
  { path: 'detailBooksBorrowed/:title', component: BookListComponent }, // Detail page for borrowed books
  { path: 'detailBooks/:title', component: BookDetailsComponent }, // Detail page for available books
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

