package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.model.Course;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void saveCourse(Course course) {
        courseDao.saveCourse(course);
    }

    @Override
    public List<Course> courseList(Long id) {
        return courseDao.courseList(id);
    }

    @Override
    public List<Course> courseList() {
        return courseDao.courseList();
    }

    @Override
    public void updateCourse(Course course) {
        courseDao.updateCourse(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseDao.deleteCourse(id);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }
}
