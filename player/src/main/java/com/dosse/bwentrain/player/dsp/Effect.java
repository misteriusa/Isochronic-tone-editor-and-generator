package com.dosse.bwentrain.player.dsp;

/**
 * Simple interface for audio effects applied to raw sample buffers.
 */
public interface Effect {
    /**
     * Processes the given buffer in-place.
     *
     * @param buffer the audio sample buffer.
     * @param offset starting index in the buffer.
     * @param length number of samples to process.
     */
    void process(float[] buffer, int offset, int length);
}

