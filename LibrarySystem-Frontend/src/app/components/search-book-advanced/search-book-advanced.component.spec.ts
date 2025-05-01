import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBookAdvancedComponent } from './search-book-advanced.component';

describe('SearchBookAdvancedComponent', () => {
  let component: SearchBookAdvancedComponent;
  let fixture: ComponentFixture<SearchBookAdvancedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SearchBookAdvancedComponent]
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
