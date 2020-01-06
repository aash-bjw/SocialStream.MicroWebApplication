package com.zipcode.socialStream.services;

import com.zipcode.socialStream.models.User;
import com.zipcode.socialStream.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository mockRepository;

    private List<User> userList;
    private User user1, user2, user3;

    @Before
    public void setUp() {
        userList = new ArrayList<>();
        user1 = new User("mUser", "One", "Mock", "pass","mock@email");
        user2 = new User("User", "Two", "Mock", "pass", "m@email");
        user3 = new User("Mock", "Three", "Mock", "pass", "3@email");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }

    @Test
    public void testAddUser() throws Exception {
        when(mockRepository.save(user1)).thenReturn(user1);

        assertEquals(service.addUser(user1), user1);

        verify(mockRepository, times(1)).save(user1);
    }

    @Test
    public void testAddUser2() throws Exception {
        doReturn(user1).when(mockRepository).save(any(User.class));

        User actual = service.addUser(user1);

        assertEquals(user1, actual);
    }

    @Test
    public void testFindUserByUsername(){
        when(mockRepository.findByUsername("User")).thenReturn(user2);
        assertEquals(service.findByUsername("User"), user2);

        verify(mockRepository, times(1)).findByUsername("User");
    }

    @Test
    public void testfindAll() {
        when(mockRepository.findAll()).thenReturn(userList);

        assertEquals(service.findAll(), userList);

        verify(mockRepository, times(1)).findAll();
    }

    @Test
    public void testUpdate(){
        User update = new User("mUser", "Mark", "Mock", "pass","mock@email");

        when(mockRepository.findByUsername("mUser")).thenReturn(user1);

        service.updateUser("mUser", update);
        String actual = user1.getFirstName();

        assertEquals(actual,"Mark");
    }

    @Test
    public void testDelete() {
        when(mockRepository.findByUsername("User")).thenReturn(user2);

        assertThat(service.deleteByUsername("User"), is(true));

        verify(mockRepository, times(1)).findByUsername("User");
        verify(mockRepository, times(1)).delete(user2);
    }

}
