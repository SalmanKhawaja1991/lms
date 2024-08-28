package com.salman.service;

import com.salman.dto.ProfileDTO;
import java.util.List;

public interface ProfileService {

    ProfileDTO createProfile(ProfileDTO profileDTO);

    ProfileDTO updateProfile(ProfileDTO profileDTO);

    void deleteProfile(Long profileId);

    ProfileDTO getProfileById(Long profileId);

    List<ProfileDTO> getAllProfiles();
}
