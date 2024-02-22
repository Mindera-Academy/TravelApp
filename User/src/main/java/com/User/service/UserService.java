package com.User.service;

import com.User.execption.*;
import com.User.model.User;
import com.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOne(Integer id) {
        Optional<User> user = userRepository.findById(id);
        validateUserNotFound(user, id, " not found");
        return user.get();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User addOne(User user) {
        validateUsernameUnique(user.getUsername());
        validateConfirmPassword(user.getPassword(), user.getConfirmPassword());
        return userRepository.save(user);
    }

    public User update(Integer id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        validateUserNotFound(existingUser, id, " not found!");
        user.setId(id);
        validateConfirmPassword(user.getPassword(), user.getConfirmPassword());
        return userRepository.save(user);
    }

    public void remove(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
    }

    private void validateUserNotFound(Optional<User> userRepository, Integer id, String x) {
        if (userRepository.isEmpty()) {
            throw new UserNotFoundException("User " + id + x);
        }
    }

    private void validateUsernameUnique(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (!users.isEmpty()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
    }

    private void validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Password and confirm password do not match!");
        }
    }

    public String getConfirmPassword(Integer id) {
        Optional<User> user = userRepository.findById(id);
        validateUserNotFound(user, id, " not found!");
        return user.get().getConfirmPassword();
    }

}