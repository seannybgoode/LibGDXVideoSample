# libGDX-EvolveLiteMediaPlayerLibrary version 0.1.5

EvoLMPL - Written by Sean Brophy
sjbrophy.eastvan@gmail.com

The Evolve Lite Media Player Library represents a lightweight media player library designed to 
permit playing media files from within a libGDX instance. It is unknown if this will work on Andriod/mobile
so if someone wanted to test that and get back to me, that'd be awesome. Currently taking feature requests.

This project was forked from https://github.com/aaronsnoswell/LibGDXVideoSample

A tip of the cap to Aaron Snoswell for doing the basic work necessary to make this project possible.
We've taken this work and done some optimization and added audio support.

#0.1.5 changes
- fixed a bug where the timestamp was being set by the last decoded audio frame, time is now kept by the total audio playtime which we get from the Java audio stream

- fixed the missing java class Node

- removed references to the proprietary GameManager class

- added executable test applications

- added missing jars to the git index, they were missing for some reason

- added pom.xml for maven dependency management (thanks Piotr Jastrzebski!)


#0.1.4 changes

- Fixed an issue where video playback could be far ahead of the audio

- Moved some code from the update/render thread into the decoding thread

- Optimized frame conversions to a texture(we were calling new unnecessarily in every draw call)

- Added a mechanism for having a frame wait until the audio is in sync

#0.1.3 changes

- Fixed the big stuttering occurring when a video starts playing

- Offloaded the video picture conversion to the packet handler thread
for a minor bump in framerate

- Added a bunch of documentation 

- Cleaned up a bunch of unused variables and cleaned up warnings from the code

- Added more functionality to the video screen class to increase
its usefullness

#0.1.2 changes

- major re-write of the playback algorithm

- added multithreaded decoding

- fixed the major video stutters and skips with the new approach

- added debug output to VideoScreen

- made the VideoScreen class more useful by adding a new constructor

#0.1.2 known issues

- pause does not work

- starting the video again after playback has finished does not work

#0.1.1 changes

- fixed an issue with video corruption during playback

- improved the issues with audio sync and added improved desync protection

#0.1.0 changes

- added support for audio track playback

- did some optimization around audio playback

- optimized video frame decoding

- added the 'Video Screen' class for easy video playback

- added a callback for the video frame texture to update when we have a new video frame
this was necessary to prevent choppy playback

- added the xuggler and slf4j libraries and their licenses

![Screenshot](screenshot.png "Screenshot")


## Controls

Touch / click to play / pause
Enter to stop video

