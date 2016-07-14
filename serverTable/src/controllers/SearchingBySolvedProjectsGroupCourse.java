package controllers;


import model.student.Student;
import storage.MyTableBase;

import java.util.List;

/**
 * Created by pasha on 20.05.2016.
 */
public class SearchingBySolvedProjectsGroupCourse {
    private int numberOfCourse;
    private int numberOfGroup;
    private int numberOfSolvedProjects;
    private MyTableBase myTableBase;
    private MyTableBase tableBase;

    public SearchingBySolvedProjectsGroupCourse(int solvdProjects, int course, int group, MyTableBase myTableBaseIn, MyTableBase tableBase1) {
        numberOfSolvedProjects = solvdProjects;
        numberOfGroup = group;
        numberOfCourse = course;
        myTableBase = myTableBaseIn;
        tableBase = tableBase1;
        searchStudents();
    }

    private void searchStudents() {
        tableBase.removeAll();
        List<Student> students = myTableBase.getStudentList();
        for (Student student : students) {
            if (student.getProject().getNumberOfSolvedProjects() == numberOfSolvedProjects &&
                    student.getCourse().getNumberOfCourse() == numberOfCourse &&
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
