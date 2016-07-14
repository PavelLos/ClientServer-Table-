package controllers;


import model.student.Student;
import storage.MyTableBase;

import java.util.List;

/**
 * Created by pasha on 20.05.2016.
 */
public class SearchingByCourseAndProgramLanguage {
    private int numberOfCourse;
    private String programLanguage;
    private MyTableBase myTableBase;
    private MyTableBase tableBase;

    public SearchingByCourseAndProgramLanguage(int course, String language, MyTableBase tableBaseSecond, MyTableBase tableBase1) {
        numberOfCourse = course;
        programLanguage = language;
        myTableBase = tableBaseSecond;
        tableBase = tableBase1;
        searchStudents();
    }

    private void searchStudents() {
        tableBase.removeAll();
        List<Student> students = myTableBase.getStudentList();
        for (Student student : students) {
            if (student.getProgramLanguage().getNameOfProgramLanguage().equals(programLanguage) &&
                    student.getCourse().getNumberOfCourse() == numberOfCourse) {
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
