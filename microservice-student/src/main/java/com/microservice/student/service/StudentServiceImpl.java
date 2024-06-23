package com.microservice.student.service;

import com.microservice.student.Mapper.StudentMapper;
import com.microservice.student.entities.Student;
import com.microservice.student.persistence.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.student.dto.StudentDTO;
import com.microservice.student.exception.StudentAlreadyExistsException;
import com.microservice.student.exception.CourseNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    public StudentServiceImpl() {
    }

    @Override
    public List<StudentDTO> findAll() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return students.stream()
                .map(StudentMapper::toStudentDTO)
                .collect(Collectors.toList());
    }
    @Override
    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return StudentMapper.toStudentDTO(student);
    }
    @Override
    public void save(StudentDTO studentDTO) {
        studentRepository.findByNameAndLastName(studentDTO.getName(), studentDTO.getLastName())
                .ifPresent(s -> {
                    throw new StudentAlreadyExistsException("Student with the same name and last name already exists");
                });
        Student student = StudentMapper.toStudent(studentDTO);
        studentRepository.save(student);
    }
    @Override
    public List<StudentDTO> findByIdCourse(Long idCourse) {
        List<Student> students = studentRepository.findAllByStudent(idCourse);
        return students.stream()
                .map(StudentMapper::toStudentDTO)
                .collect(Collectors.toList());
    }
}
