package sample;

import java.util.ArrayList;
import java.util.List;

public class GraphicData {
    private List<Info> graphic;

    public GraphicData() {
        graphic = new ArrayList<>();
    }

    public int addPoint(Info point) {
        synchronized (graphic) {
            if(graphic.size()==0){
                if(point.getQuantity()!=2) return -1;
            }
            graphic.add(point);
            return graphic.size() - 1;
        }
    }

    public void removeAll() {
        synchronized (graphic) {
            graphic.clear();
        }
    }

    public List<Info> getGraphic() {
        synchronized (graphic) {
            return new ArrayList<>(graphic);
        }
    }
}
