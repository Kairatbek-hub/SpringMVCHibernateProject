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
        return "group/Groups";
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
        return "group/Groups";
    }
    @GetMapping("/edit")
    public String getGroupToUpdate(@RequestParam("groupId") Long id, Model model) {
        Group group = groupService.getGroupId(id);
        model.addAttribute("group", group);
        return "group/updateGroup";
    }

    @PostMapping ("/update")
    public String updateGroup(@ModelAttribute Group group, Model model){
        groupService.updateGroup(group);
//        model.addAttribute("group", groupService.groupList());
//       return "group/Groups";
        return "redirect:/group";
    }

    @GetMapping("/delete")
    public String deleteGroup(@RequestParam("groupId") Long id, Model model) {
        System.out.println(id);
        groupService.deleteGroup(id);
        model.addAttribute("group", groupService.groupList());
//        return "group/Groups";
        return "redirect:/group";
    }
}

