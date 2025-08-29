package com.dosse.bwentrain.editor;

import java.util.Random;

/**
 * Basic waveforms used by the isochronic renderer.
 */
public enum Waveform {
    SINE((f, sr, b) -> {
        double inc = 2 * Math.PI * f / sr;
        double phase = 0;
        for (int i = 0; i < b.length; i++) {
            b[i] = (float) Math.sin(phase);
            phase += inc;
        }
    }),
    SQUARE((f, sr, b) -> {
        double inc = 2 * Math.PI * f / sr;
        double phase = 0;
        for (int i = 0; i < b.length; i++) {
            b[i] = Math.sin(phase) >= 0 ? 1f : -1f;
            phase += inc;
        }
    }),
    SAW((f, sr, b) -> {
        double inc = f / sr;
        double phase = 0;
        for (int i = 0; i < b.length; i++) {
            b[i] = (float) (2 * (phase - Math.floor(phase + 0.5)));
            phase += inc;
        }
    }),
    NOISE((f, sr, b) -> {
        Random r = new Random();
        for (int i = 0; i < b.length; i++) {
            b[i] = r.nextFloat() * 2 - 1;
        }
    });

    @FunctionalInterface
    interface Generator {
        void generate(float freq, float sampleRate, float[] buffer);
    }

    private final Generator gen;

    Waveform(Generator g) {
        this.gen = g;
    }

    /**
     * Fills {@code buffer} with the waveform at the given frequency.
     */
    public void generate(float freq, float sampleRate, float[] buffer) {
        gen.generate(freq, sampleRate, buffer);
    }
}

