package com.dosse.bwentrain.player.dsp;

import com.dosse.bwentrain.sound.ISoundDevice;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sound device wrapper that processes audio through a list of effects
 * before forwarding it to the actual backend.
 */
public class EffectSoundDevice implements ISoundDevice {

    private final ISoundDevice backend;
    private final CopyOnWriteArrayList<Effect> effects = new CopyOnWriteArrayList<>();

    public EffectSoundDevice(ISoundDevice backend) {
        this.backend = backend;
    }

    public void addEffect(Effect e) {
        if (e != null) {
            effects.addIfAbsent(e);
        }
    }

    public void removeEffect(Effect e) {
        effects.remove(e);
    }

    public void clearEffects() {
        effects.clear();
    }

    public List<Effect> getEffects() {
        return List.copyOf(effects);
    }

    @Override
    public boolean isClosed() {
        return backend.isClosed();
    }

    @Override
    public void close() {
        backend.close();
    }

    @Override
    public void open() {
        backend.open();
    }

    @Override
    public int getChannelCount() {
        return backend.getChannelCount();
    }

    @Override
    public int getBitsPerSample() {
        return backend.getBitsPerSample();
    }

    @Override
    public float getSampleRate() {
        return backend.getSampleRate();
    }

    @Override
    public void write(float[] buffer) {
        for (Effect e : effects) {
            e.process(buffer, 0, buffer.length);
        }
        backend.write(buffer);
    }

    @Override
    public void setVolume(float volume) {
        backend.setVolume(volume);
    }

    @Override
    public float getVolume() {
        return backend.getVolume();
    }
}

