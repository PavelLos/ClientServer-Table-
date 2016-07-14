package storage;

import model.student.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 21.06.2016.
 */
public class MyTableBase {
    private String firstName;
    private String secondName;
    private String middleName;
    private int numberOfCourse;
    private int numberOfGroup;
    private String programLanguage;
    private int numberOfSolvedProjects;
    private int numberOfProjects;
    private List<Student> studentList;

    public MyTableBase() {
        studentList = new ArrayList<>();
    }

    public void addToBase() {
        Student student = new Student(firstName, secondName, middleName);
        student.setProgramLanguage(new ProgramLanguage(programLanguage));
        student.setProject(new Project(numberOfProjects, numberOfSolvedProjects));
        student.setCourse(new Course(numberOfCourse));
        student.setGroup(new Group(numberOfGroup));
        this.studentList.add(student);
    }

    public void addToBase(Student student){
        studentList.add(student);
    }

    public void removeFromBase(List<Student> studentListForDelete) {
        for(int studentForDeleteNumber = 0; studentForDeleteNumber < studentListForDelete.size(); studentForDeleteNumber++){
            studentList.remove(studentListForDelete.get(studentForDeleteNumber));
        }
    }

    public void removeAll(){
        studentList.clear();
    }

    public List<Student> getStudentList(){
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public int getSizeOfBase() {
        return studentList.size();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setNumberOfCourse(int numberOfCourse) {
        this.numberOfCourse = numberOfCourse;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public void setProgramLanguage(String programLanguage) {
        this.programLanguage = programLanguage;
    }

    public void setNumberOfSolvedProjects(int numberOfSolvedProjects) {
        this.numberOfSolvedProjects = numberOfSolvedProjects;
    }

    public void setNumberOfProjects(int numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

}
