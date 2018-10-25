package sample;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Controller implements Runnable {
    private Hero hero;
    private Question question;
    private LoadQuestion saxReader;
    private MainWindow mainWindow;
    private BonusLevel bonusLevel;
    private SkipQuestion skipQuestion;
    private RoundInfo round;
    private Game game;
    private DeleteQuestions deleteQuestions;

    public Controller(Hero hero, Question question, MainWindow mainWindow, BonusLevel bonusLevel, RoundInfo round, Game game) {
        this.hero = hero;
        this.question = question;
        this.mainWindow = mainWindow;
        this.bonusLevel = bonusLevel;
        this.round = round;
        this.game = game;
    }

    public void turnOnSkip() {
        skipQuestion.setSkipBool(true);
        checkAnswer();
    }

    public void addQuestion() {
        mainWindow.clearSpace();
        mainWindow.updateLabel();

        if (saxReader == null) saxReader = new LoadQuestion();
        try {
            if (game.getQuestionNumber() == 1) {
                skipQuestion = new SkipQuestion();
                deleteQuestions = new DeleteQuestions();
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                File file = new File("file.xml");
                parser.parse(file, saxReader);
                game.setQuestions(saxReader.getList());
            }
            if (game.getQuestionNumber() == game.getNumberOfRounds()) {
                mainWindow.theEndWindow(hero.getHeroCash() + 10000000);
                System.exit(0);
            }
            question = game.chooseQuestion();
            mainWindow.createQuestionBox(question);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }

    }

    public void checkAnswer() {
        if (!skipQuestion.isSkipBool()) {
            if (round.getChosenVariant().equals(question.getCorrectAnswer())) {
                mainWindow.createMessageWin();
                Thread thread = new Thread(this);
                answerQuestion(thread);
                hero.setHeroCash(hero.getHeroCash() + round.getRoundCash() * game.getLosersNumber());
                round.setRoundCash(round.getRoundCash() + 20000);
                round.setRound(round.getRound() + 1);
                game.setQuestionNumber(game.getQuestionNumber() + 1);
                mainWindow.createContinueBox();
                game.getQuestions().remove(question);
            } else {
                mainWindow.createMessageLose();
                System.exit(0);
            }
        } else {
            game.setQuestionNumber(game.getQuestionNumber() + 1);
            mainWindow.createContinueBox();
            skipQuestion.setSkipBool(false);
            skipQuestion.setUsed(true);
            game.getQuestions().remove(question);
        }
    }

    public void playBonusGame(Button button) {
        this.bonusLevel = mainWindow.getBonusLevel();
        mainWindow.writeInfoIntoButton(bonusLevel);
        if (bonusLevel.getButtonMap().get(button) < bonusLevel.getValueToCheck()) {
            hero.setHeroCash(hero.getHeroCash() / 2);
            mainWindow.updateLabel();
            mainWindow.createMessageLose();
        }
        if (bonusLevel.getButtonMap().get(button) > bonusLevel.getValueToCheck()) {
            hero.setHeroCash(hero.getHeroCash() * 2);
            mainWindow.updateLabel();
            mainWindow.createMessageWin();
        }

    }

    public void answerQuestion(Thread thread) {
        game.findLosers(question);
        thread.start();

    }

    @Override
    public void run() {
        for (int index = 0; index < game.getNumberOfPlayers(); index++) {
            final int number = index;
            if (!game.getPlayersAnswers().get(index).equals(question.getCorrectAnswer())) {
                if (game.getPlayers().get(index).getRectangle().getFill() != Color.RED) {
                    game.getPlayers().get(number).getRectangle().setFill(Color.RED);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }
            }

        }
        game.setNumberOfPlayers(game.getNumberOfPlayers() - game.getLosersNumber());
    }

    public boolean checkSkipBonus() {
        if (skipQuestion.isUsed()) {
            return false;
        }
        return true;
    }

    public boolean checkDeleteBonus() {
        if (deleteQuestions.isUsed()) return false;
        else return true;
    }


}



