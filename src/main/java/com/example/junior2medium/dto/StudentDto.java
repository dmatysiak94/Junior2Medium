package com.example.junior2medium.dto;

import java.time.LocalDateTime;

public class StudentDto {

    public StudentDto() {
    }

    public StudentDto(Long id, String email, Boolean isEnabled, LocalDateTime dateSent) {
        this.id = id;
        this.email = email;
        this.isEnabled = isEnabled;
        this.dateSent = dateSent;
    }

    public Long id;

    public String email;

    public Boolean isEnabled;

    public LocalDateTime dateSent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDateTime dateSent) {
        this.dateSent = dateSent;
    }
}
