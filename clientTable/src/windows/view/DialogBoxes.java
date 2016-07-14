package windows.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pasha on 21.06.2016.
 */
public class DialogBoxes {
    private Box boxPanel;
    private JTextField nameTextField;
    private JTextField secondNameTextField;
    private JTextField middleNameTextField;
    private JComboBox courseComboBox;
    private JComboBox groupComboBox;
    private JComboBox numberOfSolvedProjectsList;
    private JComboBox numberOfProjectsList;
    private JComboBox programLanguageList;


    public DialogBoxes(Box box) {
        boxPanel = box;
    }

    public void nameBox() {
        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("Имя:");
        nameLabel.setPreferredSize(new Dimension(200, nameLabel.getHeight()));
        nameTextField = new JTextField(20);
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(6));
        nameBox.add(nameTextField);
        boxPanel.add(nameBox);
        boxPanel.add(Box.createVerticalStrut(12));
    }

    public void secondNameBox() {
        Box secondNameBox = Box.createHorizontalBox();
        JLabel secondNameLabel = new JLabel("Фамилия:");
        secondNameLabel.setPreferredSize(new Dimension(200, secondNameLabel.getHeight()));
        secondNameTextField = new JTextField(20);
        secondNameBox.add(secondNameLabel);
        secondNameBox.add(Box.createHorizontalStrut(6));
        secondNameBox.add(secondNameTextField);
        boxPanel.add(secondNameBox);
        boxPanel.add(Box.createVerticalStrut(12));
    }

    public void middleNameBox() {
        Box middleNameBox = Box.createHorizontalBox();
        JLabel middleNameLabel = new JLabel("Отчество:");
        middleNameLabel.setPreferredSize(new Dimension(200, middleNameBox.getHeight()));
        middleNameTextField = new JTextField(20);
        middleNameBox.add(middleNameLabel);
        middleNameBox.add(Box.createHorizontalStrut(6));
        middleNameBox.add(middleNameTextField);
        boxPanel.add(middleNameBox);
        boxPanel.add(Box.createVerticalStrut(12));
    }

    public void courseBox() {
        Box courseBox = Box.createHorizontalBox();
        JLabel courseLabel = new JLabel("Курс:");
        courseLabel.setPreferredSize(new Dimension(200, courseLabel.getHeight()));
        String[] courses = {"1", "2", "3", "4", "5"};
        courseComboBox = new JComboBox(courses);
        courseBox.add(courseLabel);
        courseBox.add(Box.createHorizontalStrut(6));
        courseBox.add(courseComboBox);
        boxPanel.add(courseBox);
        boxPanel.add(Box.createVerticalStrut(12));
    }

    public void groupBox() {
        Box groupBox = Box.createHorizontalBox();
        JLabel groupLabel = new JLabel("Группа:");
        groupLabel.setPreferredSize(new Dimension(200, groupLabel.getHeight()));
        String[] groups = {"1", "2", "3"};
        groupComboBox = new JComboBox(groups);
        groupBox.add(groupLabel);
        groupBox.add(Box.createHorizontalStrut(6));
        groupBox.add(groupComboBox);
        boxPanel.add(groupBox);
        boxPanel.add(Box.createVerticalStrut(12));
    }

    public void projectsBox() {
        Box numberOfProjectsBox = Box.createHorizontalBox();
        JLabel numberOfProjectsLabel = new JLabel("Общее число работ:");
        numberOfProjectsLabel.setPreferredSize(new Dimension(200, numberOfProjectsLabel.getHeight()));
        String[] numberOfProjectsMas = new String[99];
        for (int i = 1; i < 100; i++) {
            numberOfProjectsMas[i - 1] = Integer.toString(i);
        }
        numberOfProjectsList = new JComboBox(numberOfProjectsMas);
        numberOfProjectsBox.add(numberOfProjectsLabel);
        numberOfProjectsBox.add(Box.createHorizontalStrut(6));
        numberOfProjectsBox.add(numberOfProjectsList);
        boxPanel.add(numberOfProjectsBox);
        boxPanel.add(Box.createVerticalStrut(12));
    }

    public void solvedProjectsBox() {
        Box numberOfSolvedProjectsBox = Box.createHorizontalBox();
        JLabel numberOfSolvedProjectsLabel = new JLabel("Количество выполненных работ:");
        numberOfSolvedProjectsLabel.setPreferredSize(new Dimension(200, numberOfSolvedProjectsLabel.getHeight()));
        String[] numberOfSolvedProjectsMas = new String[99];
        for (int i = 1; i < 100; i++) {
            numberOfSolvedProjectsMas[i - 1] = Integer.toString(i);
        }
        numberOfSolvedProjectsList = new JComboBox(numberOfSolvedProjectsMas);
        numberOfSolvedProjectsBox.add(numberOfSolvedProjectsLabel);
        numberOfSolvedProjectsBox.add(Box.createHorizontalStrut(6));
        numberOfSolvedProjectsBox.add(numberOfSolvedProjectsList);
        boxPanel.add(numberOfSolvedProjectsBox);
        boxPanel.add(Box.createVerticalStrut(12));
    }

    public void languageBox() {
        Box languageBox = Box.createHorizontalBox();
        JLabel languageLabel = new JLabel("Язык программирования:");
        languageLabel.setPreferredSize(new Dimension(200, languageLabel.getHeight()));
        String[] languages = {"Java", "C++", "C#", "JavaScript", "Python", "Ruby"};
        programLanguageList = new JComboBox(languages);
        languageBox.add(languageLabel);
        languageBox.add(Box.createHorizontalStrut(6));
        languageBox.add(programLanguageList);
        boxPanel.add(languageBox);
        boxPanel.add(Box.createVerticalStrut(12));
    }

    public void searchingBox(int selectedIndex) {
        switch (selectedIndex) {
            // TODO Constants
            case 0: {
                boxPanel.removeAll();
                boxPanel.revalidate();
                nameBox();
                secondNameBox();
                middleNameBox();
                groupBox();
                break;
            }
            case 1: {
                boxPanel.removeAll();
                boxPanel.revalidate();
                courseBox();
                languageBox();
                break;
            }
            case 2: {
                boxPanel.removeAll();
                boxPanel.revalidate();
                solvedProjectsBox();
                courseBox();
                groupBox();
                break;
            }
            case 3: {
                boxPanel.removeAll();
                boxPanel.revalidate();
                courseBox();
                projectsBox();
                break;
            }
        }
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getMiddleNameTextField() {
        return middleNameTextField;
    }

    public JTextField getSecondNameTextField() {
        return secondNameTextField;
    }

    public JComboBox getCourseComboBox() {
        return courseComboBox;
    }

    public JComboBox getGroupComboBox() {
        return groupComboBox;
    }

    public JComboBox getNumberOfSolvedProjectsList() {
        return numberOfSolvedProjectsList;
    }

    public JComboBox getNumberOfProjectsList() {
        return numberOfProjectsList;
    }

    public JComboBox getProgramLanguageList() {
        return programLanguageList;
    }

    public void refreshBox() {
        if (nameTextField != null)
            nameTextField.setText("");
        if (middleNameTextField != null)
            middleNameTextField.setText("");
        if (secondNameTextField != null)
            secondNameTextField.setText("");
        if (courseComboBox != null)
            courseComboBox.setSelectedIndex(0);
        if (groupComboBox != null)
            groupComboBox.setSelectedIndex(0);
        if (numberOfSolvedProjectsList != null)
            numberOfSolvedProjectsList.setSelectedIndex(0);
        if (numberOfProjectsList != null)
            numberOfProjectsList.setSelectedIndex(0);
        if (programLanguageList != null)
            programLanguageList.setSelectedIndex(0);
    }
}
