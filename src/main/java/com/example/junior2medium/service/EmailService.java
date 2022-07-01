package com.example.junior2medium.service;

import com.example.junior2medium.dto.EmailDto;

public interface EmailService {

    void sendEmail (EmailDto emailDto);

    void allowToSendAnotherEmail();
}
