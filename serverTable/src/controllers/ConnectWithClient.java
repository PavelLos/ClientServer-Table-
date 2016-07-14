package controllers;

import constants.ConnectionConstants;
import model.student.Student;
import org.xml.sax.SAXException;
import server.Server;
import storage.BaseForPage;
import storage.MyTableBase;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasha on 22.06.2016.
 */
public class ConnectWithClient extends Thread {
    private String message;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private MyTableBase myTableBase;
    private BaseForPage baseForPage;

    private JTextArea textArea;

    private Socket server;

    public ConnectWithClient(JTextArea area, Socket serverSocket) throws IOException {
        myTableBase = new MyTableBase();
        baseForPage = new BaseForPage(myTableBase);
        textArea = area;
        server = serverSocket;
        outputStream = new ObjectOutputStream(server.getOutputStream());
        inputStream = new ObjectInputStream(server.getInputStream());
        outputStream.flush();

        start();
    }


    @Override
    public void run() {
        try {
            while (true) {
                outputStream.flush();
                message = (String) inputStream.readObject();
                textArea.append("\n" + message);
                switch (message) {
                    case ConnectionConstants.ADD_STUDENT:
                        myTableBase.addToBase((Student) inputStream.readObject());
                        baseForPage.refresh();
                        outputStream.reset();
                        outputStream.writeObject(/*baseForPage.getStudentList()*/ baseForPage.getStudentList());
                        break;
                   /* case ConnectionConstants.CHEAK:
                        outputStream.reset();
                        outputStream.writeObject(*//*baseForPage.getStudentList()*//* myTableBase.getStudentList());
                        break;*/
                    case ConnectionConstants.FIND_STUDENT:
                        MyTableBase tableBase = new MyTableBase();
                        BaseForPage pageBase = new BaseForPage(tableBase);
                        switch ((String) inputStream.readObject()) {
                            case ConnectionConstants.NAME_GROUP:
                                SearchingByNameAndGroup searchingByNameAndGroup = new SearchingByNameAndGroup(
                                        (String) inputStream.readObject(), (String) inputStream.readObject(),
                                        (String) inputStream.readObject(),
                                        (int) inputStream.readObject(), myTableBase, tableBase);
                                pageBase.refresh();
                                outputStream.writeObject(pageBase.getStudentList());
                                break;
                            case ConnectionConstants.COURSE_LANGUAGE:
                                SearchingByCourseAndProgramLanguage searchingByCourseAndProgramLanguage = new SearchingByCourseAndProgramLanguage(
                                        (int) inputStream.readObject(), (String) inputStream.readObject(),
                                        myTableBase, tableBase);
                                pageBase.refresh();
                                outputStream.writeObject(pageBase.getStudentList());
                                break;
                            case ConnectionConstants.GROUP_COURSE_PROJECTS:
                                SearchingBySolvedProjectsGroupCourse searchingBySolvedProjectsGroupCourse = new SearchingBySolvedProjectsGroupCourse(
                                        (int) inputStream.readObject(), (int) inputStream.readObject(),
                                        (int) inputStream.readObject(), myTableBase, tableBase);
                                pageBase.refresh();
                                outputStream.writeObject(pageBase.getStudentList());
                                break;
                            case ConnectionConstants.PROJECTS_COURSE:
                                SearchingByProjectsAndCourse searchingByProjectsAndCourse = new SearchingByProjectsAndCourse(
                                        (int) inputStream.readObject(),
                                        (int) inputStream.readObject(), myTableBase, tableBase);
                                pageBase.refresh();
                                outputStream.writeObject(pageBase.getStudentList());
                        }
                        break;
                    case ConnectionConstants.DELETE_STUDENT:
                        myTableBase.removeFromBase((List<Student>) inputStream.readObject());
                        baseForPage.refresh();
                        outputStream.writeObject((List<Student>) baseForPage.getStudentList());

                    case ConnectionConstants.FIRST_PAGE:
                        baseForPage.firstPage();
                        outputStream.reset();
                        outputStream.writeObject((List<Student>) baseForPage.getStudentList());
                        break;

                    case ConnectionConstants.PREVIOUS_PAGE:
                        baseForPage.previousPage();
                        outputStream.reset();
                        outputStream.writeObject((List<Student>) baseForPage.getStudentList());
                        break;

                    case ConnectionConstants.NEXT_PAGE:
                        baseForPage.nextPage();
                        outputStream.reset();
                        outputStream.writeObject((List<Student>) baseForPage.getStudentList());
                        break;

                    case ConnectionConstants.LAST_PAGE:
                        baseForPage.lastPage();
                        outputStream.reset();
                        outputStream.writeObject((List<Student>) baseForPage.getStudentList());
                        break;
                    default:
                        break;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            textArea.append("\nClient disconnected");
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
