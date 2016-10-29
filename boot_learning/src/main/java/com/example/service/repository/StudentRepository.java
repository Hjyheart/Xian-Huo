package com.example.service.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    //query
    Set<Student> findByMName(String name);

    Set<Student> findByMId(String id);

    Set<Student> findDistinctStudentByMMajor(String Major);

    //modifying
    @Modifying
    @Query("update Student s set s.mName=?1 where s.mId =?2")
    int setStudentNameById(String NewName, String Id);

    @Modifying
    @Query("update Student s set s.mMajor=?1 where s.mId=?2")
    int setStudentMajorById(String NewMajor, String Id);

    @Modifying
    @Query("update Student s set s.mContact=?1 where s.mId=?2")
    int setStudentContactById(String NewContact, String Id);

    @Modifying
    @Query("update Student s set s.mGrade=?1 where s.mId=?2")
    int setStudentGradeById(String NewGrade, String Id);

}
