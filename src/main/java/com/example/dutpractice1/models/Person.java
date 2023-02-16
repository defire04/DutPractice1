package com.example.dutpractice1.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    @NotEmpty(message = "Name should be not empty!")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String username;
    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Year of birth cant be more than 1900! It's unreal!")
    private int yearOfBirth;
    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email should be not empty!")
    private String email;
    @Column(name="password")
    @NotEmpty(message = "Password should be not empty!")
    @Size(min = 8, message = "Password must be more than 8 characters!")
    private String password;
    @Column(name="role")
    private String role;

    public Person() {
    }

    public Person(String username, int yearOfBirth, String email) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
