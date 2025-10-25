package com.dosse.bwentrain.cli;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatchExportTest {

    @Test
    void exportsAllFilesInDirectory() throws Exception {
        Path input = Files.createTempDirectory("presets");
        Path output = Files.createTempDirectory("audio");
        Files.writeString(input.resolve("a.preset"), "preset");
        Files.writeString(input.resolve("b.preset"), "preset");

        Main.setExporter((in, out, loop) -> Files.writeString(Path.of(out), "audio"));

        Main.batchExport(input.toString(), output.toString(), 0, "wav");

        assertTrue(Files.exists(output.resolve("a.wav")));
        assertTrue(Files.exists(output.resolve("b.wav")));
    }
}
