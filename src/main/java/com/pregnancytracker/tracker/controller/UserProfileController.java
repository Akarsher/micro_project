package com.pregnancytracker.tracker.controller;

import com.pregnancytracker.tracker.model.UserProfile;
import com.pregnancytracker.tracker.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    // Create a new user
    @PostMapping
    public UserProfile createUser(@RequestBody UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    // Get all users
    @GetMapping
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    // Get a single user by ID
    @GetMapping("/{id}")
    public UserProfile getUserById(@PathVariable String id) {
        return userProfileRepository.findById(id).orElse(null);
    }

    // Update a user
@PutMapping("/{id}")
public UserProfile updateUser(@PathVariable String id, @RequestBody UserProfile updatedUser) {
    return userProfileRepository.findById(id)
        .map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setDueDate(updatedUser.getDueDate());
            return userProfileRepository.save(user);
        })
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

}

    // Delete a user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userProfileRepository.deleteById(id);
        return "User deleted successfully.";
    }
}

