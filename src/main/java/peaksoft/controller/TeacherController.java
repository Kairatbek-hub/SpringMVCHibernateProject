package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.model.Teacher;
import peaksoft.service.CourseService;
import peaksoft.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getTeacherInf(@RequestParam Long id, Model model) {
        model.addAttribute("teachers", teacherService.teacherList());
        return "group/Groups";
    }

    @GetMapping("/getTeacher")
    public String getTeacherAddPage(Model model){
        model.addAttribute("teacher", new Teacher());
        return "group/addTeacher";
    }
    @PostMapping("/postTeacher")
    public String teacherSave(@ModelAttribute Teacher teacher, @RequestParam("courseId") Long id, Model model){
        teacher.setCourse(courseService.getById(id));
        teacherService.saveTeacher(teacher);
        model.addAttribute("teachers", teacherService.teacherList());
        return "group/Groups";
    }
}
