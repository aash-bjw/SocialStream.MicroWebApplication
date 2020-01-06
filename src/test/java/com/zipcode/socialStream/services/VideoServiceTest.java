package com.zipcode.socialStream.services;

import com.zipcode.socialStream.SocialStreamApplication;
import com.zipcode.socialStream.controllers.VideoController;
import com.zipcode.socialStream.models.Video;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SocialStreamApplication.class)
public class VideoServiceTest {

    @MockBean
    private VideoService service;
    private VideoController controller;

//    @Before
//    public void setup(){
//        this.controller = new VideoController(service);
//    }

    @Test
    public void testCreate(){
        //Given
    }

}