package windows.view;

import client.Client;
import constants.ConnectionConstants;
import model.student.*;
import table.model.StudentTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Created by pasha on 21.06.2016.
 */
public class AddPanel extends JFrame {
    private Student student;
    private StudentTableModel studentTableModel;
    //private BaseForPage baseForPage;
    private String firstName;
    private String secondName;
    private String middleName;
    private int numberOfCourse;
    private int numberOfGroup;
    private int numberOfSolvedProjects;
    private int numberOfProjects;
    private String programLanguage;
    private DialogBoxes dialogBoxes;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    public AddPanel(StudentTableModel tableModel, Client client, ObjectInputStream in, ObjectOutputStream out) {

        studentTableModel = tableModel;
        setName("Введите данные студента");

        Box boxPanel = Box.createVerticalBox();
        firstName = "";
        secondName = "";
        middleName = "";
        numberOfCourse = 1;
        numberOfGroup = 1;
        numberOfProjects = 1;
        numberOfSolvedProjects = 1;
        programLanguage = "Java";

        dialogBoxes = new DialogBoxes(boxPanel);
        dialogBoxes.nameBox();
        dialogBoxes.secondNameBox();
        dialogBoxes.middleNameBox();
        dialogBoxes.courseBox();
        dialogBoxes.groupBox();
        dialogBoxes.projectsBox();
        dialogBoxes.solvedProjectsBox();
        dialogBoxes.languageBox();

        outputStream = out;
        inputStream = in;

        Box okBox = Box.createHorizontalBox();
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent e) {
                firstName = dialogBoxes.getNameTextField().getText();
                secondName = dialogBoxes.getSecondNameTextField().getText();
                middleName = dialogBoxes.getMiddleNameTextField().getText();
                numberOfGroup = Integer.parseInt((String) dialogBoxes.getGroupComboBox().getSelectedItem());
                numberOfCourse = Integer.parseInt((String) dialogBoxes.getCourseComboBox().getSelectedItem());
                numberOfSolvedProjects = Integer.parseInt((String) dialogBoxes.getNumberOfSolvedProjectsList().getSelectedItem());
                numberOfProjects = Integer.parseInt((String) dialogBoxes.getNumberOfProjectsList().getSelectedItem());
                programLanguage = (String) dialogBoxes.getProgramLanguageList().getSelectedItem();

                student = new Student(firstName, secondName, middleName, new ProgramLanguage(programLanguage),
                        new Project(numberOfProjects, numberOfSolvedProjects), new Course(numberOfCourse), new Group(numberOfGroup));

                try {
                    outputStream.writeObject(ConnectionConstants.ADD_STUDENT);
                    outputStream.writeObject(student);
                    /*outputStream.flush();
                    outputStream.flush();
                    tableModel.cleanStudentList();*/
                    //List<Student> studentList = (List<Student>) inputStream.readObject();
                    tableModel.setStudentList((List<Student>) inputStream.readObject());
                    //outputStream.flush();
                    tableModel.fireTableDataChanged();
                    outputStream.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                /*try {
                    outputStream.writeObject(ConnectionConstants.CHEAK);
                    List<Student> studentList = new ArrayList<Student>();
                    studentList = (List<Student>) inputStream.readObject();
                    tableModel.setStudentList(studentList);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }*/

                dialogBoxes.refreshBox();

            }
        });
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        okBox.add(Box.createHorizontalGlue());
        okBox.add(okButton);
        okBox.add(Box.createHorizontalStrut(12));
        okBox.add(cancelButton);
        boxPanel.add(okBox);
        boxPanel.add(Box.createVerticalStrut(12));

        boxPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(boxPanel);
        pack();
        setResizable(false);
        setPreferredSize(new Dimension(600, boxPanel.getHeight()));
        setSize(new Dimension(600, boxPanel.getHeight() + 20));
    }

}
