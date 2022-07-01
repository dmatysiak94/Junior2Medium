package com.example.junior2medium.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentEntity {

    public StudentEntity() {
    }

    public StudentEntity(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    @NotBlank (message = "field name must not be null nor empty")
    @Size(max = 45, message = "email too long")
    String userName;

    @Column(name = "email")
    @NotBlank (message = "field email must not be null nor empty")
    @Email (message = "bad email")
    @Size(max = 60, message = "email too long")
    String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    SendEmailStatusEntity sendEmailStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SendEmailStatusEntity getSendEmailStatus() {
        return sendEmailStatus;
    }

    public void setSendEmailStatus(SendEmailStatusEntity sendEmailStatus) {
        this.sendEmailStatus = sendEmailStatus;
    }
}
