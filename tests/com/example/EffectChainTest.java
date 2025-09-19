package com.example;

import com.dosse.bwentrain.player.dsp.*;
import com.dosse.bwentrain.sound.ISoundDevice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EffectChainTest {

    private static class DummyDevice implements ISoundDevice {
        float[] last;
        @Override
        public boolean isClosed() { return false; }
        @Override
        public void close() {}
        @Override
        public void open() {}
        @Override
        public int getChannelCount() { return 1; }
        @Override
        public int getBitsPerSample() { return 16; }
        @Override
        public float getSampleRate() { return 44100; }
        @Override
        public void write(float[] buffer) { last = buffer; }
        @Override
        public void setVolume(float volume) {}
        @Override
        public float getVolume() { return 1f; }
    }

    private static class MultiplyEffect implements Effect {
        private final float factor;
        MultiplyEffect(float factor){ this.factor=factor; }
        @Override
        public void process(float[] buffer, int offset, int length){
            for(int i=offset;i<offset+length;i++) buffer[i]*=factor;
        }
    }

    private static class AddEffect implements Effect {
        private final float amount;
        AddEffect(float amount){ this.amount=amount; }
        @Override
        public void process(float[] buffer, int offset, int length){
            for(int i=offset;i<offset+length;i++) buffer[i]+=amount;
        }
    }

    @Test
    public void testEffectOrder() {
        DummyDevice backend = new DummyDevice();
        EffectSoundDevice device = new EffectSoundDevice(backend);
        device.addEffect(new MultiplyEffect(2f));
        device.addEffect(new AddEffect(1f));
        float[] buf = new float[]{1f, -1f};
        device.write(buf);
        assertArrayEquals(new float[]{3f, -1f}, backend.last, 1e-6f);

        device.clearEffects();
        device.addEffect(new AddEffect(1f));
        device.addEffect(new MultiplyEffect(2f));
        buf = new float[]{1f, -1f};
        device.write(buf);
        assertArrayEquals(new float[]{4f, 0f}, backend.last, 1e-6f);
    }

    @Test
    public void testBufferIntegrity() {
        DummyDevice backend = new DummyDevice();
        EffectSoundDevice device = new EffectSoundDevice(backend);
        float[] buf = new float[]{0.1f, 0.2f, 0.3f};
        device.write(buf);
        assertSame(buf, backend.last);
        assertEquals(3, backend.last.length);
    }
}

