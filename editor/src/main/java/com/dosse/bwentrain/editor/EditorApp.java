package com.dosse.bwentrain.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Entry point for the JavaFX-based editor.
 */
public class EditorApp extends Application {
    @Override
    public void start(Stage stage) {
        TimelineModel model = new TimelineModel();
        TimelineView view = new TimelineView(model);
        BorderPane root = new BorderPane(view); // center the timeline
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("SINE Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
