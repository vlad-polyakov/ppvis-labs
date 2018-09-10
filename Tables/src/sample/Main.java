package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.File;

public class Main extends Application {
    ObservableList<Students> dataa = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller(dataa);
        Table tableview = new Table();
        VBox general = new VBox();
        HBox buttons = new HBox();
        Button open = new Button("Open");
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(15,20,10,10));
        Button add = new Button("Add");
        Button delete = new Button("Delete");
        Button save = new Button("Save");
        Button search = new Button("Search");
        Button exit = new Button("Exit");
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(open,add,search,delete,save,exit);
        TableView table = new TableView();
        new Table().createTable(table);
        VBox tableww = tableview.create(dataa);
        add.setOnAction(event -> new AddInfo().add(dataa,tableview));
        open.setOnAction(event ->{
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            controller.open(file, dataa);
            tableview.updateAll(dataa);
        });
        save.setOnAction(event ->{
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(primaryStage);
            controller.save(file,dataa);
        });
        search.setOnAction(event -> new SearchInfo().run(dataa));
        delete.setOnAction(event -> new DeleteInfo().run(dataa, tableview));
        exit.setOnAction(event -> controller.exit());
        general.getChildren().addAll(buttons,tableww);
        primaryStage.setTitle("Students");
        primaryStage.setScene(new Scene(general, 700, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
