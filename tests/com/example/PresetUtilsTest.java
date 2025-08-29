package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.dosse.bwentrain.editor.Utils;

/**
 * Tests for JSON preset load/save utilities.
 */
public class PresetUtilsTest {

    @Test
    public void roundTripPreset() throws Exception {
        JSONObject preset = new JSONObject();
        preset.put("name", "Test");
        preset.put("frequency", 5);
        preset.put("gain", 0.5);
        preset.put("author", "me");
        preset.put("description", "desc");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Utils.savePreset(preset, out);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        JSONObject loaded = Utils.loadPreset(in);
        assertEquals(preset.get("name"), loaded.get("name"));
        assertEquals(preset.get("frequency"), loaded.get("frequency"));
    }

    @Test
    public void missingRequiredField() throws Exception {
        JSONObject preset = new JSONObject();
        preset.put("frequency", 5);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        assertThrows(ValidationException.class, () -> Utils.savePreset(preset, out));
    }

    @Test
    public void unsupportedVersion() throws Exception {
        String json = "{\"version\":99,\"name\":\"Test\",\"frequency\":5}";
        ByteArrayInputStream in = new ByteArrayInputStream(json.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        assertThrows(IOException.class, () -> Utils.loadPreset(in));
    }
}
