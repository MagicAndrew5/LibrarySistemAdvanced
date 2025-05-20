package com.unimib.lybrarysystem.DTO;

import java.util.List;
import java.util.Set;

public class BookDTO {

    private Integer isbn;

    private String title;

    private String author;

    private String publisher;

    private Set<String> authors;

    private GenreDTO genre;

    private List<Integer> borrowingMemberIds;

    private List<Integer> historianMemberIds;

    // Costruttori
    public BookDTO() { }

    public BookDTO(Integer isbn, String title, String author, String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public BookDTO(Integer isbn, String title, String author, String publisher, Set<String> authors,
                   GenreDTO genre, List<Integer> borrowingMemberIds, List<Integer> historianMemberIds) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.authors = authors;
        this.genre = genre;
        this.borrowingMemberIds = borrowingMemberIds;
        this.historianMemberIds = historianMemberIds;
    }

    // Getter e Setter
    public Integer getIsbn() { return isbn; }

    public void setIsbn(Integer isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public GenreDTO getGenre() { return genre; }

    public void setGenre(GenreDTO genre) { this.genre = genre; }

    public List<Integer> getBorrowingMemberIds() { return borrowingMemberIds; }

    public void setBorrowingMemberIds(List<Integer> borrowingMemberIds) 
    { this.borrowingMemberIds = borrowingMemberIds; }

    public List<Integer> getHistorianMemberIds() { return historianMemberIds; }

    public void setHistorianMemberIds(List<Integer> historianMemberIds) 
    { this.historianMemberIds = historianMemberIds; }

}

