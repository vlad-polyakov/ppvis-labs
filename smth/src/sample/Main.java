package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.control.cell.*;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.Callback;

public class Main extends Application {
    private TableView table = new TableView<Data>();
    private TableView tableNew = new TableView();
    public int quantity =0;
    public ObservableList<ObservableList> inside = FXCollections.observableArrayList();
    public String inputed = "here";
    private final ObservableList<Data> data =
            FXCollections.observableArrayList(new Data("lol","two"));
    private int columns = 0;
    private int rows = 0;
    public List<TableColumn> tableColumns = new ArrayList<TableColumn>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox space = new HBox(); 
        Scene scene = new Scene(space,1100,175);

        space.setSpacing(10);
        space.setPadding(new Insets(0,10,10,10));

        primaryStage.setTitle("LAB 1");
        primaryStage.setScene(scene);

        VBox root1 = new VBox();//1 группа
        root1.setSpacing(10);
        root1.setPadding(new Insets(15,20,10,10));

        TextField fillText1 = new TextField();
        fillText1.setMaxWidth(120);
        root1.getChildren().add(fillText1);

        ObservableList<String> combo = FXCollections.observableArrayList();
        final ComboBox<String> list = new ComboBox<>(combo);

        list.setPrefWidth(120);
        root1.getChildren().add(list);

        Button addToList = new Button();


