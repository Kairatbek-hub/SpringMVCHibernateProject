package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public String getCompanyInf(@PathVariable Long id, Model model) {
        model.addAttribute("courses", courseService.courseList());
        return "company/Company";
    }

    @GetMapping("/getCourse")
    public String getCourseAddPage(Model model){
        model.addAttribute("course", new Course());
        return "course/addCourse";
    }
    @PostMapping("/postCourse")
    public String courseSave(@ModelAttribute Course course,@RequestParam("id") Long id, Model model){
        course.setCompany(companyService.getById(id));
        courseService.saveCourse(course);
        model.addAttribute("courses", courseService.courseList());
        return "/company/Company";
    }
    @GetMapping("/edit/{id}")
    public String getCourseToUpdate(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        return "course/updateCourse";
    }

    @PostMapping ("/update/{id}")
    public String updateCourse(@ModelAttribute Course course, Model model){
        courseService.updateCourse(course);
        model.addAttribute("courses", courseService.courseList());
        return "/company/Company";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, Model model) {
       courseService.deleteCourse(id);
        model.addAttribute("courses", courseService.courseList());
        return "/company/Company";
    }

}
