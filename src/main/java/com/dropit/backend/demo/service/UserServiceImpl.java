package com.dropit.backend.demo.service;

import com.dropit.backend.demo.exception.RecordNotFoundException;
import com.dropit.backend.demo.exception.UsernameAlreadyExistsException;
import com.dropit.backend.demo.exception.UsernameNotFoundException;
import com.dropit.backend.demo.exception.emailTakenException;
import com.dropit.backend.demo.model.Authority;
import com.dropit.backend.demo.model.User;
import com.dropit.backend.demo.repository.UserRepository;
import com.dropit.backend.demo.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements com.dropit.backend.demo.service.UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private AuthorityRepository authorityRepository;

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    @Override
    public boolean userExists(String query) {
        boolean exists;

        if(query.contains("@")) {
            Optional<User> checkUser = userRepository.findUserByEmail(query);
            exists = checkUser.isPresent();
        } else {
            exists = userRepository.existsById(query);
        }

        return exists;
    }

//    @Override
//    public boolean usernameExists(String username) {
//        return userRepository.existsById(username);
//    }

    @Override
    public String createUser(User user) {

        String usernameCheck = user.getUsername();
        if(userExists(usernameCheck)) {
            throw new UsernameAlreadyExistsException("Username: " + usernameCheck + " has all ready been taken!");
        }
//        user.setUsername(usernameCheck);

        String emailCheck = user.getEmail();
        if(userExists(emailCheck)) {
            throw new emailTakenException("email: " + emailCheck + " has all ready been taken!");
        }
        user.setEmail(user.getEmail());

        String randomString = RandomStringGenerator.generateAlphaNumeric(20);

        user.setApikey(randomString);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.getAuthorities().clear();

        user.addAuthority(new Authority(user.getUsername(), "USER"));

        user.setId(getUsers().size() + 1);

        User newUser = userRepository.save(user);

        return newUser.getUsername();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public void updateUser(String username, User newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    @Override
    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        return user.getAuthorities();
    }

    @Override
    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    @Override
    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
    }

}
