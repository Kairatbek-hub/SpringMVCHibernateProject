package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Teacher;
import peaksoft.service.CourseService;
import peaksoft.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    //    @GetMapping
//    public String getTeacherInf(@RequestParam Long id, Model model) {
//        model.addAttribute("teachers", teacherService.teacherList(id));
//        return "group/Groups";
//    }

    @GetMapping("/getTeacher")
    public String getTeacherAddPage(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "group/addTeacher";
    }

    @PostMapping("/postTeacher")
    public String teacherSave(@ModelAttribute Teacher teacher, @RequestParam("courseId") Long courseId) {
        if (courseService.getCourseById(courseId).getTeacher() == null) {
            teacher.setCourse(courseService.getCourseById(courseId));
            teacherService.saveTeacher(teacher);
        } else {
            throw new Error("You can not add two instructor");
        }
        return "redirect:/group?courseId=" + courseId;
    }

    @GetMapping("/edit")
    public String getTeacherToUpdate(@RequestParam("teacherId") Long teacherId, Model model) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", teacher);
        return "group/updateTeacher";
    }

    @PatchMapping("/update")
    public String updateTeacher(@ModelAttribute Teacher teacher, @RequestParam("teacherId") Long teacherId, @RequestParam("courseId") Long courseId) {
        teacher.setId(teacherId);
        teacherService.updateTeacher(teacher);
        return "redirect:/group?courseId=" + courseId;
    }

    @DeleteMapping("/delete")
    public String deleteGroup(@RequestParam("teacherId") Long teacherId, @RequestParam("courseId") Long courseId) {
        teacherService.deleteTeacher(teacherId);
        return "redirect:/group?courseId=" + courseId;
    }
}
