package sample;

import javafx.collections.ObservableList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Controller {
    private Hero hero;
    private Players players;
    private Question question;
    private LoadQuestion saxReader;
    private MainWindow mainWindow;
    public Controller(Hero hero, Players players, Question question, MainWindow mainWindow){
        this.hero = hero;
        this.players = players;
        this.question = question;
        this.mainWindow = mainWindow;
    }

    public void addQuestion(File file){
        mainWindow.clearSpace();
        if (saxReader == null) saxReader = new LoadQuestion();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, saxReader);
            ObservableList<Question> questions = saxReader.getList();
            final Random random = new Random();
            int numberOfQuestions = questions.size();
            mainWindow.createQuestionBox(questions.get(random.nextInt(numberOfQuestions)).getQuestion(),questions.get(random.nextInt(numberOfQuestions)).getAnswers());
            System.out.println(numberOfQuestions-1);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }
    }

}
