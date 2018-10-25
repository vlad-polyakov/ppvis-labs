package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.Random;

public class Game {
    private int numberOfRounds = 4;
    private ObservableList<Players> players;
    private ObservableList<String> playersAnswers;
    private int numberOfPlayers;
    private int losersNumber;
    private ObservableList<Question> questions;
    private int questionNumber;

    public Game() {
        players = FXCollections.observableArrayList();
        playersAnswers = FXCollections.observableArrayList();
        questions = FXCollections.observableArrayList();
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }


    public ObservableList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ObservableList<Question> questions) {
        this.questions = questions;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setLosersNumber(int losersNumber) {
        this.losersNumber = losersNumber;
    }

    public int getLosersNumber() {
        return losersNumber;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }


    public ObservableList<Players> getPlayers() {
        return players;
    }

    public ObservableList<String> getPlayersAnswers() {
        return playersAnswers;
    }

    public void findLosers(Question question) {
        losersNumber = 0;
        for (int index = 0; index < numberOfPlayers; index++) {
            final Random random = new Random();
            int randomIndex = random.nextInt(4);
            if (playersAnswers.size() == 99) {
                playersAnswers.set(index, question.getAnswers().get(randomIndex));
            } else playersAnswers.add(question.getAnswers().get(randomIndex));
        }
        for (int index = 0; index < numberOfPlayers; index++) {
            final int number = index;
            if (!playersAnswers.get(index).equals(question.getCorrectAnswer())) {
                if (players.get(index).getRectangle().getFill() != Color.RED) {
                    losersNumber++;
                }
            }
        }
    }

    public Question chooseQuestion() {
        final Random random = new Random();
        int numberOfQuestions = questions.size();
        int randomIndex = random.nextInt(numberOfQuestions);
        Question question = questions.get(randomIndex);
        return question;
    }
}
