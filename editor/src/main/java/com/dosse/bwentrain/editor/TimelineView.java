package com.dosse.bwentrain.editor;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.AccessibleRole;
import java.io.File;

/**
 * Canvas-based timeline that displays waveforms and accepts drag & drop.
 */
public class TimelineView extends StackPane {
    private final TimelineModel model;
    private final Canvas canvas = new Canvas();

    public TimelineView(TimelineModel model) {
        this.model = model;
        getChildren().add(canvas);
        setAccessibleRole(AccessibleRole.NODE);
        setAccessibleText("Audio timeline");
        setOnDragOver(this::handleDragOver);
        setOnDragDropped(this::handleDrop);
        widthProperty().addListener((o, a, b) -> resize());
        heightProperty().addListener((o, a, b) -> resize());
    }

    private void handleDragOver(DragEvent e) {
        if (e.getDragboard().hasFiles()) {
            e.acceptTransferModes(TransferMode.COPY);
        }
        e.consume();
    }

    private void handleDrop(DragEvent e) {
        e.getDragboard().getFiles().forEach(this::addFile);
        e.setDropCompleted(true);
        e.consume();
    }

    private void addFile(File f) {
        try {
            model.addClip(f);
            redraw();
        } catch (Exception ex) {
            // ignore invalid files for now
        }
    }

    private void resize() {
        canvas.setWidth(getWidth());
        canvas.setHeight(getHeight());
        redraw();
    }

    private void redraw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double trackHeight = canvas.getHeight() / Math.max(1, model.getClips().size());
        double y = 0;
        for (AudioClip clip : model.getClips()) {
            drawWaveform(g, clip.getWaveform(), y, trackHeight);
            y += trackHeight;
        }
    }

    private void drawWaveform(GraphicsContext g, float[] data, double y, double h) {
        g.setStroke(Color.DARKBLUE);
        g.beginPath();
        double w = canvas.getWidth();
        for (int i = 0; i < data.length; i++) {
            double x = i * w / data.length;
            double sy = y + h / 2 - data[i] * h / 2;
            if (i == 0) {
                g.moveTo(x, sy);
            } else {
                g.lineTo(x, sy);
            }
        }
        g.stroke();
    }
}
