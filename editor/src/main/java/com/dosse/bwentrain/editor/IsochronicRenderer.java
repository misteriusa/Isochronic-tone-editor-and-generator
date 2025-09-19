package com.dosse.bwentrain.editor;

/**
 * Minimal isochronic tone renderer using a selectable waveform and ADSR envelope.
 */
public class IsochronicRenderer {
    private final Waveform waveform;
    private final ADSR adsr;

    public IsochronicRenderer(Waveform waveform, ADSR adsr) {
        this.waveform = waveform;
        this.adsr = adsr;
    }

    /**
     * Generates an audio buffer for the given frequency and length.
     */
    public float[] render(float frequency, float sampleRate, int seconds) {
        int n = (int) (sampleRate * seconds);
        float[] buffer = new float[n];
        waveform.generate(frequency, sampleRate, buffer);
        if (adsr != null) {
            adsr.apply(buffer);
        }
        return buffer;
    }
}

