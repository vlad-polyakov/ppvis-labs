package sample;


public class Info {
    private long time;
    private int quantity;
    public Info(){
        this.time = 0;
        this.quantity = 0;
    }

    public Info(long time, int quantity){
        this.time = time;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public long getTime() {
        return time;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
