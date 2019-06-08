package com.kaadi.KadiBusinessCard.service;

import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.repository.RoleRepository;
import com.kaadi.KadiBusinessCard.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp(){
        initMocks(this);
        userServiceUnderTest=new UserService(mockUserRepository,
                                            mockRoleRepository,
                                            mockBCryptPasswordEncoder);
        user = User.builder()
                .id(1)
                .username("JanushMaj")
                .email("janush@maj.zyje")
                .build();
        Mockito.when(mockUserRepository.save(any())).thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
    }

    @Test
    public void testFindUserByEmail(){

        final String email= "janush@maj.zyje";
        final User result = userServiceUnderTest.findUserByEmail(email);
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser(){
        final String email="janush@maj.zyje";
        User result = userServiceUnderTest.saveUser(User.builder().build());
        assertEquals(email,result.getEmail());
    }

}
