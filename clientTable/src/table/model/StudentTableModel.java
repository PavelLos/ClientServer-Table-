package table.model;

import constants.TableConstants;
import model.student.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by pasha on 21.06.2016.
 */
public class StudentTableModel extends AbstractTableModel {
    //private MyTableBase myTableBase;
    private List<Student> studentList;


    public StudentTableModel(/*MyTableBase base*/List<Student> stud) {
        //myTableBase = base;
        //studentList = myTableBase.getStudentList();
        studentList = stud;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return TableConstants.COLUMN_NAMES[columnIndex];
    }

    @Override
    public int getRowCount() {
        return studentList.size();

    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return studentList.get(rowIndex).getStudentFirstName() + " " + studentList.get(rowIndex).getStudentSecondName() + " " + studentList.get(rowIndex).getStudentMiddleName();
            case 1:
                return studentList.get(rowIndex).getCourse().getNumberOfCourse();
            case 2:
                return studentList.get(rowIndex).getGroup().getNumberOfGroup();
            case 3:
                return studentList.get(rowIndex).getProject().getNumberOfProjects();
            case 4:
                return studentList.get(rowIndex).getProject().getNumberOfSolvedProjects();
            case 5:
                return studentList.get(rowIndex).getProgramLanguage().getNameOfProgramLanguage();
            default:
                return "";
        }
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setStudent(Student stud){
        studentList.add(stud);
    }

    public void cleanStudentList(){
        studentList.clear();
    }
}
