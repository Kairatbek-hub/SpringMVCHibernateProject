package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.model.Course;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public void saveCourse(Course course) {
        courseDao.saveCourse(course);
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
    public Course getById(Long id) {
        return courseDao.getById(id);
    }
}
