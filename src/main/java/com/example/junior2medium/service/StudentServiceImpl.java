package com.example.junior2medium.service;

import com.example.junior2medium.entity.SendEmailStatusEntity;
import com.example.junior2medium.entity.StudentEntity;
import com.example.junior2medium.repository.SendEmailStatusRepository;
import com.example.junior2medium.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    SendEmailStatusRepository sendEmailStatusRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              SendEmailStatusRepository sendEmailStatusRepository) {
        this.studentRepository = studentRepository;
        this.sendEmailStatusRepository = sendEmailStatusRepository;
    }

    @Override
    public Optional<StudentEntity> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<StudentEntity> getStudentByUserName(String surname) {
        return studentRepository.findStudentEntityByUserName(surname);
    }

    @Override
    @Transactional
    public Optional<StudentEntity> addStudent(StudentEntity studentEntity) {
        if (studentRepository.findStudentEntityByUserName(studentEntity.getUserName()).isPresent()) {
            return Optional.ofNullable(null);
        } else if (studentRepository.findStudentEntityByEmail(studentEntity.getEmail()).isPresent()) {
            return Optional.ofNullable(null);
        }
        StudentEntity newStudent = saveStudent(studentEntity);
        SendEmailStatusEntity newSendEmailStatusForStudent = new SendEmailStatusEntity(true,
                null,
                newStudent);
        sendEmailStatusRepository.save(newSendEmailStatusForStudent);
        return Optional.of(newStudent);
    }

    @Override
    public Optional<StudentEntity> updateStudent(StudentEntity studentEntity) {
        if (studentRepository.findStudentEntityByUserName(studentEntity.getUserName()).isPresent()) {
            return Optional.ofNullable(null);
        } else if (studentRepository.findStudentEntityByEmail(studentEntity.getEmail()).isPresent()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(saveStudent(studentEntity));
    }

    @Override
    public void deleteStudent(StudentEntity studentEntity) {
        studentRepository.delete(studentEntity);
    }

    private StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }
}
