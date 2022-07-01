package com.example.junior2medium.dto;

public class EmailStatusDto {

    public EmailStatusDto() {
    }

    public EmailStatusDto(Boolean isSent) {
        this.isSent = isSent;
    }

    Boolean isSent;

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }
}
