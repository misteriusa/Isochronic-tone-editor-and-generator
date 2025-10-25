package com.example;

import com.dosse.bwentrain.core.EntrainmentTrack;
import com.dosse.bwentrain.core.Preset;
import com.dosse.bwentrain.editor.SessionIO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class SessionIOTest {
    @Test
    public void roundTripComplexSession() throws Exception {
        Preset p = new Preset(600, -1, "t", "a", "d");
        p.addEntrainmentTrack();
        EntrainmentTrack t0 = p.getEntrainmentTrack(0);
        t0.getBaseFrequencyEnvelope().setVal(0, 440);
        t0.getBaseFrequencyEnvelope().setVal(300, 880);
        t0.getVolumeEnvelope().setVal(0, 1);
        t0.getVolumeEnvelope().setVal(300, 0.5f);
        t0.getEntrainmentFrequencyEnvelope().setVal(0, 10);
        t0.getEntrainmentFrequencyEnvelope().setVal(300, 12);
        EntrainmentTrack t1 = p.getEntrainmentTrack(1);
        t1.getBaseFrequencyEnvelope().setVal(0, 220);
        t1.getVolumeEnvelope().setVal(0, 0.8f);
        t1.getEntrainmentFrequencyEnvelope().setVal(0, 7);
        p.getNoiseEnvelope().setVal(0, 0.1f);
        p.getNoiseEnvelope().setVal(200, 0.2f);
        File tmp = File.createTempFile("session", ".sine-session");
        SessionIO.exportSession(p, tmp);
        SessionIO.Session s = SessionIO.importSession(tmp);
        assertEquals(p, s.preset);
        assertTrue(s.savedAt > 0);
    }
}
