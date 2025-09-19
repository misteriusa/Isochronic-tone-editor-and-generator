package com.example;

import com.dosse.binaural.BinauralEnvelope;
import com.dosse.bwentrain.editor.ADSR;
import com.dosse.bwentrain.editor.Waveform;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BinauralEnvelopeTest {

    @Test
    public void testStringAndXmlRoundTrip() throws Exception {
        BinauralEnvelope env = new BinauralEnvelope();
        env.setBaseF(220);
        env.setPoint(0.0, 4.0, 1.0, 0.5, 1.0, 0.3, 1.0);
        env.setPoint(1.0, 6.0, 1.0, 0.4, 1.0, 0.2, 1.0);

        String asString = env.toString();
        BinauralEnvelope fromString = BinauralEnvelope.fromString(asString);
        assertEquals(asString, fromString.toString());

        String asXml = env.toXML();
        BinauralEnvelope fromXml = BinauralEnvelope.fromXML(asXml);
        assertEquals(env.toString(), fromXml.toString());

        byte[] hes = env.toHES();
        BinauralEnvelope fromHes = BinauralEnvelope.fromHES(hes);
        assertEquals(env.toString(), fromHes.toString());
    }

    @Test
    public void testWaveformsAndEnvelope() {
        int samples = 100;
        float sr = 1000f;
        for (Waveform wf : Waveform.values()) {
            float[] buf = new float[samples];
            wf.generate(10f, sr, buf);
            assertEquals(samples, buf.length);
        }
        ADSR adsr = new ADSR(0.01f, 0.1f, 0.5f, 0.1f, sr);
        float[] tone = new float[samples];
        Arrays.fill(tone, 1f);
        adsr.apply(tone);
        assertEquals(0f, tone[0], 1e-3);
        assertEquals(0f, tone[samples - 1], 1e-3);
        assertTrue(tone[samples / 2] > 0f);
    }
}
