# `.sine-session` format

The `.sine-session` file stores an entire SINE Editor session including all tracks, the preset and additional metadata.

```xml
<sine-session schemaVersion="1">
  <metadata>
    <savedAt>UTC epoch milliseconds when exported</savedAt>
  </metadata>
  <preset>
    <!-- `Preset` serialized via `Preset.toXML()` -->
  </preset>
</sine-session>
```

* `schemaVersion` — used to manage future migrations. The editor currently supports only version `1`.
* `metadata/savedAt` — export timestamp (UTC epoch milliseconds).
* `preset` — complete preset definition containing tracks and envelopes.
