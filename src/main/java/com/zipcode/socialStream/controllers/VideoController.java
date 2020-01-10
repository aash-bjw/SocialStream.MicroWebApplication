package com.zipcode.socialStream.controllers;

import com.zipcode.socialStream.models.Video;
import com.zipcode.socialStream.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "\"herokuapp.com/\", \"http://socialstream-ui.herokuapp.com/\", \"http://socialstreamapp.herokuapp.com/\"")
@RequestMapping("")
public class VideoController {
    @Autowired
    private VideoService videoService;

        @RequestMapping("videos/all")
        public ResponseEntity<Iterable<Video>> index(){
            return new ResponseEntity<>(videoService.index(), HttpStatus.OK);
        }

        @GetMapping("/videos/{videoName}")
        public ResponseEntity<Video> show (@PathVariable String videoName){
            return new ResponseEntity<>(videoService.show(videoName), HttpStatus.OK);
        }

        @PostMapping("/videos")
        public ResponseEntity<Video> create(@Valid @RequestParam("file") MultipartFile file,
                                            @RequestParam("videoName") String videoName,
                                            @RequestParam("videoDescription") String videoDescription){
            return new ResponseEntity<>(videoService.create(file, videoName, videoDescription), HttpStatus.OK);
        }

        @PutMapping("/videos/{videoName}")
        public ResponseEntity<Video> update(@PathVariable String videoName, @Valid @RequestBody Video video){
            return new ResponseEntity<>(videoService.update(videoName, video), HttpStatus.OK);
        }

        @DeleteMapping("/videos/{videoId}")
        public ResponseEntity<Boolean> delete(@PathVariable Long videoId){
            return new ResponseEntity<>(videoService.delete(videoId), HttpStatus.OK);
        }
}
