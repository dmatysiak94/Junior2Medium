package com.example.junior2medium.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Constraint(validatedBy = SendEmailValidation.class)
@Target({PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SendEmailValidationConstrain {
    String message() default "Email Already Sent";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
