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
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String allCompanies(Model model) {
        model.addAttribute("companies", companyService.companyList());
        return "company/Companies";
    }

    @GetMapping("/getCompany")
    public String getCompanyAddPage(Model model) {
        model.addAttribute("company", new Company());
        return "company/addCompany";
    }

    @PostMapping("/postCompany")
    public String saveCompany(@ModelAttribute Company company) {
        companyService.saveCompany(company);
        return "redirect:/";
    }

    @GetMapping("/editCompany/{companyId}")
    public String getCompanyToUpdate(@PathVariable("companyId") Long companyId, Model model) {
        Company company = companyService.getCompanyById(companyId);
        model.addAttribute("company", company);
        return "company/updateCompany";
    }

    @PatchMapping("/updateCompany/{companyId}")
    public String updateCompany(@ModelAttribute Company company, @PathVariable("companyId") Long companyId) {
        company.setId(companyId);
        companyService.updateCompany(company);
        return "redirect:/";
    }

    @DeleteMapping("/deleteCompany/{companyId}")
    public String deleteCompany(@PathVariable("companyId") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/";
    }

}
