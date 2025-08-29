package com.dosse.bwentrain.editor;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.sound.sampled.*;

/**
 * Simple representation of an audio clip with a decimated waveform.
 */
public class AudioClip {
    private final File file; // original file reference
    private final float[] waveform; // normalized waveform samples [-1,1]

    public AudioClip(File file) throws IOException, UnsupportedAudioFileException {
        this.file = Objects.requireNonNull(file);
        this.waveform = extractWaveform(file, 1000); // sample 1000 points for display
    }

    public File getFile() {
        return file;
    }

    public float[] getWaveform() {
        return waveform;
    }

    private static float[] extractWaveform(File f, int points)
            throws IOException, UnsupportedAudioFileException {
        try (AudioInputStream in = AudioSystem.getAudioInputStream(f)) {
            AudioFormat fmt = in.getFormat();
            byte[] data = in.readAllBytes();
            int frameSize = fmt.getFrameSize();
            int totalFrames = data.length / frameSize;
            int step = Math.max(1, totalFrames / points);
            float[] out = new float[totalFrames / step];
            for (int i = 0, j = 0; j < out.length && i < data.length; j++, i += step * frameSize) {
                int idx = i;
                int sample = fmt.isBigEndian() ? (data[idx] << 8) | (data[idx + 1] & 0xff)
                        : (data[idx + 1] << 8) | (data[idx] & 0xff);
                out[j] = sample / 32768f;
            }
            return out;
        }
    }
}
