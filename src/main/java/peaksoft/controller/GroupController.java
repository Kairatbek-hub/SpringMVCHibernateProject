package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getGroupInf(@RequestParam("courseId") Long id, Model model) {
        model.addAttribute("groups", groupService.groupList());
        return "course/Course";
    }

    @GetMapping("/getGroup")
    public String getGroupAddPage(Model model){
        model.addAttribute("group", new Group());
        return "group/addGroup";
    }
    @PostMapping("/postGroup")
    public String groupSave(@ModelAttribute Group group, @RequestParam("courseId") Long id, Model model){
        group.setCourse(courseService.getById(id));
        groupService.saveGroup(group);
        model.addAttribute("groups", groupService.groupList());
        return "course/Course";
    }
//    @GetMapping("/edit")
//    public String getCourseToUpdate(@RequestParam("courseId") Long id, Model model) {
//        Course course = courseService.getById(id);
//        model.addAttribute("course", course);
//        return "course/updateCourse";
//    }
//
//    @PostMapping ("/update")
//    public String updateCourse(@ModelAttribute Course course, Model model){
//        courseService.updateCourse(course);
////        model.addAttribute("courses", courseService.courseList(id));
////        return "/company/Company";
//        return "redirect:/course";
//    }
//
//    @GetMapping("/delete")
//    public String deleteCourse(@RequestParam("courseId") Long id, Model model) {
//        courseService.deleteCourse(id);
//        model.addAttribute("courses",courseService.courseList(id));
//        return "redirect:/course";
//    }
}

