package peaksoft.dao;

import peaksoft.model.Student;

import java.util.List;

public interface StudentDao {
    void saveStudent(Student student);
    List<Student> studentList(Long id);
    void updateStudent(Student student);
    void deleteStudent(Long id);
    Student getStudentById(Long id);
}
