package com.kaadi.KadiBusinessCard.service;

import com.kaadi.KadiBusinessCard.model.Role;
import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.repository.RoleRepository;
import com.kaadi.KadiBusinessCard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findUserById(int id){
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setFacebook("https://www.facebook.com");
        user.setLinkedin("https://www.linkedin.com");
        Role userRole=roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User saveUpdatedUser(User user){
        User oldUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        oldUser.setUsername((user.getUsername()!= null
                && !(user.getUsername().equals(oldUser.getUsername()))) ? user.getUsername() : oldUser.getUsername());
        oldUser.setFacebook((user.getFacebook()!=null
                && !(user.getFacebook().equals(oldUser.getFacebook()))) ? user.getFacebook() : oldUser.getFacebook());
        oldUser.setLinkedin((user.getLinkedin()!=null
                && !(user.getLinkedin().equals(oldUser.getLinkedin()))) ? user.getLinkedin() : oldUser.getLinkedin());
        return userRepository.save(oldUser);
    }

}
