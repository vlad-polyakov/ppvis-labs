package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadInfo extends DefaultHandler {
    enum Element {
        student, students, group, name, surname, FIO, course,works,allworks,language;
    }
    private ObservableList<Students> studentsList;
    private Students student;
    private Element thisElem;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("student")) {
            student = new Students();
            thisElem = Element.student;
            return;
        }
        if (qName.equals("works")) {
            thisElem = Element.works;
            return;
        }
        if (qName.equals("allworks")) {
            thisElem = Element.allworks;
            return;
        }
        if (qName.equals("language")) {
            thisElem = Element.language;
            return;
        }
        if (qName.equals("name")) {
            thisElem = Element.name;
            return;
        }
        if (qName.equals("surname")) {
            thisElem = Element.surname;
            return;
        }
        if (qName.equals("group")) {
            thisElem = Element.group;
            return;
        }
        if (qName.equals("FIO")) {
            thisElem = Element.FIO;
            return;
        }
        if (qName.equals("course")) {
            thisElem = Element.course;
            return;
        }

        if (qName.equals("students")) {
            studentsList = FXCollections.observableArrayList();
            thisElem = Element.students;
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if ("".equals(str)) return;
        if (thisElem == Element.works) {
            student.setWorks(str);
            return;
        }
        if (thisElem == Element.allworks) {
            student.setTotalWorks(str);
            return;
        }
        if (thisElem == Element.course) {
            student.setCourse(str);
            return;
        }
        if (thisElem == Element.name) {
            student.setName(str);
            return;
        }
        if (thisElem == Element.surname) {
            student.setSurname(str);
            return;
        }

        if (thisElem == Element.group) {
            student.setNumberOfGroup(str);
            return;
        }
        if (thisElem == Element.language) {
            student.setLangOfProgram(str);
            return;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (qName.equals("student")) {
            String name = student.getName();
            String surname = student.getSurname();
            student.setFio(name+ " "+surname);
            studentsList.add(student);
            student = null;
            return;
        }
    }

    public ObservableList<Students> getStudentsList() {
        return studentsList;
    }

}


