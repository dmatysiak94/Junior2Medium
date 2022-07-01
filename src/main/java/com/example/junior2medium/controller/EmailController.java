package com.example.junior2medium.controller;

import com.example.junior2medium.dto.EmailDto;
import com.example.junior2medium.service.EmailService;
import com.example.junior2medium.validation.SendEmailValidationConstrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api")
public class EmailController {

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email")
    public void sendEmail(@RequestBody @SendEmailValidationConstrain @Valid EmailDto emailDto) {
        emailService.sendEmail(emailDto);
    }
}
