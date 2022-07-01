package com.example.junior2medium.controller;


import com.example.junior2medium.entity.StudentEntity;
import com.example.junior2medium.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/student")
    public ResponseEntity<StudentEntity> getStudentByUserName(@RequestParam(value="username") String userName) {
        return studentService.getStudentByUserName(userName).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/student")
    public ResponseEntity<StudentEntity> addStudent(@RequestBody @Valid StudentEntity studentEntity) {
        return studentService.addStudent(studentEntity).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
       return studentService.getStudentById(id).map(student -> {
            studentService.deleteStudent(student);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentEntity> putStudent(@PathVariable Long id,
                                                       @RequestBody @Valid StudentEntity student) throws Exception {
        StudentEntity studentEntity = studentService.getStudentById(id)
                .orElseThrow(() -> new Exception("Employee not found for this id :: " + id));

        studentEntity.setEmail(student.getEmail());
        studentEntity.setUserName(student.getUserName());
        final Optional<StudentEntity> updatedStudent = studentService.updateStudent(studentEntity);
        return updatedStudent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
