package com.dosse.bwentrain.editor;

import java.util.prefs.Preferences;

/**
 * Small utility class wrapping {@link Preferences} to persist UI options such
 * as theme selection and high-contrast mode. Using a dedicated helper keeps
 * access to the backing store consistent and testable.
 */
public final class PreferencesManager {

    private static final Preferences PREFS =
            Preferences.userNodeForPackage(PreferencesManager.class);
    private static final String KEY_THEME = "theme";
    private static final String KEY_HIGH_CONTRAST = "highContrast";

    private PreferencesManager() {
        // utility class
    }

    /**
     * Returns the preferred theme name. Defaults to {@code "light"}.
     */
    public static String getTheme() {
        return PREFS.get(KEY_THEME, "light");
    }

    /**
     * Persists the preferred theme name.
     */
    public static void setTheme(String theme) {
        PREFS.put(KEY_THEME, theme);
    }

    /**
     * Whether the high-contrast mode is enabled. Defaults to {@code false}.
     */
    public static boolean isHighContrast() {
        return PREFS.getBoolean(KEY_HIGH_CONTRAST, false);
    }

    /**
     * Persists the high-contrast preference.
     */
    public static void setHighContrast(boolean highContrast) {
        PREFS.putBoolean(KEY_HIGH_CONTRAST, highContrast);
    }
}
