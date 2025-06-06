package com.example;

import com.dosse.bwentrain.sound.backends.wav.WavFileSoundBackend;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class WavFileSoundBackendTest {

    @Test
    public void testCreateWavFile() throws Exception {
        File tmp = File.createTempFile("test", ".wav");
        tmp.deleteOnExit();

        WavFileSoundBackend backend = new WavFileSoundBackend(tmp.getAbsolutePath(), 44100, 1);
        backend.open();
        backend.write(new float[441]);
        backend.close();

        assertTrue(tmp.exists());
        assertTrue(Files.size(tmp.toPath()) > 44); // header size
    }
}
