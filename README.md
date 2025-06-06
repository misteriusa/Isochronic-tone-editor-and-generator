# SINE Isochronic Entrainer
SINE Isochronic Entrainer is a Free and Open Source Brainwave Entrainment application.

Brainwave Entrainment is the practice of using sounds, visual stimuli or even electromagnetic fields to alter the frequency of brainwaves.
It can be done for several reasons: people with insomnia can take great benefit from it, but it can also be used to improve your attention span, and even simulate the effects of some recreational drugs.
The effect is not permanent, and fades away in less than a minute after the stimuli is removed.

Isochronic tones are a commonly used aural stimuli for Brainwave Entrainment, consisting of short pulses of a sine wave, varying in frequency. Unlike binaural beats, isochronic tones can be played on speakers. 

This PC Version include an Editor to make your own sessions.
 
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
 
## Usage
Import the projects into Netbeans.

_SETUP contains all the files used to build the GNU/Linux packages, the installer for Windows and the Mac app packages.
To build the installer for Windows, you'll need [Inno Setup](https://www.jrsoftware.org/isinfo.php) and [launch4j](https://launch4j.sourceforge.net/)

## Building and testing
This project uses a small Gradle build defined in `build.gradle`. The Gradle wrapper JAR is omitted from version control to avoid storing binary files.
Run `gradle wrapper` to generate it locally or execute tasks directly with a system Gradle installation, e.g. `gradle test`.

## Screenshots
![Screenshot](https://fdossena.com/sine/pc1.png)
![Screenshot](https://fdossena.com/sine/pc2.png)
![Screenshot](https://fdossena.com/sine/website1.png)

## License
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
