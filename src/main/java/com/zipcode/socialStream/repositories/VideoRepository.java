package com.zipcode.socialStream.repositories;

import com.zipcode.socialStream.models.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {

    Video findVideoByVideoName(String videoName);

}