        addToList.setText("Click me");
        addToList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = fillText1.getText();
                if (list.getItems().contains(text)) {
                    Alert warning = new Alert(Alert.AlertType.INFORMATION);
                    warning.setTitle("Warning!");
                    warning.setContentText("This word is using.");
                    warning.showAndWait();
                    return;
                }
                list.getItems().add(text);
                fillText1.clear();
            }
    });

        HBox additionalRoot1 = new HBox();
        additionalRoot1.getChildren().add(addToList);
        root1.getChildren().add(additionalRoot1);
        space.getChildren().add(root1);



        VBox root2 = new VBox();
        root2.setSpacing(10);
        root2.setPadding(new Insets(15,20,10,10));

        TextField fillText2 = new TextField();
        fillText2.setMaxWidth(120);
        root2.getChildren().add(fillText2);

        Button TextFromField = new Button();
        TextFromField.setText("Edit next Button");

        Button ChangeNames = new Button();
        ChangeNames.setText("There is some text");

        TextFromField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String smth = fillText2.getText();
                ChangeNames.setText(smth);
            }
        });

        ChangeNames.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name1 = TextFromField.getText();
                String name2 = ChangeNames.getText();
                TextFromField.setText(name2);
                ChangeNames.setText(name1);
            }
        });
        root2.getChildren().add(TextFromField);
        root2.getChildren().add(ChangeNames);
        space.getChildren().add(root2);

        VBox root3 = new VBox();
        root3.setSpacing(10);
        root3.setPadding(new Insets(15,20,10,10));

        TextField fillText3 = new TextField();
        fillText3.setMaxWidth(120);
        root3.getChildren().add(fillText3);

        HBox plus = new HBox();
        HBox minus = new HBox();
        minus.setSpacing(10);
        plus.setSpacing(10);
        RadioButton chosen1 = new RadioButton("1");
        RadioButton chosen2 = new RadioButton("2");
        RadioButton chosen3 = new RadioButton("3");
        ToggleGroup group = new ToggleGroup();
        chosen1.setToggleGroup(group);
        chosen2.setToggleGroup(group);
        chosen3.setToggleGroup(group);
        plus.getChildren().add(chosen1);
        plus.getChildren().add(chosen2);
        root3.getChildren().add(plus);
        minus.getChildren().add(chosen3);
        Button markRButton = new Button("Simple Button");
        minus.getChildren().add(markRButton);
        root3.getChildren().add(minus);
        markRButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String smtext = fillText3.getText();
                boolean help = false;

                if(chosen1.getText().equals(smtext)){
                    chosen1.setSelected(true);
                    help = true;
                }
                if(chosen2.getText().equals(smtext)){
                    chosen2.setSelected(true);
                    help = true; }
                if(chosen3.getText().equals(smtext)){
                    chosen3.setSelected(true);
                   help = true; }
                if (!help) {
                    Alert warn = new Alert(Alert.AlertType.INFORMATION);
                    warn.setTitle("Warning!");
                    warn.setContentText("There is no such word.");
                    warn.showAndWait();
                }
            }
        });
        space.getChildren().add(root3);

        VBox root4 = new VBox();
        root4.setSpacing(10);
        root4.setPadding(new Insets(15,20,10,10));
        TextField fillText4 = new TextField();
        fillText4.setMaxWidth(120);
        root4.getChildren().add(fillText4);
        HBox boxes = new HBox();
        boxes.setSpacing(10);
        CheckBox box1 = new CheckBox("1");
        CheckBox box2 = new CheckBox("2");
        boxes.getChildren().add(box1);
        boxes.getChildren().add(box2);
        root4.getChildren().add(boxes);
        CheckBox box3 = new CheckBox("3");
        root4.getChildren().add(box3);
        HBox group4 = new HBox();
        Button markCheckbox = new Button("Point");
        group4.getChildren().add(markCheckbox);
        markCheckbox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String filled = fillText4.getText();
                boolean help = false;

                if(box1.getText().equals(filled)){

                    box1.fire();
                    help = true;
                }
                if(box2.getText().equals(filled)){
                    box2.fire();
                    help = true; }
                if(box3.getText().equals(filled)){
                    box3.fire();
                    help = true; }
                if (!help) {
                    Alert warn = new Alert(Alert.AlertType.INFORMATION);
                    warn.setTitle("Warning!");
                    warn.setContentText("There is no such word.");
                    warn.showAndWait();
                }
            }
        });
        Button clean4 = new Button();
        clean4.setText("Clear");
        clean4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fillText4.clear();
            }
        });
        group4.getChildren().add(clean4);
        root4.getChildren().add(group4);
        space.getChildren().add(root4);
        VBox root5 = new VBox();
        root5.setSpacing(10);
        root5.setPadding(new Insets(15,20,10,10));
        TextField textfield = new TextField();
        textfield.setMaxWidth(120);
        root5.getChildren().add(textfield);
        HBox buttons = new HBox();
        Button but1 = new Button("in 1");
        Button but2 = new Button("in 2");
        Button but3 = new Button("repeat 1");
        buttons.getChildren().addAll(but1,but2);
        root5.getChildren().addAll(buttons, but3);
        space.getChildren().add(root5);

        TableView<Data> table = new TableView<Data>();
        table.setMaxWidth(200);
        TableColumn<Data, String> FirstCol = new TableColumn<Data, String>("One");

        TableColumn<Data, String> SecondCol = new TableColumn<Data, String>("Two");
        FirstCol.setMinWidth(100);
        SecondCol.setMinWidth(100);
        FirstCol.setCellValueFactory(new PropertyValueFactory<Data,String>("one"));
        SecondCol.setCellValueFactory(new PropertyValueFactory<Data,String>("two"));
        table.setItems(data);
        table.getColumns().addAll(FirstCol,SecondCol);
        space.getChildren().add(table);
        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textfield.getText();
                if (text.equals("")) {
                    Alert warn = new Alert(Alert.AlertType.INFORMATION);
                    warn.setTitle("Warning!");
                    warn.setContentText("There is no word.");
                    warn.showAndWait();
                    return;
                }

                Data example = new Data();

                example.setOne(text);
                table.getItems().add(example);
                textfield.clear();
            }
        });

        but2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Data example =table.getItems().get(row);
                String s = example.getOne();
                if(s.equals("")) return;
                example.removeOne();
                example.setTwo(s);
                table.getItems().set(row, example);
            }
        });

        but3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Data example = table.getItems().get(row);
                String s = example.getTwo();
                if(s.equals("")) return;
                example.removeTwo();
                example.setOne(s);
                table.getItems().set(row, example);
            }
        });
        primaryStage.show();


        Stage dopTask = new Stage();
        VBox layout = new VBox();
        Scene taskScene = new Scene(layout,500,300);
        dopTask.setScene(taskScene);
        tableNew.setMaxWidth(400);
        tableNew.setMaxHeight(200);
        tableNew.setEditable(true);
        TableColumn temp = new TableColumn();
        layout.getChildren().add(tableNew);
        TextField inputRows = new TextField("Rows");
        inputRows.setMaxWidth(100);
        TextField inputColumns = new TextField("Columns");
        inputColumns.setMaxWidth(100);
        Button start = new Button("Start");
        MyThread myThread = new MyThread();
        start.setOnAction(event -> myThread.start() );
        Button setSize = new Button("Set size");
        Button stop = new Button("stop");
        tableNew.setEditable(true);
        setSize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String width = inputColumns.getText();
                String height = inputRows.getText();
                rows = Integer.parseInt(height);
                columns = Integer.parseInt(width);

                for (int i = 0; i < columns; i++) {
                    final int finalIdx = i;
                    TableColumn column = new TableColumn(
                            String.valueOf(i + 1)
                    );
                    tableColumns.add(column);
                    column.setMinWidth(100);

                    column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(finalIdx).toString());
                        }
                    });
                    column.setCellFactory(TextFieldTableCell.<String> forTableColumn());
                    tableNew.getColumns().add(column);
                }
                for (int i = 0; i < rows; i++) {
                    ObservableList<String> away = FXCollections.observableArrayList();
                    for(int j=0; j<columns; j++){
                        away.add(String.valueOf(i));
                    }
                    inside.add(away);
                }
                tableNew.setItems(inside);
            }
        });
        /*TableColumn<Task,String>[] Col = new TableColumn[Integer.parseInt(inputColumns.getText())];

        for(int i=0; i<Integer.parseInt(inputColumns.getText());i++) {

      }*/
        /*for (int i = 0; i < columns; i++) {
            final int I = i;
            tableColumns.get(i).setOnEditCommit((CellEditEvent<ObservableList,String> event) -> {
                TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                ObservableList<String> lists = inside.get(row);
                lists.set(I, event.getNewValue());
            });
        }*/
        stop.setOnAction(event -> myThread.interrupt());
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,20,10,10));
        hbox.getChildren().addAll(start, inputRows, inputColumns,setSize,stop);
        layout.getChildren().add(hbox);
        dopTask.show();

    }
    public class MyThread extends Thread {
        public  String returnText="";
        public void run() {
            try {
                quantity = 1;
                for (int colInd = 0; colInd < columns; colInd++) {
                    for (int rowInd = 0; rowInd < rows - quantity; rowInd++) {
                        String teext = (String) tableColumns.get(colInd).getCellObservableValue(rowInd).getValue();
                        ObservableList<String> list = inside.get(rowInd);
                        list.set(colInd, teext + "," + inputed);
                        boolean checkRows = rowInd!=0;
                        if(checkRows){
                            ObservableList<String> returned = inside.get(rowInd-1);
                            returned.set(colInd, returnText);
                            inside.set(rowInd-1,returned);
                        }
                        Thread.sleep(1000);
                        inside.set(rowInd,list);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                tableNew.setItems(inside);
                            }
                        });
                        Thread.sleep(1000);
                        returnText = teext;
                    }
                    int dop = 0;
                    if (colInd < columns - 1) {
                        int prevCellNum = rows - quantity - 1;
                        for (int rowDopIndex = prevCellNum; rowDopIndex < rows; rowDopIndex++) {
                            dop = rowDopIndex;
                            String teext = (String) tableColumns.get(colInd + 1).getCellObservableValue(rowDopIndex).getValue();
                            if(isInterrupted()) return;
                            ObservableList<String> strings = inside.get(rowDopIndex);
                            boolean b = rowDopIndex == prevCellNum;
                            if(b){
                                strings.set(colInd, teext);
                            }
                            boolean bull = rowDopIndex>prevCellNum;
                            if(bull){
                                ObservableList<String> returned = inside.get(rowDopIndex-1);
                                returned.set(colInd+1, returnText);
                                inside.set(rowDopIndex-1,returned);
                            }
                            strings.set(colInd + 1, teext + "," + inputed);
                            Thread.sleep(1000);
                            inside.set(rowDopIndex,strings);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    tableNew.setItems(inside);
                                }
                            });
                            Thread.sleep(1000);
                            returnText = teext;
                        }
                    }
                    Thread.sleep(1000);
                    int lastIndex = rows - 1;
                    boolean finalCheck = dop==lastIndex;
                    if(finalCheck){
                        ObservableList<String> returned = inside.get(dop);
                        returned.set(colInd+1, returnText);
                        inside.set(dop,returned);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                tableNew.setItems(inside);
                            }
                        });
                    }
                        if (quantity == lastIndex) {

                            colInd++;
                            quantity = 1;
                        } else{ quantity++; }
                }
            }
            catch(InterruptedException e){}
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

