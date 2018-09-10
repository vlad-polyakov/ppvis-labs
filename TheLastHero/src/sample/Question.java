package sample;

import javafx.collections.ObservableList;

public class Question {
    private String question;
    private ObservableList<String> answers;
    private String correctAnswer;
    private int questionNumber;


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
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
