package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public MainWindow mainWindow;
    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow = new MainWindow(primaryStage);
        VBox root = mainWindow.createWindow();
        primaryStage.setTitle("The Last Hero");
        primaryStage.setScene(new Scene(root, 1350, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
