package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class MainWindow {
    private Players players;
    private Hero hero;
    private Question question;
    private Label numberOfPlayersLabel;
    private Label numberOfRoundLabel;
    private Label balanceLabel;
    private Label roundBalanceLabel;
    private Label questionNumberLabel;
    private Controller controller;
    private VBox mainSceneBox;
    private Stage stage;
    MainWindow(Stage stage){
        this.stage = stage;
    }

    public VBox createWindow(){
        players = new Players();
        hero = new Hero();
        question = new Question();
        controller = new Controller(hero,players,question,this);
        players.setPlayersNumber(99);
        question.setQuestionNumber(1);
        hero.setRound(1);
        hero.setRoundCash(20000);
         mainSceneBox = new VBox(20);
        HBox firstLinePlayersBox = new HBox(5);
        firstLinePlayersBox.setAlignment(Pos.CENTER);
        HBox secondLinePlayersBox = new HBox(5);
        secondLinePlayersBox.setAlignment(Pos.CENTER);
        HBox labelBox = new HBox();
        VBox infoBox = new VBox(5);

        labelBox.setPadding(new Insets(0,10,0,0));
        numberOfPlayersLabel = new Label("Players: " + players.getPlayersNumber());
        numberOfPlayersLabel.setFont(new Font("Cambria",20));
        questionNumberLabel = new Label("Question: " + question.getQuestionNumber());
        questionNumberLabel.setFont(new Font("Cambria",20));
        numberOfRoundLabel = new Label("Round " + hero.getRound());
        numberOfRoundLabel.setFont(new Font("Cambria",20));
        balanceLabel = new Label("Balance: "+hero.getHeroCash());
        balanceLabel.setFont(new Font("Cambria",20));
        roundBalanceLabel = new Label("Round Cash: "+hero.getRoundCash());
        roundBalanceLabel.setFont(new Font("Cambria",20));
        infoBox.getChildren().addAll(numberOfPlayersLabel,numberOfRoundLabel,balanceLabel,roundBalanceLabel,questionNumberLabel);
        labelBox.setAlignment(Pos.CENTER_RIGHT);
        labelBox.getChildren().add(infoBox);
        Button but = new Button("Начать игру");
        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(150,0,0,0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(but);
        but.setOnAction(event -> {
            /*final Random random = new Random();
            players.getPlayers()[random.nextInt(99) + 1].setFill(Color.BLACK);
            players.setPlayersNumber(players.getPlayersNumber()-1);
            hero.setRound(hero.getRound()+1);
            hero.setRoundCash(hero.getRoundCash()+20000);
            updateLabel();*/
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            controller.addQuestion(file);
        });
        Rectangle[] rectangle = new Rectangle[99];
        for(int i=0;i<99;i++) {
            rectangle[i] = new Rectangle();
        rectangle[i].setWidth(20);
        rectangle[i].setHeight(40);
        rectangle[i].setFill(Color.BLUE);
        if(i<50)
        firstLinePlayersBox.getChildren().add(rectangle[i]);
        else secondLinePlayersBox.getChildren().add(rectangle[i]);
        }
       mainSceneBox.getChildren().addAll(firstLinePlayersBox,secondLinePlayersBox,labelBox,buttonBox);
        players.setPlayers(rectangle);
        return mainSceneBox;
    }
    public void updateLabel(){
        numberOfPlayersLabel.setText("Players: "+players.getPlayersNumber());
        numberOfRoundLabel.setText("Round "+hero.getRound());
        balanceLabel.setText("Balance: "+hero.getHeroCash());
        roundBalanceLabel.setText("Round Cash "+hero.getRoundCash());
        questionNumberLabel.setText("Question: "+question.getQuestionNumber());
    }

    public void clearSpace(){
        mainSceneBox.getChildren().remove(mainSceneBox.getChildren().size()-1);
    }

    public void createQuestionBox(String questionStr, ObservableList<String> answers){
        VBox questionBox = new VBox(5);
        questionBox.setPadding(new Insets(50,0,0,0));
        questionBox.setAlignment(Pos.CENTER);
        Label questionLabel = new Label(questionStr);
        HBox ButtonBox12 = new HBox(5);
        ButtonBox12.setAlignment(Pos.CENTER);
        HBox ButtonBox34= new HBox(5);
        ButtonBox34.setAlignment(Pos.CENTER);
        Button variant1But = new Button("1."+answers.get(0));
        Button variant2But = new Button("2."+answers.get(1));
        Button variant3But = new Button("3."+answers.get(2));
        Button variant4But = new Button("4."+answers.get(3));
        ButtonBox12.getChildren().addAll(variant1But,variant2But);
        ButtonBox34.getChildren().addAll(variant3But,variant4But);
        questionBox.getChildren().addAll(questionLabel,ButtonBox12,ButtonBox34);
        mainSceneBox.getChildren().add(questionBox);
    }
}
