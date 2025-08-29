package com.dosse.bwentrain.player.dsp;

/**
 * A very small feedback delay based reverb.
 * Keeps internal state so process is synchronized.
 */
public class ReverbEffect implements Effect {

    private final float[] delay;
    private int index = 0;
    private final float decay;

    /**
     * Creates a new reverb effect.
     *
     * @param size  delay buffer size in samples
     * @param decay feedback amount (0..1)
     */
    public ReverbEffect(int size, float decay) {
        this.delay = new float[size];
        this.decay = decay;
    }

    /**
     * Creates a reverb effect with default settings.
     */
    public ReverbEffect() {
        this(4410, 0.5f); // ~0.1s delay at 44.1kHz
    }

    @Override
    public synchronized void process(float[] buffer, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            float out = buffer[i] + delay[index] * decay;
            delay[index] = out;
            buffer[i] = out;
            index = (index + 1) % delay.length;
        }
    }
}

