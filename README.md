# SINE Isochronic Entrainer
SINE Isochronic Entrainer is a Free and Open Source Brainwave Entrainment application.

Brainwave Entrainment is the practice of using sounds, visual stimuli or even electromagnetic fields to alter the frequency of brainwaves.
It can be done for several reasons: people with insomnia can take great benefit from it, but it can also be used to improve your attention span, and even simulate the effects of some recreational drugs.
The effect is not permanent, and fades away in less than a minute after the stimuli is removed.

Isochronic tones are a commonly used aural stimuli for Brainwave Entrainment, consisting of short pulses of a sine wave, varying in frequency. Unlike binaural beats, isochronic tones can be played on speakers. 

 
## Website
[SINE Isochronic Entrainer](https://sine.fdossena.com/)

## Download
[Installer for Windows](https://downloads.fdossena.com/geth.php?r=sine-win)

[Mac App](https://downloads.fdossena.com/geth.php?r=sine-mac)

[.deb package for Ubuntu, Debian, etc.](https://downloads.fdossena.com/geth.php?r=sine-deb)

[Multiplatform](https://downloads.fdossena.com/geth.php?r=sine-pcbin) (Requires [Java](https://java.com))

## Compatibility
* Windows
* macOS
* Any platform supported by Java SE 7 or newer
 
codex/add-javafx-prototype-for-playback-controls
## Usage
Import the projects into Netbeans.

### JavaFX prototype
The `player` module contains an experimental JavaFX entry point called `MainFX`.
To run it you need OpenJFX on your classpath:
```bash
javac --module-path /path/to/javafx/lib --add-modules javafx.controls 
      -cp "SINE/lib/*" -d build SINE/src/com/dosse/bwentrain/player/MainFX.java
java --module-path /path/to/javafx/lib --add-modules javafx.controls 
     -cp "build:SINE/lib/*" com.dosse.bwentrain.player.MainFX
```

The Swing based UI remains the default; this prototype only demonstrates basic playback controls.

_SETUP contains all the files used to build the GNU/Linux packages, the installer for Windows and the Mac app packages.
To build the installer for Windows, you'll need [Inno Setup](https://www.jrsoftware.org/isinfo.php) and [launch4j](https://launch4j.sourceforge.net/)

There were modifications
=======
## Usage
Import the projects into Netbeans or build with Gradle.

Run `./gradlew :player:jpackage` to create the player installer and `./gradlew :editor:jpackage` for the editor. Icons are taken from `_SETUP/Windows`.
Manual packaging scripts in `_SETUP` are deprecated and kept only for reference.

## Screenshots
![Screenshot](https://fdossena.com/sine/pc1.png)
![Screenshot](https://fdossena.com/sine/pc2.png)
![Screenshot](https://fdossena.com/sine/website1.png)

## License
See [LICENSE](LICENSE) for the full license text.
Copyright (C) 2014-2020 Federico Dossena

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
