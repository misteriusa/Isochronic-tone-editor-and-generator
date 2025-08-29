package com.dosse.bwentrain.player;

import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * Simple UI component allowing the user to enable session synchronization and
 * choose a backend. This is a minimal JavaFX pane used by the demo player.
 */
public class SyncSettingsPane extends VBox {
    private final CheckBox enableSync = new CheckBox("Enable session sync");
    private final ComboBox<String> backend = new ComboBox<>(
            FXCollections.observableArrayList("S3", "Firebase"));

    public SyncSettingsPane() {
        getChildren().addAll(enableSync, backend);
        backend.getSelectionModel().selectFirst();
    }

    public boolean isSyncEnabled() {
        return enableSync.isSelected();
    }

    public String getSelectedBackend() {
        return backend.getValue();
    }
}
