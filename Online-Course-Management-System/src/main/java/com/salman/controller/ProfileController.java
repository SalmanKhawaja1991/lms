package com.salman.controller;

import com.salman.dto.ApiResponseMessage;
import com.salman.dto.ProfileDTO;
import com.salman.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<ApiResponseMessage> createProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO createdProfile = profileService.createProfile(profileDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Profile created successfully")
                .status(HttpStatus.CREATED)
                .success(true)
                .data(createdProfile)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> updateProfile(@PathVariable("id") String id, @RequestBody ProfileDTO profileDTO) {
        profileDTO.setProfile_id(id); // Ensure the ID is set in the DTO
        ProfileDTO updatedProfile = profileService.updateProfile(profileDTO);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Profile updated successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(updatedProfile)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteProfile(@PathVariable("id") Long id) {
        profileService.deleteProfile(id);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Profile deleted successfully")
                .status(HttpStatus.NO_CONTENT)
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> getProfileById(@PathVariable("id") Long id) {
        ProfileDTO profileDTO = profileService.getProfileById(id);
        if (profileDTO != null) {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Profile retrieved successfully")
                    .status(HttpStatus.OK)
                    .success(true)
                    .data(profileDTO)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseMessage response = ApiResponseMessage.builder()
                    .message("Profile not found")
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseMessage> getAllProfiles() {
        List<ProfileDTO> profiles = profileService.getAllProfiles();
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Profiles retrieved successfully")
                .status(HttpStatus.OK)
                .success(true)
                .data(profiles)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
