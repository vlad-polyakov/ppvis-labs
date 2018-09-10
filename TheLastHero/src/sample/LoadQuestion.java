package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadQuestion extends DefaultHandler {
    enum Element {
        questions, question, name, first, second, third, fourth, correct;
    }
    private ObservableList<Question> studentsList;
    private ObservableList<String> answers;
    private Question question;
    private Element thisElem;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("question")) {
            question = new Question();
            answers = FXCollections.observableArrayList();
            thisElem = Element.question;
            return;
        }
        if (qName.equals("name")) {
            thisElem = Element.name;
            return;
        }
        if (qName.equals("first")) {
            thisElem = Element.first;
            return;
        }
        if (qName.equals("second")) {
            thisElem = Element.second;
            return;
        }
        if (qName.equals("third")) {
            thisElem = Element.third;
            return;
        }
        if (qName.equals("fourth")) {
            thisElem = Element.fourth;
            return;
        }
        if (qName.equals("correct")) {
            thisElem = Element.correct;
            return;
        }


        if (qName.equals("questions")) {
            studentsList = FXCollections.observableArrayList();
            thisElem = Element.questions;
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if ("".equals(str)) return;
        if (thisElem == Element.name) {
            question.setQuestion(str);
            return;
        }
        if (thisElem == Element.first) {
            answers.add(str);
            return;
        }
        if (thisElem == Element.second) {
            answers.add(str);
            return;
        }
        if (thisElem == Element.third) {
            answers.add(str);
            return;
        }
        if (thisElem == Element.fourth) {
            answers.add(str);
            return;
        }

        if (thisElem == Element.correct) {
            question.setCorrectAnswer(str);
            return;
        }

    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (qName.equals("question")) {
            question.setAnswers(answers);
            studentsList.add(question);
            question = null;
            answers.removeAll();
            return;
        }
    }
    public ObservableList getList(){
        return studentsList;
    }
}
