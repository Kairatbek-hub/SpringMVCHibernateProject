package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.TeacherDao;
import peaksoft.model.Teacher;

import java.util.List;

@Transactional
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherDao.saveTeacher(teacher);
    }

    @Override
    public List<Teacher> teacherList(Long id) {
        return teacherDao.teacherList(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherDao.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherDao.deleteTeacher(id);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherDao.getTeacherById(id);
    }
}
