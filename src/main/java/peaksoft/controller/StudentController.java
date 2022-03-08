package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @GetMapping
    public String getStudentInf(@RequestParam("groupId") Long id, Model model) {
        model.addAttribute("students", studentService.studentList());
        return "student/Students";
    }

    @GetMapping("/getStudent")
    public String getStudentAddPage(Model model){
        model.addAttribute("student", new Student());
        return "student/addStudent";
    }
    @PostMapping("/postStudent")
    public String studentSave(@ModelAttribute Student student, @RequestParam("groupId") Long id, Model model){
        student.setGroup(groupService.getGroupId(id));
        studentService.saveStudent(student);
        model.addAttribute("students", studentService.studentList());
        return "student/Students";
    }
}
