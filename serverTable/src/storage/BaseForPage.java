package storage;

import model.student.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 22.06.2016.
 */
public class BaseForPage {
    private MyTableBase tableBase;
    private MyTableBase myTableBase;

    private int numberOfRows; //кол-во строк на странице
    private int numberOfPages; //кол-во страниц
    private int startRow; //начало страницы
    private int rowsOnTheLatestPage; // кол-во студентов на последней странице
    private int currentPage; //текущая страница
    private boolean latestPageFlag; // последняя страница
    private int sizeOfBase;

    public BaseForPage(MyTableBase myTable) {
        myTableBase = myTable;
        tableBase = new MyTableBase();
        numberOfRows = 2;
        refresh();
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
        refresh();
    }

    private void dataChange(int startRow, int numberOfRows) {
        List<Student> students = new ArrayList<>();
        students = myTableBase.getStudentList();
        int studentNumber = 0;
        for (int i = 0; i < myTableBase.getStudentList().size(); i++) {
            if (i >= startRow && studentNumber < numberOfRows) {
                tableBase.setFirstName(students.get(i).getStudentFirstName());
                tableBase.setSecondName(students.get(i).getStudentSecondName());
                tableBase.setMiddleName(students.get(i).getStudentMiddleName());
                tableBase.setNumberOfCourse(students.get(i).getCourse().getNumberOfCourse());
                tableBase.setNumberOfGroup(students.get(i).getGroup().getNumberOfGroup());
                tableBase.setNumberOfProjects(students.get(i).getProject().getNumberOfProjects());
                tableBase.setNumberOfSolvedProjects(students.get(i).getProject().getNumberOfSolvedProjects());
                tableBase.setProgramLanguage(students.get(i).getProgramLanguage().getNameOfProgramLanguage());
                tableBase.addToBase();
                studentNumber++;
            }
        }
    }

    public void refresh() {
        sizeOfBase = myTableBase.getSizeOfBase();
        numberOfPages = sizeOfBase / numberOfRows;
        if (sizeOfBase % numberOfRows != 0) {
            numberOfPages += 1;
        }
        currentPage = 1;
        startRow = 0;
        rowsOnTheLatestPage = sizeOfBase % numberOfRows;
        if (numberOfPages == 1) {
            latestPageFlag = true;
            tableBase.removeAll();
            //tableBase = new MyTableBase();
            dataChange(startRow, sizeOfBase);
        } else {
            latestPageFlag = false;
            tableBase.removeAll();
            //tableBase = new MyTableBase();
            dataChange(startRow, numberOfRows);
        }
    }

    public void nextPage() {
        if (!latestPageFlag) {
            tableBase.removeAll();
            //tableBase = new MyTableBase();
            startRow += numberOfRows;
            dataChange(startRow, numberOfRows);
            currentPage++;
            if (currentPage == numberOfPages)
                latestPageFlag = true;
        }
    }

    public void previousPage() {
        if (currentPage != 1) {
            tableBase.removeAll();
            //tableBase = new MyTableBase();
            startRow -= numberOfRows;
            dataChange(startRow, numberOfRows);
            currentPage--;
            latestPageFlag = false;
        }
    }

    public void firstPage() {

        while (currentPage != 1)
            previousPage();
    }

    public void lastPage() {
        while (!latestPageFlag) {
            nextPage();
        }

    }

    public MyTableBase getTableBase() {
        return tableBase;
    }

    public void setTableBase(MyTableBase tableBase) {
        this.tableBase = tableBase;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<Student> getStudentList(){
        return tableBase.getStudentList();
    }

    public int getSizeOfPageBase() {
        return tableBase.getSizeOfBase();
    }
}
