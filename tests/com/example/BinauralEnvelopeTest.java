package com.example;

import com.dosse.binaural.BinauralEnvelope;
import org.junit.jupiter.api.Test;

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
}
