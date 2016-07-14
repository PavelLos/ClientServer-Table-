package windows.view;

import client.Client;
import constants.ConnectionConstants;
import model.student.Student;
import table.model.StudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static constants.TableConstants.DELETE_CRITERIA;

/**
 * Created by pasha on 22.06.2016.
 */
public class DeletePanel extends JFrame {
   /* private MyTableBase myTableBase;
    private MyTableBase tableBase;
    private BaseForPage baseForPage;*/
    private StudentTableModel myTableModel;

    private int selectedIndex;
    private String firstName, secondName, middleName;
    private int numberOfCourse;
    private int numberOfGroup;
    private int numberOfSolvedProjects;
    private int numberOfProjects;
    private String programLanguage;
    private List<Student> studentListForDelete;
    private Box boxPanel;
    private DialogBoxes dialogBoxes;
    private Box mainBox;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public DeletePanel(/*MyTableBase myTableBase1, BaseForPage bPage,*/ StudentTableModel tableModel, Client client) {
        setName("Введите данные студента");
        /*myTableBase = myTableBase1;
        tableBase = new MyTableBase();*/
        outputStream = client.getOutputStream();
        inputStream = client.getInputStream();
        studentListForDelete = new ArrayList<>();
        //baseForPage = bPage;
        myTableModel = tableModel;

        firstName = "";
        secondName = "";
        numberOfCourse = 1;
        numberOfGroup = 1;
        numberOfProjects = 1;
        numberOfSolvedProjects = 1;
        programLanguage = "Java";
        selectedIndex = 0;

        mainBox = Box.createVerticalBox();
        boxPanel = Box.createVerticalBox();
        dialogBoxes = new DialogBoxes(boxPanel);
        dialogBoxes.searchingBox(selectedIndex);
        Box deleteBox = Box.createHorizontalBox();
        JComboBox deleteCriteria = new JComboBox(DELETE_CRITERIA);
        deleteCriteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = deleteCriteria.getSelectedIndex();
                dialogBoxes.searchingBox(selectedIndex);
            }
        });
        deleteBox.add(deleteCriteria);
        Box okBox = Box.createHorizontalBox();
        JButton okButton = new JButton("Удалить");
        okButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outputStream.writeObject(ConnectionConstants.FIND_STUDENT);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                switch (selectedIndex) {
                    case 0: {
                        try {
                            outputStream.writeObject(ConnectionConstants.NAME_GROUP);
                            outputStream.writeObject(dialogBoxes.getNameTextField().getText());
                            outputStream.writeObject(dialogBoxes.getSecondNameTextField().getText());
                            outputStream.writeObject(dialogBoxes.getMiddleNameTextField().getText());
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getGroupComboBox().getSelectedItem()));
                            studentListForDelete = (List<Student>) inputStream.readObject();
                            outputStream.flush();
                            outputStream.writeObject(ConnectionConstants.DELETE_STUDENT);
                            outputStream.writeObject(studentListForDelete);
                            myTableModel.setStudentList((List<Student>) inputStream.readObject());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        myTableModel.fireTableDataChanged();
                        dialogBoxes.refreshBox();
                        JOptionPane.showMessageDialog(okButton, "Удалено: " + studentListForDelete.size() + " записей");
                        dialogBoxes.refreshBox();
                        break;
                    }
                    case 1: {
                        try {
                            outputStream.writeObject(ConnectionConstants.NAME_GROUP);
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getCourseComboBox().getSelectedItem()));
                            outputStream.writeObject((String) dialogBoxes.getProgramLanguageList().getSelectedItem());
                            studentListForDelete = (List<Student>) inputStream.readObject();
                            outputStream.flush();
                            outputStream.writeObject(ConnectionConstants.DELETE_STUDENT);
                            outputStream.writeObject(studentListForDelete);
                            myTableModel.setStudentList((List<Student>) inputStream.readObject());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        myTableModel.fireTableDataChanged();
                        JOptionPane.showMessageDialog(okButton, "Удалено: " + studentListForDelete.size() + " записей");
                        dialogBoxes.refreshBox();
                        break;
                    }
                    case 2: {
                        try {
                            outputStream.writeObject(ConnectionConstants.NAME_GROUP);
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getGroupComboBox().getSelectedItem()));
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getGroupComboBox().getSelectedItem()));
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getNumberOfSolvedProjectsList().getSelectedItem()));
                            studentListForDelete = (List<Student>) inputStream.readObject();
                            outputStream.flush();
                            outputStream.writeObject(ConnectionConstants.DELETE_STUDENT);
                            outputStream.writeObject(studentListForDelete);
                            myTableModel.setStudentList((List<Student>) inputStream.readObject());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        myTableModel.fireTableDataChanged();
                        JOptionPane.showMessageDialog(okButton, "Удалено: " + studentListForDelete.size() + " записей");
                        dialogBoxes.refreshBox();
                        break;
                    }
                    case 3: {
                        try {
                            outputStream.writeObject(ConnectionConstants.NAME_GROUP);
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getNumberOfProjectsList().getSelectedItem()));
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getCourseComboBox().getSelectedItem()));
                            studentListForDelete = (List<Student>) inputStream.readObject();
                            outputStream.flush();
                            outputStream.writeObject(ConnectionConstants.DELETE_STUDENT);
                            outputStream.writeObject(studentListForDelete);
                            myTableModel.setStudentList((List<Student>) inputStream.readObject());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        myTableModel.fireTableDataChanged();
                        JOptionPane.showMessageDialog(okButton, "Удалено: " + studentListForDelete.size() + " записей");
                        dialogBoxes.refreshBox();
                        break;
                    }
                }
            }
        });
        okBox.add(Box.createHorizontalGlue());
        okBox.add(okButton);
        okBox.add(Box.createHorizontalStrut(12));

        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(deleteBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(boxPanel);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(okBox);
        mainBox.add(Box.createVerticalStrut(12));
        add(mainBox);
        setSize(new Dimension(600, 270));
        setVisible(true);

    }
}