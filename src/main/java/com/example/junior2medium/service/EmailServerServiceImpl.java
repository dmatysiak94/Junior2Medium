package com.example.junior2medium.service;

import com.example.junior2medium.dto.EmailDto;
import com.example.junior2medium.dto.StudentDto;
import com.example.junior2medium.entity.SendEmailStatusEntity;
import com.example.junior2medium.entity.StudentEntity;
import com.example.junior2medium.repository.SendEmailStatusRepository;
import com.example.junior2medium.repository.StudentRepository;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailServerServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;
    private StudentRepository studentRepository;
    private SendEmailStatusRepository sendEmailStatusRepository;

    private static final Boolean FIND_STUDENTS_WITH_DISABLED_EMAIL_SEND = false;
    private static final Boolean ALLOW_FOR_SEND = true;
    private static final Boolean STOP_SENDING_REMINDERS = false;

    @Autowired
    public EmailServerServiceImpl emailServerServiceImpl(JavaMailSender javaMailSender,
                                                         StudentRepository studentRepository,
                                                         SendEmailStatusRepository sendEmailStatusRepository) {
        this.javaMailSender = javaMailSender;
        this.studentRepository = studentRepository;
        this.sendEmailStatusRepository = sendEmailStatusRepository;
        return null;
    }

    @Override
    public void sendEmail(EmailDto emailDto) {

        Optional<StudentEntity> student = studentRepository.findStudentEntityByEmail(emailDto.getEmailAddressToSend());

         if (student.get().getSendEmailStatus().isEnabled) {
            SendEmailStatusEntity sendEmailStatus = updateCvReminderHandler(student.get().getSendEmailStatus());
            sendEmailStatusRepository.save(sendEmailStatus);
        }

        sendEmailViaSmtp(emailDto);
    }

    private SendEmailStatusEntity updateCvReminderHandler(SendEmailStatusEntity updateCvReminder) {
        updateCvReminder.setEnabled(STOP_SENDING_REMINDERS);
        updateCvReminder.setDateSent(LocalDateTime.now());
        return updateCvReminder;
    }

    private void sendEmailViaSmtp(EmailDto emailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("dmatysiakdev@gmail.com");
        simpleMailMessage.setTo(emailDto.getEmailAddressToSend());
        simpleMailMessage.setText(emailDto.getEmailContent());
        simpleMailMessage.setSubject(emailDto.getEmailSubject());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    @Transactional
    public void allowToSendAnotherEmail() {
        studentRepository.findStudentsWithSentEmail(FIND_STUDENTS_WITH_DISABLED_EMAIL_SEND)
            .ifPresent(users -> users.stream()
                .forEach(user -> {
                        DateTime dateFromUser = DateTime.parse(user.getDateSent().toString());
                        boolean result = Minutes.minutesBetween(new DateTime(dateFromUser), new DateTime())
                                .isGreaterThan(Minutes.minutes(1));
                        if (result) {
                            changeSentEmailStatus(user);
                        }
                    }
                )
            );
    }

    private void changeSentEmailStatus(StudentDto studentDto) {
        studentDto.setEnabled(ALLOW_FOR_SEND);
        Optional<SendEmailStatusEntity> emailStatusDto = sendEmailStatusRepository.findById(studentDto.getId());
        emailStatusDto.ifPresent(emailStatus -> {
            emailStatus.setEnabled(studentDto.isEnabled);
            sendEmailStatusRepository.save(emailStatus);});

    }
}
