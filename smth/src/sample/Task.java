package sample;
import java.util.List;
import java.util.ArrayList;

public class Task {
    /*private  List<String> one = new ArrayList<>();

    public Task(String text) {
        for(int i=0; i<10;i++) {
            one.add(text);
        }
    }

    public String getOne(int number) {
        return one.get(number);
    }

    public void setOne(String one, int number) {
        this.one.set(number,one);
    }
*/
    //public void removeOne(int number){
     //   one[number] = "";
   // }
    private  String one;

    public Task() {
       this.one = "";
    }
    public Task(String one) {
        this.one = one;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one=one;
    }

}
