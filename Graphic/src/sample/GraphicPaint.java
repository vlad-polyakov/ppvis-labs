package sample;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class GraphicPaint {
    private long endX = 600;
    private long beginY = 550;
    private long beginX = 40;
    private long endY = 100;
    private long posX;
    private long posY;
    private long newPosY;
    private long newPosX;
    private long lastValue = 1;
    private Double k = 1.1;
    private int shift = 20;
    private int size = 0;
    private long posAxis;
    private Canvas canvas;
    private ScrollPane scrollPane;

    public void setSize(int size) {
        this.size = size;
    }


    public void paintXY(){
        clear();
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setStroke(Color.BLACK);
        context.setFill(Color.BLACK);
        canvas.setWidth(size*shift+3*shift);
        canvas.setHeight(1000);
        paintLine(beginX, endY,beginX, beginY,context);
        paintLine(beginX, endY, shift*size+shift*2, endY,context);
        context.setFill(Color.INDIGO);
        context.setFont(Font.font("Consolas", FontWeight.BOLD, 18));
        context.fillText("t",10,endY);
        context.fillText("N",50,endY-20);
        posAxis = beginY;

    }
    public Pane createChart(Pane father){
        VBox overallBox = new VBox(5);
        this.canvas = new Canvas();
        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefSize(700, 700);
       Group group = new Group();
       group.getChildren().add(canvas);

        scrollPane.setContent(group);

        father.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.CONTROL) {
                overallBox.setOnKeyReleased(event1 -> {
                    if(event1.getCode() == KeyCode.CONTROL) {
                        canvas.setOnScroll(event2 -> {
                            event2.consume();
                        });
                    }
                });
                canvas.setOnScroll(event2 -> {
                    double scaleFactor;
                    if (event2.getDeltaY() > 0) {
                        scaleFactor = k;
                    } else if(canvas.getScaleX() > 1.1){
                        scaleFactor = 1 / k;
                    } else {
                        scaleFactor = 1;
                    }

                    canvas.setScaleX(canvas.getScaleX() * scaleFactor);
                    canvas.setScaleY(canvas.getScaleY() * scaleFactor);
                });
            }
        });

        canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                canvas.setOnMouseDragged(event2 -> {
                    scrollPane.setHvalue(event2.getX() / 1000);
                    scrollPane.setVvalue(event2.getY() / 1000);
                });
            }
        });

        overallBox.getChildren().addAll(scrollPane);

        return overallBox;
    }
    public void clear(){
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.WHITE);
        context.setStroke(Color.WHITE);
        context.clearRect(0,0,context.getCanvas().getWidth(),context.getCanvas().getHeight());
        context.setLineWidth(1);
        context.strokeRect(0,0,context.getCanvas().getWidth(),context.getCanvas().getHeight());
        lastValue  =1;
    }

    public void setDataInGraphic(int index, Info point){
        if(index==0) {
            posX = beginX;
            posY = endY;
            newPosX = posX + 2*shift;
            paintXY();
            markingY(20);
            markingX(size);
        }
        else {
            newPosX = posX + shift;
        }

        newPosY = endY + shift * point.getTime();
        if(newPosY>posAxis){
            canvas.setHeight(point.getTime()*shift+canvas.getHeight()/2);
            paintLine(beginX, posAxis,beginX, newPosY,canvas.getGraphicsContext2D());
            markingY(point.getTime());
            posAxis = newPosY;
            ;
        }
        paintLine(posX,posY,newPosX,newPosY,canvas.getGraphicsContext2D());
        posX = newPosX;
        posY = newPosY;
    }
    public void updatePos(){
        posY = beginY;
        posX = beginX;
    }

    public void markingX(int size){
        long positionX = beginX+shift;
        canvas.getGraphicsContext2D().setFont(Font.font("Consolas", FontWeight.BLACK, 13));
        for (int index = 1; index <=size; index++){
            paintLine(positionX,endY-5,positionX,endY+5,canvas.getGraphicsContext2D());
            canvas.getGraphicsContext2D().fillText(String.valueOf(index), positionX, endY+20);
            positionX+=shift;
        }
    }

    public void markingY(long size){
        long positionY;
        if(lastValue==1) {
            positionY = endY+shift;
            lastValue--;
        }
        else positionY = endY+shift*lastValue+shift;
        canvas.getGraphicsContext2D().setFont(Font.font("Consolas", FontWeight.BLACK, 13));
        for (int index = 1; index <=size-lastValue; index++){
            paintLine(beginX-5,positionY,beginX+5,positionY,canvas.getGraphicsContext2D());
            canvas.getGraphicsContext2D().fillText(String.valueOf(index+lastValue), beginX+20, positionY);
            positionY+=shift;
        }
        lastValue = size;
    }

    public void paintLine(long fromX, long fromY, long toX, long toY,GraphicsContext context){
        context.beginPath();
        context.setLineWidth(3);
        context.moveTo(fromX,fromY);
        context.lineTo(toX,toY);
        context.stroke();
    }

}
