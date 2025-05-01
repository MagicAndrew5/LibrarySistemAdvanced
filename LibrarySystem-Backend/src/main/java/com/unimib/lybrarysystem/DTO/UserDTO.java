package com.unimib.lybrarysystem.DTO;

import com.unimib.lybrarysystem.model.User;

public class UserDTO {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phoneNumber;
    private String city;
    private Integer libraryMemberId;

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.city = user.getCity();
        this.libraryMemberId = user.getLibraryMember() != null ? user.getLibraryMember().getId() : null;
    }


    public UserDTO(Integer id, String name, String surname, String username, String email,
                   String phoneNumber, String city, Integer libraryMemberId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.libraryMemberId = libraryMemberId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Integer getLibraryMemberId() { return libraryMemberId; }
    public void setLibraryMemberId(Integer libraryMemberId) { this.libraryMemberId = libraryMemberId; }
}
