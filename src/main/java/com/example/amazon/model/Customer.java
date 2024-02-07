package com.example.amazon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", columnDefinition = "char(50)", nullable = false)
    private String id;

    @Column(name = "DESCRIPTION", columnDefinition = "varchar2(500)")
    private String description;

    @Column(name = "EMAIL", columnDefinition = "char(100)", nullable = false)
    private String email;

    @Column(name = "FIRST_NAME", columnDefinition = "varchar(100)")
    private String firstName;

    @Column(name = "LAST_NAME", columnDefinition = "varchar(100)")
    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
