# springbootffmpeg
Playing RTSP/mp4 etc with FFMPEG in SpringBoot Project


It enables image playback with FFMPEG using Jaffreee's Kokorin's library (https://github.com/kokorin/Jaffree) in the Spring Boot Framework. You can play live RTSP, recorded images and mp4 files.

It works with Java 11. For it to work, you need to install the current FFMPEG version on your computer and add it to the environment variables.

You can visit the website to download FFMPEG and for more detailed information. (https://www.ffmpeg.org/)

It offers 3 different endpoints;

1-) /video/live 

2-) /video/fastorslow

3-) /video/playmp4.

Although it basically performs the same operations, it uses different parameters when running FFMPEG.

There is an example html class. From here, you can play the image on the browser by typing the end-point of the feature you want to run.