package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @GetMapping
    public String getCourseInf(@RequestParam("companyId") Long companyId, Model model) {
        List<Course> courseList = courseService.courseList(companyId);
        model.addAttribute("courses", courseList);
        return "course/Courses";
    }

    @GetMapping("/getCourse")
    public String getCourseAddPage(Model model) {
        model.addAttribute("course", new Course());
        return "course/addCourse";
    }

    @PostMapping("/postCourse")
    public String courseSave(@ModelAttribute Course course, @RequestParam("companyId") Long companyId) {
        course.setCompany(companyService.getCompanyById(companyId));
        courseService.saveCourse(course);
        return "redirect:/course?companyId=" + companyId;
    }

    @GetMapping("/edit")
    public String getCourseToUpdate(@RequestParam("courseId") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "course/updateCourse";
    }

    @PatchMapping("/update")
    public String updateCourse(@ModelAttribute Course course, @RequestParam("courseId") Long courseId, @RequestParam("companyId") Long companyId) {
        course.setId(courseId);
        courseService.updateCourse(course);
        return "redirect:/course?companyId=" + companyId;
    }

    @DeleteMapping("/delete")
    public String deleteCourse(@RequestParam("courseId") Long courseId, @RequestParam("companyId") Long companyId) {
        courseService.deleteCourse(courseId);
        return "redirect:/course?companyId=" + companyId;
    }
}
