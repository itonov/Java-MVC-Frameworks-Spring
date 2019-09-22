package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.User;

public interface UserService {
    void addUser(User userToAdd);

    User findByUsername(String username);
}
