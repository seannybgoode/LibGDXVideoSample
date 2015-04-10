# libGDX-EvolveLiteMediaPlayerLibrary version 0.1.0

EvoLMPL - Written by Sean Brophy, courtesy of Evolve Interactive Inc. 


The Evolve Lite Media Player Library represents a lightweight media player library designed to 
permit playing media files from within a libGDX instance. Currently only supports desktop
playback. 

This project was forked from https://github.com/aaronsnoswell/LibGDXVideoSample

A tip of the cap to Aaron Snoswell for doing the basic work necessary to make this project possible.
We've taken this work and done some optimization and added audio support.

#0.1.0 changes:
-added support for audio track playback
-did some optimization around audio playback
-optimized video frame decoding
-added the 'Video Screen' class for easy video playback
-added a callback for the video frame texture to update when we have a new video frame
this was necessary to prevent choppy playback
-added the xuggler and slf4j libraries and their licenses
![Screenshot](screenshot.png "Screenshot")


## Controls

Touch / click to play / pause
Enter to stop video

