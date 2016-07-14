package model.student;

import java.io.Serializable;

/**
 * Created by pasha on 22.04.2016.
 */
public class Student  implements Serializable{
    private String studentFirstName;
    private String studentSecondName;
    private String studentMiddleName;
    private ProgramLanguage programLanguage;
    private Project project;
    private Course course;
    private Group group;

    public Student(String firstName, String secondtName, String middleName) {
        studentFirstName = firstName;
        studentSecondName = secondtName;
        studentMiddleName = middleName;
    }

    public Student(String studentFirstName, String studentSecondName, String studentMiddleName,
                   ProgramLanguage programLanguage, Project project, Course course, Group group) {
        this.studentFirstName = studentFirstName;
        this.studentSecondName = studentSecondName;
        this.studentMiddleName = studentMiddleName;
        this.programLanguage = programLanguage;
        this.project = project;
        this.course = course;
        this.group = group;
    }

    public String getStudentSecondName() {
        return studentSecondName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public ProgramLanguage getProgramLanguage() {
        return programLanguage;
    }

    public void setProgramLanguage(ProgramLanguage programLanguage) {
        this.programLanguage = programLanguage;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null)
            return false;

        if (!(getClass() == obj.getClass()))
            return false;
        else {
            Student tmp = (Student) obj;
            if (tmp.studentFirstName == this.studentFirstName &&
                    tmp.studentSecondName == this.studentSecondName &&
                    tmp.studentMiddleName == this.studentMiddleName &&
                    tmp.programLanguage.equals(this.programLanguage) &&
                    tmp.project.equals(this.project) &&
                    tmp.course.equals(this.course) &&
                    tmp.group.equals(this.group))
                return true;
            else
                return false;
        }
    }
}
