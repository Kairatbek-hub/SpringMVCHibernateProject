package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;

@Controller
@RequestMapping("/")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("companies", companyService.companyList());
        return "company/Companies";
    }
    @GetMapping("/getCompany")
    public String getCompanyAddPage(Model model){
        model.addAttribute("company", new Company());
        return "company/addCompany";
    }
    @PostMapping("/postCompany")
    public String companySave(@ModelAttribute Company company, Model model){
        companyService.saveCompany(company);
        model.addAttribute("companies", companyService.companyList());
        return "company/Companies";
    }
    @GetMapping("/edit/{id}")
    public String getCompanyToUpdate(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getById(id);
        model.addAttribute("company", company);
        return "company/updateCompany";
    }

    @PatchMapping ("/update/{id}")
    public String updateCompany(@ModelAttribute Company company, Model model){
        companyService.updateCompany(company);
        model.addAttribute("companies", companyService.companyList());
        return "company/Companies";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id, Model model) {
        companyService.deleteCompany(id);
        model.addAttribute("companies", companyService.companyList());
        return "company/Companies";
    }

}
