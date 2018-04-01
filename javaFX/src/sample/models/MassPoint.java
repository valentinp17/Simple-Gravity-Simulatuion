package sample.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MassPoint {
    private static final int HEIGHT_CIRCLE = 15;
    private static final int WIGHT_CIRCLE = 15;
    private static final double G = 6.67408;

    private double mass;
    private double posX, posY;
    private double velX, velY;
    private double accX, accY;
    private Color color;
    private GraphicsContext gc;


    public MassPoint(MassPoint point) {
        this.mass = point.mass;
        this.posX = point.posX;
        this.posY = point.posY;
        this.velX = point.velX;
        this.velY = point.velY;
        this.color = point.color;
        this.gc = point.gc;
    }

    public MassPoint(double mass, double posX, double posY, double velX, double velY, Color color,GraphicsContext gc) {
        this.mass = mass;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.color = color;
        this.gc = gc;
    }

    public double distance(MassPoint point) {
        return sqrt((posX - point.posX) * (posX - point.posX) + (posY - point.posY) * (posY - point.posY));
    }

    public void draw() {
        gc.setFill(color);
        gc.setStroke(color.darker());

        gc.strokeOval(posX, posY, WIGHT_CIRCLE, HEIGHT_CIRCLE);
        gc.fillOval(posX, posY, WIGHT_CIRCLE, HEIGHT_CIRCLE);
    }

    public void drawVectors() {
        gc.setLineWidth(1);
        gc.setStroke(new Color(0.0824, 0.098, 1, 1));
        gc.strokeLine(posX + WIGHT_CIRCLE / 2, posY + HEIGHT_CIRCLE / 2,
                (posX + WIGHT_CIRCLE / 2) + velX, (posY + HEIGHT_CIRCLE / 2) + velY);


        gc.setStroke(new Color(0.2431, 1, 0.1059, 1));
        gc.strokeLine(posX + WIGHT_CIRCLE / 2, posY + HEIGHT_CIRCLE / 2,
                (posX + WIGHT_CIRCLE / 2) + accX, (posY + HEIGHT_CIRCLE / 2) + accY);
    }

    public static List<MassPoint> updatePoints(double dt, List<MassPoint> points) {
        List<MassPoint> points2 = new ArrayList<>();

        for(MassPoint p: points) {
            MassPoint point = new MassPoint(p);
            double ax = 0;
            double ay = 0;
            for(MassPoint p2: points) {
                if(p != p2) {
                    ax += G * ((p2.mass * (p2.posX - p.posX)) / (pow(p.distance(p2), 3 / 2)));
                    ay += G * ((p2.mass * (p2.posY - p.posY)) / (pow(p.distance(p2), 3 / 2)));
                }
            }
            point.accX = ax;
            point.accY = ay;
            point.velX += (point.accX) / dt;
            point.velY += (point.accY) / dt;
            point.posX += (point.velX) / dt;
            point.posY += (point.velY) / dt;

            points2.add(point);
        }

        return points2;
    }

    @Override
    public String toString() {
        return "MassPoint{" +
                "mass=" + mass +
                ", posX=" + posX +
                ", posY=" + posY +
                ", velX=" + velX +
                ", velY=" + velY +
                ", accX=" + accX +
                ", accY=" + accY +
                '}';
    }
}













