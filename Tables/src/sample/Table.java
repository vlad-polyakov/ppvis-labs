package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Students;

public class Table {
    private Label pageNumLabel;
    private Label currPageNumLabel;
    private Label sizeLabel;
    private TableView<Students> table = new TableView<>();
    private int pageIndex;
    private int pageNum;
    private int fromIndex;
    private int toIndex;
    private int rowsPerPage;

    public void createTable(TableView table) {
        TableColumn<Students, String> fioCOl = new TableColumn<>("Name");
        fioCOl.setMinWidth(200);
        TableColumn<Students, String> groupCol = new TableColumn<>("Group");
        TableColumn<Students, String> courseCol = new TableColumn<>("Course");
        TableColumn<Students, String> worksCol = new TableColumn<>("Works");
        TableColumn<Students, String> totalWorksCol = new TableColumn<>("All works");
        TableColumn<Students, String> langOfProgramCol = new TableColumn<>("Language");
        fioCOl.setCellValueFactory(new PropertyValueFactory<>("fio"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("numberOfGroup"));
        worksCol.setCellValueFactory(new PropertyValueFactory<>("works"));
        totalWorksCol.setCellValueFactory(new PropertyValueFactory<>("totalWorks"));
        langOfProgramCol.setCellValueFactory(new PropertyValueFactory<>("langOfProgram"));
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
        table.getColumns().addAll(fioCOl, courseCol, groupCol, worksCol, totalWorksCol, langOfProgramCol);
    }

    public VBox create(ObservableList<Students> list) {
        VBox overallBox = new VBox(20);
        overallBox.setAlignment(Pos.CENTER);

        pageIndex = 0;
        rowsPerPage = 10;
        fromIndex = 0;
        toIndex = Math.min(fromIndex + rowsPerPage, list.size());

        TableColumn<Students, String> fioCOl = new TableColumn<>("Name");
        fioCOl.setMinWidth(200);
        TableColumn<Students, String> groupCol = new TableColumn<>("Group");
        TableColumn<Students, String> courseCol = new TableColumn<>("Course");
        TableColumn<Students, String> worksCol = new TableColumn<>("Works");
        TableColumn<Students, String> totalWorksCol = new TableColumn<>("All works");
        TableColumn<Students, String> langOfProgramCol = new TableColumn<>("Language");

        if ((list.size() > (toIndex - fromIndex)) & ((toIndex - fromIndex) != -1)) {
            table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
        } else {
            table.setItems(list);
        }
        fioCOl.setCellValueFactory(new PropertyValueFactory<>("fio"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("numberOfGroup"));
        worksCol.setCellValueFactory(new PropertyValueFactory<>("works"));
        totalWorksCol.setCellValueFactory(new PropertyValueFactory<>("totalWorks"));
        langOfProgramCol.setCellValueFactory(new PropertyValueFactory<>("langOfProgram"));
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
        table.getColumns().addAll(fioCOl, courseCol, groupCol, worksCol, totalWorksCol, langOfProgramCol);

        Button prev = new Button("Prev");
        Button next = new Button("Next");
        Button first = new Button("At First");
        Button last = new Button("At Last");
        Button setPage = new Button("Go");
        HBox firstPages = new HBox(10);
        firstPages.getChildren().addAll(first,prev);
        HBox lastPages = new HBox(10);
        lastPages.getChildren().addAll(next,last);


        TextField pageField = new TextField();
        pageField.setMaxWidth(60);

        HBox setPageBox = new HBox(10);
        setPageBox.setAlignment(Pos.CENTER);
        setPageBox.getChildren().addAll(pageField, setPage);
        HBox pageCtrlBox = new HBox(150);
        pageCtrlBox.setPadding(new Insets(50,0,0,0));
        pageCtrlBox.getChildren().addAll(firstPages,setPageBox,lastPages);
        pageCtrlBox.setAlignment(Pos.CENTER);

        HBox labelsBox = new HBox(10);

        Label sizeLabelInfo = new Label("Quantity of rows");
        Label pageNumInfoLabel = new Label("Pages:");
        sizeLabel = new Label("");
        labelsBox.getChildren().addAll(sizeLabelInfo,sizeLabel);
        labelsBox.setAlignment(Pos.CENTER);
        pageNumLabel = new Label("");
        HBox pageNumInfo = new HBox(5);
        pageNumInfo.getChildren().addAll(pageNumInfoLabel, pageNumLabel);
        pageNumInfo.setAlignment(Pos.CENTER);

        Label currPageNumInfoLabel = new Label("Current page");
        currPageNumLabel = new Label("");
        HBox currPageNumInfo = new HBox(5);
        currPageNumInfo.getChildren().addAll(currPageNumInfoLabel, currPageNumLabel);
        currPageNumInfo.setAlignment(Pos.CENTER);

        Button setRowsPerPageButton = new Button("Input number of strings");
        TextField rowsNumPerPage = new TextField();
        HBox rowPerPageSetBox = new HBox(5);
        rowPerPageSetBox.getChildren().addAll(setRowsPerPageButton, rowsNumPerPage);
        rowPerPageSetBox.setAlignment(Pos.CENTER);

        overallBox.getChildren().addAll( pageCtrlBox,table, pageNumInfo, currPageNumInfo,labelsBox, rowPerPageSetBox);


        updatePageNum(list);
        updatePageIndex(0);
        updateData(list);

        prev.setOnAction(event -> {
            if (fromIndex - rowsPerPage >= 0) {
                fromIndex -= rowsPerPage;
                toIndex = Math.min(fromIndex + rowsPerPage, list.size());
                table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
                updatePageIndex(fromIndex);
            }
        });

        next.setOnAction(event -> {
            if (fromIndex + rowsPerPage < list.size()) {
                fromIndex += rowsPerPage;
                toIndex = Math.min(fromIndex + rowsPerPage, list.size());
                table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
                updatePageIndex(fromIndex);
            }
        });

        first.setOnAction(event -> {
            fromIndex = 0;
            toIndex = Math.min(fromIndex + rowsPerPage, list.size());
            table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
            updatePageIndex(fromIndex);
        });

        last.setOnAction(event -> {
            int tmp = list.size() / rowsPerPage;
            if (list.size() % rowsPerPage == 0) {
                fromIndex = (tmp - 1) * rowsPerPage;
            } else {
                fromIndex = tmp * rowsPerPage;
            }
            toIndex = Math.min(fromIndex + rowsPerPage, list.size());
            table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
            updatePageIndex(fromIndex);
        });

        setPage.setOnAction(event -> {
            int tmp = Integer.parseInt(pageField.getText()) - 1;
            if (pageNum > tmp) {
                fromIndex = tmp * rowsPerPage;
                toIndex = Math.min(fromIndex + rowsPerPage, list.size());
                table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
                updatePageIndex(fromIndex);
            }
        });

        setRowsPerPageButton.setOnAction(event -> {
            rowsPerPage = Integer.parseInt(rowsNumPerPage.getText());
            updateTable(list);
            updatePageIndex(fromIndex);
            updatePageNum(list);
        });

        return overallBox;
    }

    public void updatePageNum(ObservableList<Students> list) {
        if (list.size() % rowsPerPage != 0) {
            pageNum = list.size() / rowsPerPage + 1;
        } else {
            pageNum = (list.size() / rowsPerPage);
        }
        pageNumLabel.setText(String.valueOf(pageNum));
    }

    public void updateTable(ObservableList<Students> list) {
        fromIndex = 0;
        toIndex = Math.min(fromIndex + rowsPerPage, list.size());
        table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
    }
    public void updateData(ObservableList<Students> list){
        sizeLabel.setText(String.valueOf(list.size()));
    }
    public void updateAll(ObservableList<Students> list) {
        updatePageNum(list);
        updateTable(list);
        updateData(list);
    }

    void toFirstPage(ObservableList<Students> list) {
        fromIndex = 0;
        toIndex = Math.min(fromIndex + rowsPerPage, list.size());
        table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
        updatePageIndex(fromIndex);
    }

    private void updatePageIndex(int fromIndex) {
        pageIndex = fromIndex / rowsPerPage;
        currPageNumLabel.setText(String.valueOf(pageIndex + 1));
    }
}