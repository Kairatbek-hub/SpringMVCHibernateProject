package peaksoft.service;

import peaksoft.model.Course;

import java.util.List;

public interface CourseService {
    void saveCourse(Course course);
    List<Course> courseList(Long id);
    List<Course> courseList();
    void updateCourse(Course course);
    void deleteCourse(Long id);
    Course getById(Long id);
}
