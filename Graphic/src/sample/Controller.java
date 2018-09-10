package sample;



public class Controller {
    private GraphicData graphicData;
    private MassivWork massiv;
    private GraphicPaint paint;
    private Thread thread;
    private Table table;
    private int quantity;
    private int test;
    private MainScene mainScene;

    public Controller(Table table, GraphicPaint paint, int quantity, int test){
        this.table = table;
        this.massiv = new MassivWork(this);
        this.graphicData = new GraphicData();
        this.paint = paint;
        this.mainScene = new MainScene();
        this.quantity = quantity;
        this.test = test;
    }
    Thread drawThread = new Thread(new Runnable() {
        public void run() {
            paint.setSize(quantity);
            if (thread != null) {
                massiv.shutdown();
                paint.clear();
                graphicData.removeAll();
                paint.updatePos();
            }
            table.clearTable();
            massiv.operate(quantity, test);
            thread = new Thread(massiv);
            thread.start();

    }
    });
        public void createGraphic (){
            drawThread.start();
            }

    public void setPoint(Info info){
        int index = graphicData.addPoint(info);
        update(index);
    }


    public void update(int index){
        Info point = graphicData.getGraphic().get(index);
        paint.setDataInGraphic(index,point);
        table.updateTable(point);
    }

}
