package com.unimib.librarysystem.DTO;

public class AdvancedSearchDTO {

    private String publisher;

    private String authorNationality;

    public AdvancedSearchDTO() { }

    public AdvancedSearchDTO(String publisher, String authorNationality) {
        this.publisher = publisher;
        this.authorNationality = authorNationality;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthorNationality() {
        return authorNationality;
    }

    public void setAuthorNationality(String authorNationality) {
        this.authorNationality = authorNationality;
    }
}

