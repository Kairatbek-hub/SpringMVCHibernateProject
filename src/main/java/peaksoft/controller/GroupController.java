package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/{id}")
    public String getCompanyInf(@PathVariable Long id, Model model) {
        model.addAttribute("groups", groupService.groupList());
        return "company/Company";
    }
}

