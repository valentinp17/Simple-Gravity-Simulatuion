package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.models.MassPoint;
import sample.models.World;

public class MainController {
    private World world;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField textFieldMass;

    @FXML
    private TextField textFieldPosX;

    @FXML
    private TextField textFieldPosY;

    @FXML
    private TextField textFieldVelX;

    @FXML
    private TextField textFieldVelY;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox checkVectors;

    public void onStart(ActionEvent actionEvent) {
        System.out.println("Start Button pressed");

        if(!world.isStart()) {
            world.start();
        }
    }

    public void onStop(ActionEvent actionEvent) {
        System.out.println("Stop Button pressed");
        world.stop();
    }

    public void btnPreview(ActionEvent actionEvent) {
        if(!textFieldMass.getText().isEmpty() && !textFieldPosX.getText().isEmpty() &&
                !textFieldPosY.getText().isEmpty() && !textFieldVelX.getText().isEmpty() &&
                !textFieldVelY.getText().isEmpty()) {
            world.stop();
            world.draw();
            MassPoint p = new MassPoint(Double.parseDouble(textFieldMass.getText()),
                    Double.parseDouble(textFieldPosX.getText()),
                    Double.parseDouble(textFieldPosY.getText()),
                    Double.parseDouble(textFieldVelX.getText()),
                    Double.parseDouble(textFieldVelY.getText()),
                    colorPicker.getValue(),
                    world.getGc());
            p.draw();
        }
    }

    public void btnAdd(ActionEvent actionEvent) {
        if(!textFieldMass.getText().isEmpty() && !textFieldPosX.getText().isEmpty() &&
                !textFieldPosY.getText().isEmpty() && !textFieldVelX.getText().isEmpty() &&
                !textFieldVelY.getText().isEmpty()) {
            world.stop();
            world.getPoints().add(new MassPoint(Double.parseDouble(textFieldMass.getText()),
                    Double.parseDouble(textFieldPosX.getText()),
                    Double.parseDouble(textFieldPosY.getText()),
                    Double.parseDouble(textFieldVelX.getText()),
                    Double.parseDouble(textFieldVelY.getText()),
                    colorPicker.getValue(),
                    world.getGc()));
            world.getPoints().get(world.getPoints().size() - 1).draw();
        }

    }

    public void changeVectors(ActionEvent actionEvent) {
        world.setVectorsFlag(checkVectors.isSelected());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
