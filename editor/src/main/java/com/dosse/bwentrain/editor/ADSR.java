package com.dosse.bwentrain.editor;

/**
 * Simple ADSR (Attack, Decay, Sustain, Release) envelope.
 */
public class ADSR {
    private final int attack;
    private final int decay;
    private final float sustain;
    private final int release;

    public ADSR(float attackS, float decayS, float sustainLevel, float releaseS, float sampleRate) {
        this.attack = Math.max(1, (int) (attackS * sampleRate));
        this.decay = Math.max(1, (int) (decayS * sampleRate));
        this.release = Math.max(1, (int) (releaseS * sampleRate));
        this.sustain = sustainLevel;
    }

    /**
     * Applies the envelope in-place to {@code buffer}.
     */
    public void apply(float[] buffer) {
        int n = buffer.length;
        for (int i = 0; i < n; i++) {
            float env;
            if (i < attack) {
                env = (float) i / attack;
            } else if (i < attack + decay) {
                float f = (i - attack) / (float) decay;
                env = 1f - (1f - sustain) * f;
            } else if (i < n - release) {
                env = sustain;
            } else {
                float f = (i - (n - release)) / (float) release;
                env = sustain * (1f - f);
            }
            buffer[i] *= env;
        }
    }
}

