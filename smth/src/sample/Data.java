package sample;

public class Data {
    private String one;
    private String two;


    public Data(String one, String two) {
        this.one = one;
        this.two = two;
    }
    public Data() {
        this.one = "";
        this.two = "";
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public void removeOne(){
        this.one = "";
    }

    public void removeTwo(){

        this.two = "";
    }

    /*@Override
    public String toString() {
        return one+two;
    }*/
}

