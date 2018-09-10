package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class
Controller {
    LoadInfo saxReader;
    SaveInfo writerXML;
    ObservableList<Students> students;
    public Controller(ObservableList<Students> students){
        this.students = students;
    }
    public boolean open(File file, ObservableList<Students> list) {
        if (saxReader == null) saxReader = new LoadInfo();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, saxReader);
            while(list.size()!=0){
                list.remove(0);
            }
            saxReader.getStudentsList().forEach(studentInfo -> {
                list.add(studentInfo);
            });
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save(File file, ObservableList<Students> list) {
        if (writerXML == null)
            writerXML = new SaveInfo();
        writerXML.setFile(file);
        try {
            writerXML.write(list);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public ObservableList<Students> search(String text1, String text2)
    {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Students> dopList = FXCollections.observableArrayList();
            dopList = pattern.checkPattern(text1, text2, students);
        return dopList;
    }
    public ObservableList<Students> search(String text1, String text2, boolean chooseCourse, boolean chooseGroup)
    {
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Students> dopList = FXCollections.observableArrayList();
        dopList = pattern.checkPattern(text1, text2, students, chooseCourse, chooseGroup);
        return dopList;
    }
    public ObservableList<Students> delete(String text1, String text2){
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Students> dopList = FXCollections.observableArrayList();
        dopList = pattern.checkPattern(text1, text2, students);
        students.removeAll(dopList);
        return dopList;
    }
    public ObservableList<Students> delete(String text1, String text2, boolean chooseCourse, boolean chooseGroup){
        SearchAndDeleteModel pattern = new SearchAndDeleteModel();
        ObservableList<Students> dopList = FXCollections.observableArrayList();
        dopList = pattern.checkPattern(text1, text2, students, chooseCourse, chooseGroup);
        students.removeAll(dopList);
        return dopList;
    }
    public void add (Students someObj){
        students.add(someObj);
    }

    public void exit(){
        System.exit(0);
    }
}
