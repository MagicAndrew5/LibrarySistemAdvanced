package com.unimib.librarysystem.DTO;

import java.util.List;

public class LibraryMemberDTO {
    private Integer id;

    private String name;

    private String surname;

    private String membershipDate;

    private List<Integer> borrowedBookIds;

    private List<Integer> historianBookIds;

    public LibraryMemberDTO() { }

    public LibraryMemberDTO(Integer id, String name, String surname, String membershipDate,
                            List<Integer> borrowedBookIds, List<Integer> historianBookIds) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.membershipDate = membershipDate;
        this.borrowedBookIds = borrowedBookIds;
        this.historianBookIds = historianBookIds;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getMembershipDate() { return membershipDate; }

    public void setMembershipDate(String membershipDate) { this.membershipDate = membershipDate; }

    public List<Integer> getBorrowedBookIds() { return borrowedBookIds; }

    public void setBorrowedBookIds(List<Integer> borrowedBookIds) { this.borrowedBookIds = borrowedBookIds; }

    public List<Integer> getHistorianBookIds() { return historianBookIds; }

    public void setHistorianBookIds(List<Integer> historianBookIds) { this.historianBookIds = historianBookIds; }
}

