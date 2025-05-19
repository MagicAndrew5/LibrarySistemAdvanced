import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBookComponent } from './search-book.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('SearchBookComponent', () => {
  let component: SearchBookComponent;
  let fixture: ComponentFixture<SearchBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchBookComponent, HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
