package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controllers.MainController;
import sample.models.World;

public class Main extends Application {
    private static final int TIME_STEP = 1000 / 60;

    public static Thread thread;
    private World world;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/main.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Simple Gravity Simulation");
        primaryStage.setMinHeight(720);
        primaryStage.setMinWidth(1280);
        primaryStage.setResizable(false);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        MainController mainController = fxmlLoader.getController();
        GraphicsContext gc = mainController.getCanvas().getGraphicsContext2D();

        world = new World(gc);

        mainController.setWorld(world);
        world.start();
        world.draw();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(TIME_STEP), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(world.isStart()) {
                    world.update(TIME_STEP);
                    world.draw();
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
