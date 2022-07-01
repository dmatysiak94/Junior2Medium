package com.example.junior2medium.repository;

import com.example.junior2medium.dto.EmailStatusDto;
import com.example.junior2medium.dto.StudentDto;
import com.example.junior2medium.dto.StudentExistsDto;
import com.example.junior2medium.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findStudentEntityByUserName(String userName);

    Optional<StudentEntity> findStudentEntityByEmail(String email);

    @Query("SELECT new com.example.junior2medium.dto.EmailStatusDto( "
            + "ses.isEnabled) "
            + "FROM StudentEntity se "
            + "INNER JOIN se.sendEmailStatus ses "
            + "WHERE se.email = :email")
    EmailStatusDto findSendEmailStatus(String email);

    @Query("SELECT new com.example.junior2medium.dto.StudentExistsDto( "
            + "se.id) "
            + "FROM StudentEntity se "
            + "WHERE se.email = :email")
    Optional<StudentExistsDto> findStudentExist(String email);

    @Query("SELECT new com.example.junior2medium.dto.StudentDto( "
            + "ucv.id, "
            + "ucv.email, "
            + "ure.isEnabled, "
            + "ure.dateSent) "
            + "FROM StudentEntity ucv "
            + "INNER JOIN ucv.sendEmailStatus ure "
            + "WHERE ure.isEnabled = :sendReminder")
    Optional<List<StudentDto>> findStudentsWithSentEmail(Boolean sendReminder);

}
