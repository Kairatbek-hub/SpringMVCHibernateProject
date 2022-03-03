package peaksoft.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "date_of_start")
    private Date dateOfStart;
    @Column(name = "date_of_finish")
    private Date dateOfFinish;
    @ManyToMany(cascade = {PERSIST,DETACH,MERGE,REFRESH}, fetch = FetchType.LAZY, mappedBy = "groupList")
    private List<Course> courseList;
    @OneToMany(cascade = {PERSIST,DETACH,MERGE,REFRESH}, fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> studentList;

    public Group() {
    }

    public Group(String groupName, Date dateOfStart, Date dateOfFinish) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
    }

    public void setStudentList(List<Student> studentList) {
        if (this.studentList == null) {
            this.studentList = new ArrayList<>();
        }
        for (Student student : studentList) {
            this.studentList.add(student);
            student.setGroup(this);
        }
    }

    public void setStudentList(Student student) {
        if (studentList == null) {
            studentList = new ArrayList<>();
        }
        studentList.add(student);
        student.setGroup(this);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfFinish() {
        return dateOfFinish;
    }

    public void setDateOfFinish(Date dateOfFinish) {
        this.dateOfFinish = dateOfFinish;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", dateOfStart=" + dateOfStart +
                ", dateOfFinish=" + dateOfFinish +
                '}';
    }

    public void addCourseToTheGroup(Course course) {
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        courseList.add(course);
    }
}