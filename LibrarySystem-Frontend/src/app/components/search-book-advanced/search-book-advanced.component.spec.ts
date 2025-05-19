import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBookAdvancedComponent } from './search-book-advanced.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('SearchBookAdvancedComponent', () => {
  let component: SearchBookAdvancedComponent;
  let fixture: ComponentFixture<SearchBookAdvancedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchBookAdvancedComponent, HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchBookAdvancedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
