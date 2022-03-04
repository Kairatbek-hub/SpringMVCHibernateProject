package peaksoft.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "company", fetch = FetchType.LAZY)
    private List<Course> courseList;

    public Company() {
    }

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void setCourseList(List<Course> courseList) {
        if (this.courseList == null) {
            this.courseList = new ArrayList<>();
        }
        for (Course course : courseList) {
            this.courseList.add(course);
            course.setCompany(this);
        }
    }

    public void setCourseList(Course course) {
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        courseList.add(course);
        course.setCompany(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocatedCountry() {
        return locatedCountry;
    }

    public void setLocatedCountry(String locatedCountry) {
        this.locatedCountry = locatedCountry;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", CompanyName='" + companyName + '\'' +
                ", locatedCountry='" + locatedCountry + '\'' +
                '}';
    }
}
