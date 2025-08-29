package com.dosse.bwentrain.player;

import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

/**
 * Displays a disclaimer on first run to satisfy medical and licensing requirements.
 */
public final class FirstRunWizard {
    private static final String PREF_KEY = "disclaimerAccepted";

    private FirstRunWizard() {
        // utility class
    }

    /**
     * Show the disclaimer dialog if the user has not previously accepted it.
     */
    public static void maybeShow() {
        Preferences prefs = Preferences.userNodeForPackage(FirstRunWizard.class);
        if (!prefs.getBoolean(PREF_KEY, false)) {
            JOptionPane.showMessageDialog(
                    null,
                    "This software is for informational purposes only and is not a medical device. Consult a qualified healthcare professional before use.",
                    "Disclaimer",
                    JOptionPane.INFORMATION_MESSAGE
            );
            prefs.putBoolean(PREF_KEY, true);
        }
    }
}
