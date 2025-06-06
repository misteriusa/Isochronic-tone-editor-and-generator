package com.dosse.bwentrain.player;

import com.dosse.bwentrain.core.Preset;
import com.dosse.bwentrain.renderers.isochronic.IsochronicRenderer;
import com.dosse.bwentrain.sound.backends.pc.PCSoundBackend;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Minimal JavaFX version of the player UI. It implements open, play, pause and
 * volume controls in order to demonstrate playback without Swing.
 */
public class MainFX extends Application {

    private IsochronicRenderer renderer;
    private final Slider progress = new Slider();
    private final Slider volume = new Slider(0, 1, 1);

    @Override
    public void start(Stage stage) {
        stage.setTitle("SINE JavaFX Prototype");
        Button open = new Button("Open");
        Button play = new Button("Play");
        Button pause = new Button("Pause");

        open.setOnAction(e -> openPreset(stage));
        play.setOnAction(e -> { if (renderer != null) renderer.play(); });
        pause.setOnAction(e -> { if (renderer != null) renderer.pause(); });

        volume.valueProperty().addListener((obs, o, n) -> {
            if (renderer != null) {
                renderer.setVolume(n.floatValue());
            }
        });

        BorderPane root = new BorderPane();
        HBox controls = new HBox(5, open, play, pause, new Label("Volume"), volume);
        root.setTop(controls);
        root.setCenter(progress);
        Scene scene = new Scene(root, 400, 120);
        stage.setScene(scene);
        stage.show();

        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (renderer != null && renderer.getLength() > 0) {
                    progress.setValue(renderer.getPosition());
                    progress.setMax(renderer.getLength());
                }
            }
        };
        t.start();
    }

    private void openPreset(Stage stage) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("SINE Preset", "*.sin"));
        File f = fc.showOpenDialog(stage);
        if (f == null) {
            return;
        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(f.getAbsolutePath()));
            if (renderer != null) {
                renderer.stopPlaying();
            }
            Preset p = new Preset(doc.getDocumentElement());
            renderer = new IsochronicRenderer(p, new PCSoundBackend(44100, 1), -1);
            renderer.setVolume((float) volume.getValue());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
