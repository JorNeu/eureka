package com.microservice.student.service;

import com.microservice.student.dto.StudentDTO;
import com.microservice.student.entities.Student;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> findAll();
    StudentDTO findById(Long id);
    void save(StudentDTO studentDTO);
    List<StudentDTO> findByIdCourse(Long idCourse);
}
