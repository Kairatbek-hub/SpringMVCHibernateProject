package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private GroupService groupService;

    @GetMapping()
    public String getCourseInf(@RequestParam("companyId") Long id, Model model) {
        List<Course> courseList =courseService.courseList(id);
        model.addAttribute("courses", courseList);
//        Set<String> groupSet = new HashSet<>();
//        for(Course course: courseList){
//            for(Group group: course.getGroupList()){
//                groupSet.add(group.getGroupName());
//            }
//        }
//
//        model.addAttribute("groups",groupSet);
        return "company/Company";
    }

    @GetMapping("/getCourse")
    public String getCourseAddPage(Model model){
        model.addAttribute("course", new Course());
        return "course/addCourse";
    }
    @PostMapping("/postCourse")
    public String courseSave(@ModelAttribute Course course,@RequestParam("companyId") Long id, Model model){
        course.setCompany(companyService.getById(id));
        courseService.saveCourse(course);
        model.addAttribute("courses", courseService.courseList(id));
        return "/company/Company";
    }
    @GetMapping("/edit")
    public String getCourseToUpdate(@RequestParam("courseId") Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        return "course/updateCourse";
    }

    @PostMapping ("/update")
    public String updateCourse(@ModelAttribute Course course, Model model){
        courseService.updateCourse(course);
//        model.addAttribute("courses", courseService.courseList(id));
//        return "/company/Company";
        return "redirect:/course";
    }

    @GetMapping("/delete")
    public String deleteCourse(@RequestParam("courseId") Long id, Model model) {
        courseService.deleteCourse(id);
//        model.addAttribute("courses",courseService.courseList(id));
        return "redirect:/course";
    }
//    @GetMapping("/edit/{id}")
//    public String getCourseToUpdate(@PathVariable("id") Long id, Model model) {
//        Course course = courseService.getById(id);
//        model.addAttribute("course", course);
//        return "course/updateCourse";
//    }
//
//    @PostMapping ("/update/{id}")
//    public String updateCourse(@ModelAttribute Course course, Model model){
//        courseService.updateCourse(course);
//        model.addAttribute("courses", courseService.courseList());
//        return "/company/Company";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteCourse(@PathVariable("id") Long id, Model model) {
//       courseService.deleteCourse(id);
//        model.addAttribute("courses", courseService.courseList());
//        return "/company/Company";
//    }

}
