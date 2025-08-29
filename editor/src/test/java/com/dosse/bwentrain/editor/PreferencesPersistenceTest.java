package com.dosse.bwentrain.editor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.prefs.Preferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Verifies that theme and high-contrast preferences persist across calls,
 * mimicking application sessions.
 */
public class PreferencesPersistenceTest {

    @BeforeEach
    void reset() throws Exception {
        Preferences.userNodeForPackage(PreferencesManager.class).clear();
    }

    @Test
    void themePersistsAcrossSessions() {
        PreferencesManager.setTheme("dark");
        assertEquals("dark", PreferencesManager.getTheme());
    }

    @Test
    void highContrastPersistsAcrossSessions() {
        PreferencesManager.setHighContrast(true);
        assertTrue(PreferencesManager.isHighContrast());
    }
}
