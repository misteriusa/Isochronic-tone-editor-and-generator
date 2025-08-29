package com.dosse.bwentrain.editor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Holds audio clips dropped on the timeline.
 */
public class TimelineModel {
    private final List<AudioClip> clips = new ArrayList<>();

    public void addClip(File file) throws IOException, UnsupportedAudioFileException {
        clips.add(new AudioClip(file));
    }

    public List<AudioClip> getClips() {
        return Collections.unmodifiableList(clips);
    }
}
