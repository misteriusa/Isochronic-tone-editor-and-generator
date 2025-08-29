package com.dosse.bwentrain.editor;

import com.dosse.bwentrain.core.Preset;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/** Utility class for reading and writing `.sine-session` files. */
public final class SessionIO {
    private SessionIO() {}

    /** Current schema version for exported sessions. */
    public static final String SCHEMA_VERSION = "1";

    /**
     * Export the given preset to a session file along with metadata.
     * @param preset preset to persist
     * @param file destination file
     * @throws Exception if writing fails
     */
    public static void exportSession(Preset preset, File file) throws Exception {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.newDocument();
        Element root = doc.createElement("sine-session");
        root.setAttribute("schemaVersion", SCHEMA_VERSION);
        doc.appendChild(root);

        Element meta = doc.createElement("metadata");
        Element savedAt = doc.createElement("savedAt");
        savedAt.setTextContent(Long.toString(System.currentTimeMillis()));
        meta.appendChild(savedAt);
        root.appendChild(meta);

        Element presetEl = (Element) doc.importNode(preset.toXML(), true);
        root.appendChild(presetEl);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(file));
    }

    /** Wrapper for imported session data. */
    public static final class Session {
        public final Preset preset;
        public final long savedAt;
        private Session(Preset p, long s) { this.preset = p; this.savedAt = s; }
    }

    /**
     * Load a session from disk validating the schema version.
     * @param file session file to read
     * @return imported session data
     * @throws Exception if the file is invalid or uses an unsupported schema
     */
    public static Session importSession(File file) throws Exception {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(file);
        Element root = doc.getDocumentElement();
        String version = root.getAttribute("schemaVersion");
        if (!SCHEMA_VERSION.equals(version)) {
            throw new IllegalArgumentException(Utils.getLocString("SESSION_UNSUPPORTED_VERSION") + ": " + version);
        }
        Element presetEl = (Element) root.getElementsByTagName("preset").item(0);
        Preset p = new Preset(presetEl);
        Element metaEl = (Element) root.getElementsByTagName("metadata").item(0);
        long saved = 0L;
        if (metaEl != null) {
            Element sEl = (Element) metaEl.getElementsByTagName("savedAt").item(0);
            if (sEl != null) {
                saved = Long.parseLong(sEl.getTextContent());
            }
        }
        return new Session(p, saved);
    }
}
