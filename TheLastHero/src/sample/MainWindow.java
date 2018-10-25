package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;


public class MainWindow {

    private Hero hero;
    private Question question;
    private BonusLevel bonusLevel;
    private DeleteQuestions deleteQuestions;
    private Button firstCardBut;
    private Button secondCardBut;
    private Button thirdCardBut;
    private Button fourthCardBut;
    private Label numberOfPlayersLabel;
    private Label numberOfRoundLabel;
    private Label balanceLabel;
    private Label roundBalanceLabel;
    private Label questionNumberLabel;
    private Controller controller;
    private VBox mainSceneBox;
    private RoundInfo round;
    private Stage stage;
    private Game game = new Game();

    MainWindow(Stage stage) {
        this.stage = stage;
    }

    public VBox createWindow() {
        round = new RoundInfo();
        hero = new Hero();
        question = new Question();
        controller = new Controller(hero, question, this, bonusLevel, round, game);
        game.setNumberOfPlayers(99);
        game.setQuestionNumber(1);
        round.setRound(1);
        round.setRoundCash(20000);
        mainSceneBox = new VBox(20);
        HBox firstLinePlayersBox = new HBox(5);
        firstLinePlayersBox.setAlignment(Pos.CENTER);
        HBox secondLinePlayersBox = new HBox(5);
        secondLinePlayersBox.setAlignment(Pos.CENTER);
        HBox labelBox = new HBox();
        VBox infoBox = new VBox(5);

        labelBox.setPadding(new Insets(0, 10, 0, 0));
        numberOfPlayersLabel = new Label("Players: " + game.getNumberOfPlayers());
        numberOfPlayersLabel.setFont(new Font("Cambria", 20));
        questionNumberLabel = new Label("Question: " + game.getQuestionNumber());
        questionNumberLabel.setFont(new Font("Cambria", 20));
        numberOfRoundLabel = new Label("Round " + round.getRound());
        numberOfRoundLabel.setFont(new Font("Cambria", 20));
        balanceLabel = new Label("Balance: " + hero.getHeroCash());
        balanceLabel.setFont(new Font("Cambria", 20));
        roundBalanceLabel = new Label("Round Cash: " + round.getRoundCash());
        roundBalanceLabel.setFont(new Font("Cambria", 20));
        infoBox.getChildren().addAll(numberOfPlayersLabel, numberOfRoundLabel, balanceLabel, roundBalanceLabel, questionNumberLabel);
        labelBox.setAlignment(Pos.CENTER_RIGHT);
        labelBox.getChildren().add(infoBox);
        Button but = new Button("Начать игру");
        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(150, 0, 0, 0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(but);
        but.setOnAction(event -> {
            controller.addQuestion();
        });
        Rectangle[] rectangle = new Rectangle[99];
        for (int enemyIndex = 0; enemyIndex < 99; enemyIndex++) {
            rectangle[enemyIndex] = new Rectangle();
            Players players = new Players();
            players.setRectangle(rectangle[enemyIndex]);
            rectangle[enemyIndex].setWidth(20);
            rectangle[enemyIndex].setHeight(40);
            rectangle[enemyIndex].setFill(Color.BLUE);
            if (enemyIndex < 50)
                firstLinePlayersBox.getChildren().add(rectangle[enemyIndex]);
            else secondLinePlayersBox.getChildren().add(rectangle[enemyIndex]);
            game.getPlayers().add(players);
        }
        mainSceneBox.getChildren().addAll(firstLinePlayersBox, secondLinePlayersBox, labelBox, buttonBox);
        return mainSceneBox;
    }

    public void updateLabel() {
        numberOfPlayersLabel.setText("Players: " + game.getNumberOfPlayers());
        numberOfRoundLabel.setText("Round " + round.getRound());
        balanceLabel.setText("Balance: " + hero.getHeroCash());
        roundBalanceLabel.setText("Round Cash " + round.getRoundCash());
        questionNumberLabel.setText("Question: " + game.getQuestionNumber());
    }

    public void clearSpace() {
        mainSceneBox.getChildren().remove(mainSceneBox.getChildren().size() - 1);
    }

    public void createQuestionBox(Question question) {
        ObservableList<String> answers = question.getAnswers();
        String questionStr = question.getQuestion();
        VBox questionBox = new VBox(5);
        questionBox.setPadding(new Insets(50, 0, 0, 0));
        questionBox.setAlignment(Pos.CENTER);
        Label questionLabel = new Label(questionStr);
        HBox ButtonBox12 = new HBox(5);
        ButtonBox12.setAlignment(Pos.CENTER);
        HBox ButtonBox34 = new HBox(5);
        ButtonBox34.setAlignment(Pos.CENTER);
        Button variant1But = new Button(answers.get(0));
        variant1But.setOnAction(event -> {
            round.setChosenVariant(variant1But.getText());
            controller.checkAnswer();
        });
        Button variant2But = new Button(answers.get(1));
        variant2But.setOnAction(event -> {
            round.setChosenVariant(variant2But.getText());
            controller.checkAnswer();
        });
        Button variant3But = new Button(answers.get(2));
        variant3But.setOnAction(event -> {
            round.setChosenVariant(variant3But.getText());
            controller.checkAnswer();
        });
        Button variant4But = new Button(answers.get(3));
        variant4But.setOnAction(event -> {
            round.setChosenVariant(variant4But.getText());
            controller.checkAnswer();
        });
        ButtonBox12.getChildren().addAll(variant1But, variant2But);
        ButtonBox34.getChildren().addAll(variant3But, variant4But);
        HBox hintBox = new HBox();
        Button skipBut = new Button("Skip Question");
        skipBut.setFont(new Font("Cambria", 17));
        skipBut.setOnAction(event -> {
            controller.turnOnSkip();
            hintBox.getChildren().remove(0);
        });
        hintBox.setAlignment(Pos.CENTER);
        Button deleteHalfBut = new Button("Delete two questions");
        deleteHalfBut.setFont(new Font("Cambria", 17));
        deleteHalfBut.setOnAction(event -> {
            for (int index = 0; index < 2; index++) {
                Random random = new Random();
                int randomIndex = random.nextInt(ButtonBox12.getChildren().size() + ButtonBox34.getChildren().size());

            }
        });
        hintBox.getChildren().addAll(skipBut, deleteHalfBut);
        if (!controller.checkSkipBonus()) {
            hintBox.getChildren().remove(hintBox);
        }
        if (!controller.checkDeleteBonus()) {
            hintBox.getChildren().remove(deleteHalfBut);
        }
        questionBox.getChildren().addAll(questionLabel, ButtonBox12, ButtonBox34, hintBox);
        mainSceneBox.getChildren().add(questionBox);
    }

    public void createMessageWin() {
        Alert warn = new Alert(Alert.AlertType.INFORMATION);
        warn.setTitle("Victory");
        warn.setContentText("You win!");
        warn.showAndWait();
    }

    public void createMessageLose() {
        Alert warn = new Alert(Alert.AlertType.INFORMATION);
        warn.setTitle("Don't worry");
        warn.setContentText("You lose!");
        warn.showAndWait();
    }

    public void createContinueBox() {
        clearSpace();
        VBox continueBox = new VBox(5);
        continueBox.setPadding(new Insets(100, 5, 5, 5));
        Label continueLabel = new Label("Choose what to do");
        HBox buttonsBox = new HBox(100);
        buttonsBox.setAlignment(Pos.CENTER);
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> controller.addQuestion());
        Button getCashButton = new Button("Get Cash");
        getCashButton.setOnAction(event -> System.exit(0));
        Button doubleCashButton = new Button("Double Cash");
        doubleCashButton.setOnAction(event -> createBonusScene());
        continueBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(continueButton, getCashButton, doubleCashButton);
        continueBox.getChildren().addAll(continueLabel, buttonsBox);
        mainSceneBox.getChildren().add(continueBox);
    }

