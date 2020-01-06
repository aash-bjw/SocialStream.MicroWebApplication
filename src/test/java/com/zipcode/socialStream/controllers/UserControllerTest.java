package com.zipcode.socialStream.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcode.socialStream.models.User;
import com.zipcode.socialStream.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    UserService mockService;

    private List<User> userList;
    private User user1, user2, user3, update;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();

        userList = new ArrayList<>();
        user1 = new User("mUser", "One", "Mock", "pass","mock@email");
        user2 = new User("User", "Two", "Mock", "pass", "m@email");
        user3 = new User("Mock", "Three", "Mock", "pass", "3@email");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        update = new User("mUser", "Mark", "Mock", "pass","mock@email");
    }

    @Test
    public void testAddUser() throws Exception {
        when(mockService.addUser(user1)).thenReturn(user1);

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user1)))
                .andExpect(status().isCreated());

        assertEquals(mockService.addUser(user1), user1);
        verify(mockService, times(1)).addUser(user1);
    }

    @Test
    public void testFindByUsername() throws Exception {

        when(mockService.findByUsername("User")).thenReturn(user2);

        mockMvc.perform(
                get("/users/{username}", user2.getUsername()))
                .andExpect(status().isOk());

        verify(mockService, times(1)).findByUsername("User");
    }

    @Test
    public void testFindAll() throws Exception {

        when(mockService.findAll()).thenReturn(userList);

        mockMvc.perform(get("/users"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(status().isOk());

        verify(mockService, times(1)).findAll();
        verifyNoMoreInteractions(mockService);
    }

    @Test
    public void testUpdateUser() throws Exception {

        when(mockService.updateUser(user1.getUsername(), update)).thenReturn(user1);

        mockMvc.perform(put("/users/{username}/", user1.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(update)))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteUser() throws Exception {
        when(mockService.deleteByUsername(user1.getUsername())).thenReturn(true);

        mockMvc.perform(delete("/users/")
                .param("username", user1.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));


        verify(mockService, times(1)).deleteByUsername("mUser");
        verifyNoMoreInteractions(mockService);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
