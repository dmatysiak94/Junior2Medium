package com.example.junior2medium.dto;

public class StudentExistsDto {

    public StudentExistsDto(Long id) {
        this.id = id;
    }

    public StudentExistsDto() {
    }

    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
