package windows.view;

import client.Client;
import constants.ConnectionConstants;
import constants.TableConstants;
import model.student.Student;
import table.model.StudentTableModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.List;

/**
 * Created by pasha on 22.06.2016.
 */
public class PageViewComponent {
    private Box pageBox;
    private JLabel currentPage;
    private JLabel numberOfStudents;
    private StudentTableModel tableModel;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public PageViewComponent(StudentTableModel model, Box box, Client client) {
        pageBox = box;
        tableModel = model;
        createPageView();
        outputStream = client.getOutputStream();
        inputStream = client.getInputStream();
    }

    private void createPageView(){
        currentPage = new JLabel();
        numberOfStudents = new JLabel();


        JButton firstPageButton = new JButton("First Page");
        firstPageButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outputStream.writeObject(ConnectionConstants.FIRST_PAGE);
                    tableModel.setStudentList((List<Student>) inputStream.readObject());

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();
            }
        });
        pageBox.add(firstPageButton);

        JButton previousPageButton = new JButton("Previous Page");
        previousPageButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outputStream.writeObject(ConnectionConstants.PREVIOUS_PAGE);
                    tableModel.setStudentList((List<Student>) inputStream.readObject());

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();
            }
        });
        pageBox.add(previousPageButton);

        JButton nextPageButton = new JButton("Next Page");
        nextPageButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outputStream.writeObject(ConnectionConstants.NEXT_PAGE);
                    tableModel.setStudentList((List<Student>) inputStream.readObject());

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();
            }
        });
        pageBox.add(nextPageButton);

        JButton lastPageButton = new JButton("Last Page");
        lastPageButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outputStream.writeObject(ConnectionConstants.LAST_PAGE);
                    tableModel.setStudentList((List<Student>) inputStream.readObject());

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();
            }
        });
        pageBox.add(lastPageButton);

        //String[] numberOfRows = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox numberOfRowsComboBox = new JComboBox(TableConstants.NUMBER_OF_COLUMNS);
        numberOfRowsComboBox.setSelectedIndex(1);
        numberOfRowsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               /* int numberOfRows = Integer.parseInt((String) numberOfRowsComboBox.getSelectedItem());
                pageTableBase.setNumberOfRows(numberOfRows);
                tableModel.fireTableDataChanged();
                numberOfStudentsAndPage();*/
            }
        });
        pageBox.add(numberOfRowsComboBox);
        numberOfStudentsAndPage();
        pageBox.add(Box.createHorizontalStrut(12));
        pageBox.add(numberOfStudents);
        pageBox.add(Box.createHorizontalStrut(12));
        pageBox.add(currentPage);
    }

    private void numberOfStudentsAndPage() {
        /*numberOfStudents.setText("Max: " + Integer.toString(myTableBase.getSizeOfBase()));
        currentPage.setText("Page: " + Integer.toString(pageTableBase.getCurrentPage()));*/

    }
}
