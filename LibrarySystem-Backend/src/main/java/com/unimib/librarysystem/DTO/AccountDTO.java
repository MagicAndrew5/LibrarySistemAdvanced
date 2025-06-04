package com.unimib.librarysystem.DTO;

import com.unimib.librarysystem.model.LibraryMember;
import com.unimib.librarysystem.model.User;

public class AccountDTO {

    private User user;
    
    private LibraryMember libraryMember;

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    // Constructor
    public AccountDTO(User user, LibraryMember libraryMember) {
        this.user = user;
        this.libraryMember = libraryMember;
    }

    // Default constructor
    public AccountDTO() {
    }

    public AccountDTO(User user) {
        this.user = user;
    }
}
