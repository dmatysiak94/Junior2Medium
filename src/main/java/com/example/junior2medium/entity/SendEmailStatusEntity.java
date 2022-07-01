package com.example.junior2medium.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_send_status")
public class SendEmailStatusEntity {

    public SendEmailStatusEntity() {
    }

    public SendEmailStatusEntity(Boolean isEnabled, LocalDateTime dateSent, StudentEntity studentEntity) {
        this.isEnabled = isEnabled;
        this.dateSent = dateSent;
        this.studentEntity = studentEntity;
    }

    @Id
    @Column(name = "id",unique = true, nullable = false)
    public Long id;

    @Column(name = "is_enabled")
    public Boolean isEnabled;

    @Column(name = "date_sent")
    LocalDateTime dateSent;

    @OneToOne(mappedBy = "sendEmailStatus")
    @MapsId
    @JoinColumn(name = "id")
    private StudentEntity studentEntity;

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

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }
}