    private void createBonusScene() {
        Stage bonusStage = new Stage();
        Random random = new Random();
        bonusLevel = new BonusLevel();
        VBox bonusStageBox = new VBox(5);
        bonusStageBox.setPadding(new Insets(40, 0, 0, 0));
        bonusStageBox.setAlignment(Pos.CENTER);
        bonusStage.setTitle("Bonus Level");
        bonusStage.setScene(new Scene(bonusStageBox, 500, 500));
        HBox buttonsAndLabelBox = new HBox(30);
        HBox buttonsBox = new HBox(5);
        int valueToCheck = random.nextInt(10) + 1;
        Label infoLabel = new Label(String.valueOf(valueToCheck));
        bonusLevel.setValueToCheck(valueToCheck);
        firstCardBut = new Button("Hello there");
        bonusLevel.getButtonMap().put(firstCardBut, random.nextInt(10) + 1);
        firstCardBut.setOnAction(event -> controller.playBonusGame(firstCardBut));
        secondCardBut = new Button("Hello there");
        secondCardBut.setOnAction(event -> controller.playBonusGame(secondCardBut));
        bonusLevel.getButtonMap().put(secondCardBut, random.nextInt(10) + 1);
        thirdCardBut = new Button("Hello there");
        thirdCardBut.setOnAction(event -> controller.playBonusGame(thirdCardBut));
        bonusLevel.getButtonMap().put(thirdCardBut, random.nextInt(10) + 1);
        fourthCardBut = new Button("Hello there");
        fourthCardBut.setOnAction(event -> controller.playBonusGame(fourthCardBut));
        Button returnBut = new Button("Return");
        returnBut.setOnAction(event -> bonusStage.close());
        bonusLevel.getButtonMap().put(fourthCardBut, random.nextInt(10) + 1);
        buttonsBox.getChildren().addAll(firstCardBut, secondCardBut, thirdCardBut, fourthCardBut);
        buttonsAndLabelBox.getChildren().addAll(infoLabel, buttonsBox, returnBut);
        bonusStageBox.getChildren().add(buttonsAndLabelBox);
        bonusStage.show();
    }

    public void writeInfoIntoButton(BonusLevel bonusLevel) {
        String button1Str = String.valueOf(bonusLevel.getButtonMap().get(firstCardBut));
        String button2Str = String.valueOf(bonusLevel.getButtonMap().get(secondCardBut));
        String button3Str = String.valueOf(bonusLevel.getButtonMap().get(thirdCardBut));
        String button4Str = String.valueOf(bonusLevel.getButtonMap().get(fourthCardBut));
        firstCardBut.setText(button1Str);
        secondCardBut.setText(button2Str);
        thirdCardBut.setText(button3Str);
        fourthCardBut.setText(button4Str);
    }

    public BonusLevel getBonusLevel() {
        return bonusLevel;
    }

    public void theEndWindow(int money) {
        Alert warn = new Alert(Alert.AlertType.INFORMATION);
        warn.setTitle("Yeah");
        warn.setContentText("The End! You have won " + String.valueOf(money));
        warn.showAndWait();
    }

}
