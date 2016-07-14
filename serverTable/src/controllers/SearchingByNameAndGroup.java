package controllers;


import model.student.Student;
import storage.MyTableBase;

import java.util.List;

/**
 * Created by pasha on 20.05.2016.
 */
public class SearchingByNameAndGroup {
    private String firstName;
    private String secondName;
    private String middleName;
    private int numberOfGroup;
    private MyTableBase myTableBase;
    private MyTableBase tableBase;

    public SearchingByNameAndGroup(String name, String sName, String mName,int group, MyTableBase myTableBaseIn, MyTableBase tableBaseIn) {
        firstName = name;
        this.secondName = sName;
        middleName = mName;
        numberOfGroup = group;
        myTableBase = myTableBaseIn;
        tableBase = tableBaseIn;
        searchStudents();
    }

    private void searchStudents() {
        List<Student> students = myTableBase.getStudentList();
        for (Student student : students) {
            if (student.getStudentFirstName().equals(firstName) &&
                    student.getStudentSecondName().equals(secondName) &&
                    student.getStudentMiddleName().equals(middleName) &&
                    student.getGroup().getNumberOfGroup() == numberOfGroup) {
                tableBase.setFirstName(student.getStudentFirstName());
                tableBase.setSecondName(student.getStudentSecondName());
                tableBase.setMiddleName(student.getStudentMiddleName());
                tableBase.setNumberOfCourse(student.getCourse().getNumberOfCourse());
                tableBase.setNumberOfGroup(student.getGroup().getNumberOfGroup());
                tableBase.setNumberOfProjects(student.getProject().getNumberOfProjects());
                tableBase.setNumberOfSolvedProjects(student.getProject().getNumberOfSolvedProjects());
                tableBase.setProgramLanguage(student.getProgramLanguage().getNameOfProgramLanguage());
                tableBase.addToBase();
            }
        }

    }

    public MyTableBase getTableBase() {

        return tableBase;
    }

}
