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
import java.util.*;
import java.util.List;

import static constants.TableConstants.SEARCH_CRITERIA;

/**
 * Created by pasha on 22.06.2016.
 */
public class FindPanel extends JFrame {
    private JTable jTable;
    /*private MyTableBase tableBase;
    private MyTableBase myTableBase;*/
    private int selectedIndex;
    //private BaseForPage pageTableBase;
    private StudentTableModel tableModel;
    private String name;
    private String secondName;
    private String middleName;
    private int numberOfCourse;
    private int numberOfGroup;
    private int numberOfSolvedProjects;
    private int numberOfProjects;
    private String programLanguage;
    private Box boxPanel;
    private Box pageBox;
    private List<Student> studentList;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private DialogBoxes dialogBoxes;

    public FindPanel(/*MyTableBase myTableBase1*/Client client) {
        setName("Find");

        outputStream = client.getOutputStream();
        inputStream = client.getInputStream();
        selectedIndex = 0;
        //myTableBase = myTableBase1;
        Box mainBox = Box.createVerticalBox();
        Box tableBox = Box.createHorizontalBox();
        boxPanel = Box.createVerticalBox();
        dialogBoxes = new DialogBoxes(boxPanel);

        studentList = new ArrayList<>();
        /*tableBase = new MyTableBase();
        pageTableBase = new BaseForPage(tableBase);*/
        tableModel = new StudentTableModel(studentList);
        jTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(jTable);
        jTable.setPreferredSize(new Dimension(700, 700));
        tableBox.add(scrollPane);
        setSize(500, 500);

        name = "";
        secondName = "";
        middleName = "";
        numberOfCourse = 1;
        numberOfGroup = 1;
        numberOfProjects = 1;
        numberOfSolvedProjects = 1;
        programLanguage = "Java";
        dialogBoxes.searchingBox(selectedIndex);

        Box findBox = Box.createHorizontalBox();
        JComboBox searchingCriteria = new JComboBox(SEARCH_CRITERIA);
        searchingCriteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = searchingCriteria.getSelectedIndex();
                dialogBoxes.searchingBox(selectedIndex);
            }
        });
        findBox.add(searchingCriteria);
        Box okBox = Box.createHorizontalBox();
        JButton okButton = new JButton("Найти");
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
                        tableModel.fireTableDataChanged();
                        numberOfGroup = Integer.parseInt((String) dialogBoxes.getGroupComboBox().getSelectedItem());
                        try {
                            outputStream.writeObject(ConnectionConstants.NAME_GROUP);
                            outputStream.writeObject(dialogBoxes.getNameTextField().getText());
                            outputStream.writeObject(dialogBoxes.getSecondNameTextField().getText());
                            outputStream.writeObject(dialogBoxes.getMiddleNameTextField().getText());
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getGroupComboBox().getSelectedItem()));
                            tableModel.setStudentList((List<Student>) inputStream.readObject());
                            outputStream.flush();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        tableModel.fireTableDataChanged();
                        dialogBoxes.refreshBox();
                        break;
                    }
                    case 1: {
                        tableModel.fireTableDataChanged();
                        try {
                            outputStream.writeObject(ConnectionConstants.COURSE_LANGUAGE);
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getCourseComboBox().getSelectedItem()));
                            outputStream.writeObject( dialogBoxes.getProgramLanguageList().getSelectedItem());
                            tableModel.setStudentList((List<Student>) inputStream.readObject());
                            outputStream.flush();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        tableModel.fireTableDataChanged();
                        dialogBoxes.refreshBox();
                        break;
                    }
                    case 2: {
                        tableModel.fireTableDataChanged();
                        try {
                            outputStream.writeObject(ConnectionConstants.GROUP_COURSE_PROJECTS);
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getGroupComboBox().getSelectedItem()));
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getGroupComboBox().getSelectedItem()));
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getNumberOfSolvedProjectsList().getSelectedItem()));
                            tableModel.setStudentList((List<Student>) inputStream.readObject());
                            outputStream.flush();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        tableModel.fireTableDataChanged();
                        dialogBoxes.refreshBox();
                        break;
                    }
                    case 3: {
                        tableModel.fireTableDataChanged();
                        try {
                            outputStream.writeObject(ConnectionConstants.PROJECTS_COURSE);
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getNumberOfProjectsList().getSelectedItem()));
                            outputStream.writeObject(Integer.parseInt((String) dialogBoxes.getCourseComboBox().getSelectedItem()));
                            tableModel.setStudentList((List<Student>) inputStream.readObject());
                            outputStream.flush();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        tableModel.fireTableDataChanged();
                        dialogBoxes.refreshBox();
                        break;
                    }
                }
            }
        });
        okBox.add(Box.createHorizontalGlue());
        okBox.add(okButton);
        okBox.add(Box.createHorizontalStrut(12));

        pageBox = Box.createHorizontalBox();
        PageViewComponent pageViewComponent = new PageViewComponent(tableModel, pageBox, client);
        //pageView();

        mainBox.add(findBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(boxPanel);
        mainBox.add(okBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(tableBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(pageBox);
        mainBox.add(Box.createVerticalStrut(12));
        add(mainBox);
        setVisible(true);
    }

    /*void pageView() {
        JButton firstPageButton = new JButton("First Page");
        firstPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.firstPage();
                tableModel.fireTableDataChanged();
            }
        });
        pageBox.add(firstPageButton);
        JButton previousPageButton = new JButton("Previous Page");
        previousPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.previousPage();
                tableModel.fireTableDataChanged();

            }
        });
        pageBox.add(previousPageButton);
        JButton nextPageButton = new JButton("Next Page");
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.nextPage();
                tableModel.fireTableDataChanged();

            }
        });
        pageBox.add(nextPageButton);
        JButton lastPageButton = new JButton("Last Page");
        lastPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.lastPage();
                tableModel.fireTableDataChanged();
            }
        });
        pageBox.add(lastPageButton);
        String[] numberOfRows = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox numberOfRowsComboBox = new JComboBox(numberOfRows);
        numberOfRowsComboBox.setSelectedIndex(1);
        numberOfRowsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfRows = Integer.parseInt((String) numberOfRowsComboBox.getSelectedItem());
                pageTableBase.setNumberOfRows(numberOfRows);
                tableModel.fireTableDataChanged();

            }
        });

        pageBox.add(numberOfRowsComboBox);
    }*/
}
