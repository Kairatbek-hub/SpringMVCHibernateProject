package peaksoft.service;

import peaksoft.model.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(Student student);
    List<Student> studentList();
    void updateStudent(Student student);
    void deleteStudent(Long id);
    Student getStudentById(Long id);
}
