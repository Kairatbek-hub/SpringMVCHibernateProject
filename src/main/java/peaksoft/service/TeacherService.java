package peaksoft.service;

import peaksoft.model.Teacher;

import java.util.List;

public interface TeacherService {
    void saveTeacher(Teacher teacher);
    List<Teacher> teacherList();
    void updateTeacher(Teacher teacher);
    void deleteTeacher(Long id);
    Teacher getTeacherById(Long id);
}
