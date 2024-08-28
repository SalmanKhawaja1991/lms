package com.salman.serviceImpl;

import com.salman.dto.ProfileDTO;
import com.salman.entity.Profile;
import com.salman.repository.ProfileRepo;
import com.salman.service.ProfileService;
import com.salman.transformer.ProfileTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile profile = ProfileTransformer.toEntity(profileDTO);
        profile = profileRepo.save(profile);
        return ProfileTransformer.toDTO(profile);
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) {
        Profile profile = ProfileTransformer.toEntity(profileDTO);
        profile = profileRepo.save(profile);
        return ProfileTransformer.toDTO(profile);
    }

    @Override
    public void deleteProfile(Long profileId) {
        profileRepo.deleteById(profileId);
    }

    @Override
    public ProfileDTO getProfileById(Long profileId) {
        return profileRepo.findById(profileId)
                .map(ProfileTransformer::toDTO)
                .orElse(null);
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        return ProfileTransformer.toDTOList(profileRepo.findAll());
    }
}
