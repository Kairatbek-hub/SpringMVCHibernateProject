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

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String getStudentInf(@RequestParam("groupId") Long groupId,
                                Model model) {
        model.addAttribute("students", studentService.studentList(groupId));
        return "student/Students";
    }

    @GetMapping("/getStudent")
    public String getStudentAddPage(Model model) {
        model.addAttribute("student", new Student());
        return "student/addStudent";
    }

    @PostMapping("/postStudent")
    public String saveStudent(@ModelAttribute Student student, @RequestParam("groupId") Long groupId) {
        student.setGroup(groupService.getGroupById(groupId));
        studentService.saveStudent(student);
        return "redirect:/student?groupId=" + groupId;
    }

    @GetMapping("/edit")
    public String getStudentToUpdate(@RequestParam("studentId") Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "student/updateStudent";
    }

    @PatchMapping("/update")
    public String updateStudent(@ModelAttribute Student student, @RequestParam("studentId") Long studentId, @RequestParam("groupId") Long groupId) {
        student.setId(studentId);
        studentService.updateStudent(student);
        return "redirect:/student?groupId=" + groupId;
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") Long studentId, @RequestParam("groupId") Long groupId) {
        studentService.deleteStudent(studentId);
        return "redirect:/student?groupId=" + groupId;
    }
}
