package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Students;

public class SearchAndDeleteModel {
    public boolean check(String checkingInfo1, String checkingInfo2){
        if("".equals(checkingInfo1)||"".equals(checkingInfo2)) {
            return false;
        }
        else return true;
    }
    public boolean checkSelection(boolean one, boolean two){
        if(one == false && two == false) return false;
        return true;
    }

    public ObservableList<Students> checkPattern(String textfield1, String textfield2, ObservableList<Students> list){
        ObservableList<Students> newTableList = FXCollections.observableArrayList();
        for(int row=0; row<list.size();row++) {
            Students student = list.get(row);
            if (
                    (student.getName().equals(textfield1) && student.getNumberOfGroup().equals(textfield2))
                    ||(student.getCourse().equals(textfield1) && student.getLangOfProgram().equals(textfield2))
                    ||(student.getCourse().equals(textfield1) && student.getTotalWorks().equals(textfield2))
            )
                newTableList.add(student);
        }
        return newTableList;
    }
    public ObservableList<Students> checkPattern(String textfield1, String textfield2, ObservableList<Students> list, boolean chooseCourse, boolean chooseGroup) {
        ObservableList<Students> newTableList = FXCollections.observableArrayList();
        for(int row=0;row<list.size();row++){
            Students student = list.get(row);
            if(chooseCourse == true){
                if((student.getCourse().equals(textfield1)&& student.getWorks().equals(textfield2)))
                    newTableList.add(student);
            }
            if(chooseGroup == true){
                if(student.getNumberOfGroup().equals(textfield1) && student.getWorks().equals(textfield2))
                    newTableList.add(student);
            }
        }
        return newTableList;
    }

}
