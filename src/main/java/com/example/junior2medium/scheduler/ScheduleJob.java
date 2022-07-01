package com.example.junior2medium.scheduler;

import com.example.junior2medium.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleJob {

    EmailService emailService;

    @Autowired
    public ScheduleJob(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(fixedDelay = 10000)
    public void allowToSendAnotherEmail() {
        emailService.allowToSendAnotherEmail();
    }
}
