package com.dosse.bwentrain.player.dsp;

/**
 * Simple first order low pass filter.
 */
public class FilterEffect implements Effect {

    private final float alpha;
    private float prev;

    /**
     * @param alpha smoothing factor in range (0,1)
     */
    public FilterEffect(float alpha) {
        this.alpha = alpha;
    }

    /**
     * Default filter with moderate smoothing.
     */
    public FilterEffect() {
        this(0.5f);
    }

    @Override
    public synchronized void process(float[] buffer, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            prev = prev + alpha * (buffer[i] - prev);
            buffer[i] = prev;
        }
    }
}

