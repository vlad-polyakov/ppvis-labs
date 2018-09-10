package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table {
    private TableView<Info> table;
    private ObservableList<Info> list = FXCollections.observableArrayList();
    public TableView<Info> createTable() {
        table = new TableView<>();
        table.setMinWidth(400);
        TableColumn<Info, String> quantityCol = new TableColumn<>("Количество элементов");
        TableColumn<Info, String> timeCol = new TableColumn<>("Время");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.getColumns().addAll(quantityCol,timeCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return table;
    }
    public void updateTable(Info info) {
        list.add(info);
        table.setItems(list);
    }
    public void clearTable(){
        list.clear();
        table.setItems(list);
    }
}
