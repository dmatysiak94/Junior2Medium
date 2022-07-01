package com.example.junior2medium.service;

import com.example.junior2medium.entity.StudentEntity;

import java.util.Optional;

public interface StudentService {

    Optional<StudentEntity> getStudentById(Long id);

    Optional<StudentEntity> getStudentByUserName(String surname);

    Optional<StudentEntity> addStudent(StudentEntity studentEntity);

    Optional<StudentEntity> updateStudent(StudentEntity studentEntity);

    void deleteStudent(StudentEntity studentEntity);
}
