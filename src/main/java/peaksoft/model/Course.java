package peaksoft.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    private int duration;
    @ManyToOne(cascade = {PERSIST,DETACH,DETACH,REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToMany(cascade = {PERSIST,DETACH,MERGE,REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "course_group", joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groupList;
    @OneToOne(cascade = {PERSIST,DETACH,MERGE,REFRESH}, fetch = FetchType.EAGER, mappedBy = "course")
    private Teacher teacher;

    public Course() {
    }

    public Course(String courseName, int duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", duration=" + duration +
                '}';
    }

    public void addGroupToTheCourse(Group group) {
        if (groupList == null) {
            groupList = new ArrayList<>();
        }
        groupList.add(group);
    }
}
