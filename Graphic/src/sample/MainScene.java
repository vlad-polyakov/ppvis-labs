package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScene {
    public Table tableObj = new Table();
    public TableView<Info> table;
    public Controller controller;
    public GraphicPaint paint = new GraphicPaint();
    public ObservableList<Info> info;
    public TextField quantityField;
    public TextField testsField;
    public VBox createScene(){
        info = FXCollections.observableArrayList();
        VBox root = new VBox();
        Pane underCanvas = paint.createChart(root);
        HBox inputDataFields = new HBox(10);
        inputDataFields.setAlignment(Pos.CENTER);
        HBox tableAndGraphic = new HBox(60);
        inputDataFields.setPadding(new Insets(10,0,40,10));
        Label quantityInfo = new Label("Размер массива");
        quantityField = new TextField();
        Label testsInfo = new Label("Количество тестов");
        testsField = new TextField();
        table = tableObj.createTable();
        tableAndGraphic.getChildren().addAll(table,underCanvas);
        Button createBut = new Button("Построить");
        createBut.setOnAction(event ->{
            String quantVal = quantityField.getText();
            String testsVal = testsField.getText();
            if(!quantVal.matches("[-+]?\\d+")
                    ||quantVal.equals("")
                    ||!testsVal.matches("[-+]?\\d+")
                    ||testsVal.equals("")){
                showAlert();
                return;
            }
            int quantity = Integer.parseInt(quantityField.getText());
            int tests = Integer.parseInt(testsField.getText());
            if(quantity<2||tests<1){
                showAlert();
                return;
            }

            controller = new Controller(tableObj,paint,quantity,tests);
            controller.createGraphic();
                }
        );
        inputDataFields.getChildren().addAll(quantityInfo, quantityField,testsInfo,testsField,createBut);
        root.getChildren().addAll(inputDataFields, tableAndGraphic);
        return root;
    }
    private void showAlert() {
        Alert warn = new Alert(Alert.AlertType.INFORMATION);
        warn.setTitle("Warning!");
        warn.setContentText("Неверный формат данных");
        warn.showAndWait();
        return;
    }
}
