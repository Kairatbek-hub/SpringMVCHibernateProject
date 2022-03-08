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
    @Autowired
    private CourseService courseService;
    @Autowired
    private CompanyService companyService;
//    @Autowired
//    private GroupService groupService;

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

        return "course/Courses";
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
        return "course/Courses";
    }
    @GetMapping("/edit")
    public String getCourseToUpdate(@RequestParam("courseId") Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        return "course/updateCourse";
    }

    @PatchMapping("/update")
    public String updateCourse(@ModelAttribute Course course, Model model, @RequestParam("courseId") Long id , @RequestParam("companyId") Long cid){
        course.setId(id);
        courseService.updateCourse(course);
//        model.addAttribute("courses", courseService.courseList(id));
//        return "course/Courses";
        return "redirect:/course?companyId=" + cid;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, Model model) {
        System.out.println(id);
        courseService.deleteCourse(id);
//        model.addAttribute("courses",courseService.courseList(id));
        return "redirect:/course?companyId=1";
    }
//    @GetMapping("/edit/{id}")
//    public String getCourseToUpdate(@PathVariable("id") Long id, Model model) {
//        Course course = courseService.getById(id);
//        model.addAttribute("course", course);
//        return "course/updateCourse";
//    }
//
//    @PostMapping ("/update/{id}")
//    public String updateCourse(@PathVariable("id")Long id, @ModelAttribute Course course, Model model){
//        courseService.updateCourse(course);
//        model.addAttribute("courses", courseService.courseList(id));
//        return "course/Courses";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteCourse(@PathVariable("id") Long id, Model model) {
//       courseService.deleteCourse(id);
//        model.addAttribute("courses", courseService.courseList());
//        return "/company/Company";
//    }

}
