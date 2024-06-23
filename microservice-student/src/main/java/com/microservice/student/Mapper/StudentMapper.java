package com.microservice.student.Mapper;

import com.microservice.student.dto.StudentDTO;
import com.microservice.student.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public static StudentDTO toStudentDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .courseId(student.getCourseId())
                .build();
    }

    public static Student toStudent(StudentDTO studentDTO) {
        return Student.builder()
                .name(studentDTO.getName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .courseId(studentDTO.getCourseId())
                .build();
    }
}