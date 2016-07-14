package windows.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

import client.Client;
import constants.ConnectionConstants;
import model.student.Student;
import table.model.StudentTableModel;

/**
 * Created by pasha on 21.06.2016.
 */


public class MainWindow {
    private JFrame frame;
    private StudentTableModel tableModel;
    private JTable jTable;
    private JLabel currentPage;
    private JLabel numberOfStudents;
    private Box pageBox;
    private Box buttonBox;
    private List<Student> studentList;

    private Client client;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public MainWindow() {
        frame = new JFrame();
        frame.setTitle("StudentTable");
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        client = new Client();
        client.createSocket();
        inputStream = client.getInputStream();
        outputStream = client.getOutputStream();

        createTable();
        createEditTable();

        pageBox = Box.createHorizontalBox();
        PageViewComponent pageViewComponent = new PageViewComponent(tableModel, pageBox, client);
        frame.add(pageBox, BorderLayout.SOUTH);
        //createPageView();
        createMenu();
        frame.setVisible(true);
    }


    private void createEditTable(){
        buttonBox = Box.createHorizontalBox();

        JButton addDataToTableButton = new JButton("Add");
        addDataToTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPanel addPanel = new AddPanel(/*myTableBase, pageTableBase,*/ tableModel, client, inputStream, outputStream);
                addPanel.setVisible(true);
            }
        });
        buttonBox.add(addDataToTableButton);
        buttonBox.add(Box.createHorizontalStrut(12));

        JButton findButton = new JButton("Find");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindPanel findPanel = new FindPanel(client);
                findPanel.setVisible(true);

            }
        });
        buttonBox.add(findButton);
        buttonBox.add(Box.createHorizontalStrut(12));

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeletePanel deletePanel = new DeletePanel(tableModel, client);
                deletePanel.setVisible(true);
                //pageTableBase.refresh();
                tableModel.fireTableDataChanged();
                //numberOfStudentsAndPage();
            }
        });
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createHorizontalStrut(12));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*SaveTable saveTable = new SaveTable(myTableBase);
                saveTable.actionPerformed(e);
                //tableModel.fireTableDataChanged();*/
            }
        });
        buttonBox.add(saveButton);
        buttonBox.add(Box.createHorizontalStrut(12));

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*ReadFile readFile = new ReadFile(myTableBase);

                readFile.actionPerformed(e);
                pageTableBase.refresh();
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();*/
            }
        });
        buttonBox.add(loadButton);
        buttonBox.add(Box.createHorizontalStrut(12));

        frame.add(buttonBox, BorderLayout.NORTH);
    }

    private void createTable(){
        //myTableBase = new MyTableBase();
        //pageTableBase = new BaseForPage(myTableBase);
        studentList = new ArrayList<>();
        tableModel = new StudentTableModel(/*pageTableBase.getTableBase()*/studentList);
        jTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(jTable);
        jTable.setPreferredSize(new Dimension(frame.getWidth(), 600));
        frame.add(scrollPane, BorderLayout.CENTER);
        tableModel.fireTableDataChanged();
    }

    /*private void createPageView(){
        currentPage = new JLabel();
        numberOfStudents = new JLabel();
        pageBox = Box.createHorizontalBox();

        JButton firstPageButton = new JButton("First Page");
        firstPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.firstPage();
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();
            }
        });
        pageBox.add(firstPageButton);

        JButton previousPageButton = new JButton("Previous Page");
        previousPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.previousPage();
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();
            }
        });
        pageBox.add(previousPageButton);

        JButton nextPageButton = new JButton("Next Page");
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.nextPage();
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();
            }
        });
        pageBox.add(nextPageButton);

        JButton lastPageButton = new JButton("Last Page");
        lastPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageTableBase.lastPage();
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();
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
                numberOfStudentsAndPage();
            }
        });
        pageBox.add(numberOfRowsComboBox);
        numberOfStudentsAndPage();
        pageBox.add(Box.createHorizontalStrut(12));
        pageBox.add(numberOfStudents);
        pageBox.add(Box.createHorizontalStrut(12));
        pageBox.add(numberOfStudentsOnPage);
        frame.add(pageBox, BorderLayout.SOUTH);
    }*/

    public void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        menuBar.add(file);

        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');
        menuBar.add(edit);

        JMenuItem openFail = new JMenuItem("Open");
        openFail.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        openFail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*ReadFile readFile = new ReadFile(myTableBase);
                readFile.actionPerformed(e);
                pageTableBase.refresh();
                tableModel.fireTableDataChanged();*/
            }
        });
        file.add(openFail);

        JMenuItem saveFail = new JMenuItem("Save");
        saveFail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               /* SaveTable saveTable = new SaveTable(myTableBase);
                saveTable.actionPerformed(e);*/
            }
        });
        saveFail.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        file.add(saveFail);
        file.addSeparator();

        JMenuItem exitFail = new JMenuItem("Выход");
        exitFail.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        exitFail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(exitFail);

        JMenuItem addItem = new JMenuItem("Add");
        addItem.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*AddPanel addPanel = new AddPanel(myTableBase, pageTableBase, tableModel);
                addPanel.setVisible(true);
                pageTableBase.refresh();
                tableModel.fireTableDataChanged();*/
            }
        });
        edit.add(addItem);

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*DeletePanel deletePanel = new DeletePanel(myTableBase, pageTableBase, tableModel);
                deletePanel.setVisible(true);
                pageTableBase.refresh();
                tableModel.fireTableDataChanged();*/
            }
        });
        edit.add(deleteItem);

        JMenuItem findItem = new JMenuItem("Find");
        findItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        findItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*FindPanel findPanel = new FindPanel(myTableBase);
                findPanel.setVisible(true);*/

            }
        });
        edit.add(findItem);
    }



}



