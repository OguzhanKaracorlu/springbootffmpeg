package com.oguzhan.karacorlu.springbootffmpeg.service;

import com.github.kokorin.jaffree.LogLevel;
import com.github.kokorin.jaffree.StreamType;
import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.PipeOutput;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * @author : oguzhan.karacorlu
 * @project : springbootffmpeg
 * @created : 20.10.2023
 */
@Service
@Log4j2
public class FfmpegService {


    /**
     * Play Live RTSP function.
     * @param rtspUrl
     * @return
     */
    public ResponseEntity<StreamingResponseBody> playRTSP(String rtspUrl) {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(os -> {
                    FFmpeg.atPath()
                            .addArgument("-re")
                            .addArguments("-rtsp_transport", "tcp")
                            .addArguments("-i", rtspUrl)
                            .addArguments("-vcodec", "copy")
                            .setLogLevel(LogLevel.DEBUG)
                            .addOutput(PipeOutput.pumpTo(os)
                                    .disableStream(StreamType.AUDIO)
                                    .disableStream(StreamType.SUBTITLE)
                                    .disableStream(StreamType.DATA)
                                    .setFormat("ismv"))
                            .addArgument("-nostdin")
                            .execute();
                });
    }


    /**
     * Speed or slow play Record RTSP.
     * @param rtsp
     * @param speedValue
     * @return
     */
    public ResponseEntity<StreamingResponseBody> playSpeedOrSlowRTSP(String rtsp, Double speedValue) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(os -> {
                    FFmpeg.atPath()
                            .addArguments("-rtsp_transport", "tcp")
                            .addArguments("-i", rtsp)
                            .addArguments("-r", "16")
                            .addArguments("-vf", "setpts=" + speedValue + "*PTS")
                            .addArguments("-bufsize", "85M")
                            .addArguments("-qp", "20")
                            .addArguments("-tune", "fastdecode")
                            .setLogLevel(LogLevel.DEBUG)
                            .addOutput(PipeOutput.pumpTo(os)
                                    .disableStream(StreamType.AUDIO)
                                    .disableStream(StreamType.SUBTITLE)
                                    .disableStream(StreamType.DATA)
                                    .setFormat("ismv"))
                            .addArgument("-nostdin")
                            .execute();
                });
    }

    /**
     * Play mp4 file.
     * @return
     */
    public ResponseEntity<StreamingResponseBody> playMP4() {
        String resourceFileName = "example_video.mp4";
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL resourceUrl = classLoader.getResource(resourceFileName);
        String resourcePath = resourceUrl.getPath();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(os -> {
                    FFmpeg.atPath()
                            .addArgument("-re")
                            .addArguments("-rtsp_transport", "tcp")
                            .addArguments("-i", resourcePath)
                            .addArguments("-vcodec", "copy")
                            .setLogLevel(LogLevel.DEBUG)
                            .addOutput(PipeOutput.pumpTo(os)
                                    .disableStream(StreamType.AUDIO)
                                    .disableStream(StreamType.SUBTITLE)
                                    .disableStream(StreamType.DATA)
                                    .setFormat("ismv"))
                            .addArgument("-nostdin")
                            .execute();
                });
    }

}
