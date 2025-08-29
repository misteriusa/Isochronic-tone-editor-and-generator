package com.dosse.bwentrain.editor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import javax.sound.sampled.*;
import org.junit.jupiter.api.Test;

/**
 * Integration tests for TimelineModel.
 */
public class TimelineIntegrationTest {
    @Test
    void multiTrackAdditionStoresClips() throws Exception {
        TimelineModel model = new TimelineModel();
        model.addClip(createTempWav(440));
        model.addClip(createTempWav(660));
        assertEquals(2, model.getClips().size());
    }

    private File createTempWav(int freq) throws Exception {
        int sampleRate = 44100;
        byte[] data = new byte[sampleRate * 2];
        for (int i = 0; i < sampleRate; i++) {
            double angle = 2 * Math.PI * freq * i / sampleRate;
            short val = (short) (Math.sin(angle) * Short.MAX_VALUE);
            data[i * 2] = (byte) (val & 0xff);
            data[i * 2 + 1] = (byte) ((val >> 8) & 0xff);
        }
        File out = File.createTempFile("clip", ".wav");
        AudioFormat fmt = new AudioFormat(sampleRate, 16, 1, true, false);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
                AudioInputStream ais = new AudioInputStream(bais, fmt, sampleRate)) {
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, out);
        }
        out.deleteOnExit();
        return out;
    }
}
