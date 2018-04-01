package sample.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

public class World {
    private boolean running;
    private boolean vectorsFlag;

    private GraphicsContext gc;
    private List<MassPoint> points = new ArrayList<>();

    public World(GraphicsContext gc) {
        this.gc = gc;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void update(double dt) {

        points = MassPoint.updatePoints(dt, points);
    }

    public void draw() {
        clear();

        //draw frame and coordinate grid
        gc.setStroke(new Color(0.2078, 0.2078, 0.2078, 0.3176));
        gc.setLineWidth(1);
        gc.strokeRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for(int i = 0; i < gc.getCanvas().getWidth(); i += 50) {
            gc.strokeLine(i, 0, i, gc.getCanvas().getHeight());
        }

        for(int i = 0; i < gc.getCanvas().getHeight(); i += 50) {
            gc.strokeLine(0, i, gc.getCanvas().getWidth(), i);
        }

        //draw points
        for(MassPoint p: points) {
            p.draw();
            if(vectorsFlag) {
                p.drawVectors();
            }

        }
    }

    public void clear() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public boolean isStart() {
        return running;
    }

    public List<MassPoint> getPoints() {
        return points;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setVectorsFlag(boolean flag) {
        vectorsFlag = flag;
    }

}
