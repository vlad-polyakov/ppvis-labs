package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddInfo {
    public void add(ObservableList<Students> students, Table table){
        Stage adding = new Stage();
        VBox stageForAdding = new VBox();
        stageForAdding.setPadding(new Insets(10,20,20,20));
        HBox groupFIO = new HBox();
        Label name = new Label("Enter name and surname:");
        TextField inputName = new TextField();
        TextField inputSurname = new TextField();
        groupFIO.getChildren().addAll(name,inputName,inputSurname);
        groupFIO.setSpacing(30);
        groupFIO.setPadding(new Insets(10,20,20,20));
        HBox groupCourse = new HBox();
        groupCourse.setSpacing(30);
        groupCourse.setPadding(new Insets(10,20,20,20));
        Label courseText = new Label("Enter number of course:");
        TextField inputCourse = new TextField();
        groupCourse.getChildren().addAll(courseText,inputCourse);
        HBox groupGroup = new HBox();
        groupGroup.setSpacing(30);
        groupGroup.setPadding(new Insets(10,20,20,20));
        Label textGroup = new Label("Enter number of group:");
        TextField inputNumberOfGroup = new TextField();
        groupGroup.getChildren().addAll(textGroup,inputNumberOfGroup);
        HBox groupWorks = new HBox();
        groupWorks.setSpacing(30);
        groupWorks.setPadding(new Insets(10,20,20,20));
        Label worksText = new Label("Enter number of works:");
        TextField inputWorks = new TextField();
        groupWorks.getChildren().addAll(worksText,inputWorks);
        HBox groupAllWorks = new HBox();
        groupAllWorks.setSpacing(30);
        groupAllWorks.setPadding(new Insets(10,20,20,20));
        Label allWorksText = new Label("Enter number of all works:");
        TextField inputAllWorks = new TextField();
        groupAllWorks.getChildren().addAll(allWorksText,inputAllWorks);
        HBox groupLang = new HBox();
        groupLang.setSpacing(30);
        groupLang.setPadding(new Insets(10,20,20,20));
        Label langText = new Label("Enter programming language:");
        TextField inputLang = new TextField();
        groupLang.getChildren().addAll(langText,inputLang);
        Button addInfo = new Button("Ready");
        stageForAdding.getChildren().addAll(groupFIO,groupGroup,groupCourse,groupWorks,groupAllWorks,groupLang,addInfo);

        addInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    if (inputName.getText().equals("")||inputNumberOfGroup.getText().equals("")||inputCourse.getText().equals("")||inputLang.getText().equals("")||inputAllWorks.getText().equals("")) {
                        Alert warn = new Alert(Alert.AlertType.INFORMATION);
                        warn.setTitle("Warning!");
                        warn.setContentText("There is no word.");
                        warn.showAndWait();
                        return;
                    }

                String worksValue = inputWorks.getText();
                if(worksValue.equals("")){
                    worksValue = "0";
                }
                //Students example = new Students();
                Students example = new Students(inputName.getText(),inputSurname.getText(),inputNumberOfGroup.getText(),inputCourse.getText(),worksValue,inputAllWorks.getText(),inputLang.getText());
               /*example.setSurname(inputName.getText().substring(0,inputName.getText().indexOf(" ")));
                example.setName(inputName.getText().substring(inputName.getText().indexOf(" ")+1));
                example.setFio(inputName.getText());
                example.setNumberOfGroup(inputNumberOfGroup.getText());
                example.setCourse(inputCourse.getText());
                example.setWorks(worksValue);
                example.setTotalWorks(inputAllWorks.getText());
                example.setLangOfProgram(inputLang.getText());*/
                Controller controller=new Controller(students);
                controller.add(example);
                table.updateAll(students);
                adding.close();
            }
        });
        //totals = Integer.parseInt(inputWorks.getText()) + Integer.parseInt(inputSimplePass.getText());
        adding.setTitle("Enter information");
        adding.setScene(new Scene(stageForAdding, 600, 450));
        adding.show();
    }
}
