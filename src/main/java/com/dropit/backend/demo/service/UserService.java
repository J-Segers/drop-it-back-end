package com.dropit.backend.demo.service;

import com.dropit.backend.demo.model.Authority;
import com.dropit.backend.demo.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    public String createUser(User user);
    public void updateUser(String username, User user);
    public void deleteUser(String username);
    public Collection<User> getUsers();
    public Optional<User> getUser(String username);
    public boolean userExists(String username);
    public Set<Authority> getAuthorities(String username);
    public void addAuthority(String username, String authority);
    public void removeAuthority(String username, String authority);

}