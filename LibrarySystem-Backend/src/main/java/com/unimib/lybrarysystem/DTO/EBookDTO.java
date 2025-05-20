package com.unimib.lybrarysystem.DTO;

import java.util.List;
import java.util.Set;

public class EBookDTO extends BookDTO {

    private String format;
    
    private int fileSizeMB;

    public EBookDTO(String format, int fileSizeMB) {
        this.format = format;
        this.fileSizeMB = fileSizeMB;
    }

    public EBookDTO(Integer isbn, String title, String author, String publisher, 
            Set<String> authors, GenreDTO genre, List<Integer> borrowingMemberIds, 
            List<Integer> historianMemberIds, String format, int fileSizeMB) {
        super(isbn, title, author, publisher, authors, genre, borrowingMemberIds, historianMemberIds);
        this.format = format;
        this.fileSizeMB = fileSizeMB;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getFileSizeMB() {
        return fileSizeMB;
    }

    public void setFileSizeMB(int fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }
}
