# Dependency Inventory

The project bundles several JARs under the `SINE/lib` folder which are copied
into `SINE-Editor/lib` and `SINE-CLI/lib` as well. The main artifacts are:

- JNA 4.2.1 – available from Maven Central.
- Java-Flac-Encoder 0.3.1 – older version; 0.3.7 exists on Maven Central.
- JavaLAME – no Maven Central entry found.
- LibBWEntrainment and its sound backends/renderers – project specific JARs.
- apple-0.0.2 – unclear origin; not found on Maven Central.

These libraries should be reviewed for license compatibility before migrating to
a new build system.  Where possible, replace the local copies with dependencies
from Maven Central.
