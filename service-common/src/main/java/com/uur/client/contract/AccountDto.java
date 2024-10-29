package com.uur.client.contract;

import java.util.Date;


public class AccountDto {
    private String id ;

    private String username;

    private String name;

    private String surname;

    private String email;

    private Date birthDate;

    public String getNameSurname() {
        return this.name + " " + this.surname;
    }

    public AccountDto() {
    }

    public AccountDto(String id, String username, String name, String surname, String email, Date birthDate) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
