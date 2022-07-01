package com.example.junior2medium.dto;

import javax.validation.constraints.NotBlank;

public class EmailDto {

    public EmailDto() {
    }

    public EmailDto(String emailAddressToSend, String emailSubject, String emailContent) {
        this.emailAddressToSend = emailAddressToSend;
        this.emailSubject = emailSubject;
        this.emailContent = emailContent;
    }

    @NotBlank
    String emailAddressToSend;

    @NotBlank
    String emailSubject;

    @NotBlank
    String emailContent;

    public String getEmailAddressToSend() {
        return emailAddressToSend;
    }

    public void setEmailAddressToSend(String emailAddressToSend) {
        this.emailAddressToSend = emailAddressToSend;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
}
