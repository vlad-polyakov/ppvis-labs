package sample;

import javafx.collections.ObservableList;

public class Question {
    private String question;
    private ObservableList<String> answers;
    private String correctAnswer;


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public ObservableList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ObservableList<String> answers) {
        this.answers = answers;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
