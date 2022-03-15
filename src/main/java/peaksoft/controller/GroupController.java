package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;
import peaksoft.service.TeacherService;

@Controller
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    @Autowired
    public GroupController(GroupService groupService, CourseService courseService, TeacherService teacherService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getGroupAndTeacherInf(@RequestParam("courseId") Long courseId, Model model) {
        model.addAttribute("groups", groupService.groupList(courseId));
        model.addAttribute("teachers", teacherService.teacherList(courseId));
        return "group/Groups";
    }

    @GetMapping("/getGroup")
    public String getGroupAddPage(Model model) {
        model.addAttribute("group", new Group());
        return "group/addGroup";
    }

    @PostMapping("/postGroup")
    public String groupSave(@ModelAttribute Group group, @RequestParam("courseId") Long courseId) {
        group.setCourse(courseService.getCourseById(courseId));
        groupService.saveGroup(group);
        return "redirect:/group?courseId=" + courseId;
    }

    @GetMapping("/edit")
    public String getGroupToUpdate(@RequestParam("groupId") Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        model.addAttribute("group", group);
        return "group/updateGroup";
    }

    @PatchMapping("/update")
    public String updateGroup(@ModelAttribute Group group, @RequestParam("courseId") Long courseId, @RequestParam("groupId") Long groupId) {
        group.setId(groupId);
        groupService.updateGroup(group);
        return "redirect:/group?courseId=" + courseId;
    }

    @DeleteMapping("/delete")
    public String deleteGroup(@RequestParam("groupId") Long groupId, @RequestParam("courseId") Long courseId) {
        groupService.deleteGroup(groupId);
        return "redirect:/group?courseId=" + courseId;
    }
}

