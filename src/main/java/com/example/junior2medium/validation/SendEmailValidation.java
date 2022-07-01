package com.example.junior2medium.validation;

import com.example.junior2medium.dto.EmailDto;
import com.example.junior2medium.repository.StudentRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class SendEmailValidation implements ConstraintValidator<SendEmailValidationConstrain, EmailDto> {

    private final StudentRepository studentRepository;

    SendEmailValidation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean isValid(EmailDto input, ConstraintValidatorContext context) {
        return (studentRepository.findStudentExist(input.getEmailAddressToSend()).isPresent()
                && studentRepository.findSendEmailStatus(input.getEmailAddressToSend()).getSent());
    }

    @Override
    public void initialize(SendEmailValidationConstrain constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
