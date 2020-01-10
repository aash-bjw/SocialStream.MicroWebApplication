package com.zipcode.socialStream.services;

import com.zipcode.socialStream.models.Video;
import com.zipcode.socialStream.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class VideoService {
    @Autowired
    private VideoRepository repository;
    private S3StorageService service;

    public String uploadToS3(MultipartFile multipartFile, Long id){
        String result = null;
        try{
            result = S3StorageService.upload(S3StorageService.convertToFile(multipartFile, id));
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public Iterable<Video> index(){
        return repository.findAll();
    }

    public Video show(String videoName){
        return repository.findVideoByVideoName(videoName);
    }

    public Video create(MultipartFile multipartFile, String videoName, String videoDescription)  {
        Video video = new Video(videoName,videoDescription,null);
        video = repository.save(video);
        File file = null;
        try{
            file = S3StorageService.convertToFile(multipartFile, video.getVideoId());
            String location = S3StorageService.upload(file);
            video.setLocation(location);
        }catch(Exception ex){
            System.out.println("Error Occurred in S3 Storage Service");
            ex.printStackTrace();
        }finally{
            if(file != null) file.delete();
        }
        return update(video.getVideoName(), video);

    }

    public Video update(String videoName, Video video){
        Video ogVideo = show(videoName);
        ogVideo.setVideoDescription(video.getVideoDescription());
        ogVideo.setVideoName(video.getVideoName());
        return repository.save(ogVideo);
    }

    public Boolean delete(Long videoId){
        repository.deleteById(videoId);
        return true;
    }
}
