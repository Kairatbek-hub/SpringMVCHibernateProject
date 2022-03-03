package peaksoft.dao;

import peaksoft.model.Student;

import java.util.List;

public interface StudentDao {
    void saveStudent(Student student);
    List<Student> studentList();
    void updateStudent(Student student);
    void deleteStudent(Long id);
    Student getStudentById(Long id);
}
