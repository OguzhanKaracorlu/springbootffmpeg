package com.oguzhan.karacorlu.springbootffmpeg.controller;

import com.oguzhan.karacorlu.springbootffmpeg.service.FfmpegService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * @author : oguzhan.karacorlu
 * @project : springbootffmpeg
 * @created : 20.10.2023
 */

@RestController
@AllArgsConstructor
@RequestMapping("/video")
public class VideoController {

    private final FfmpegService ffmpegService;


    @GetMapping(value = "/live")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> livestream(@PathVariable String rtspUrl) {
        return ffmpegService.playRTSP(rtspUrl);
    }

    @GetMapping(value = "/fastorslow")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> fastOrSlowPlayback(@PathVariable String rtspUrl,@PathVariable Double speedValue) {
        return ffmpegService.playSpeedOrSlowRTSP(rtspUrl,speedValue);
    }

    @GetMapping(value = "/playmp4")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> playbackHCP() {
        return ffmpegService.playMP4();
    }


}
