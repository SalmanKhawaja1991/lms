package com.salman.transformer;

import com.salman.dto.ProfileDTO;
import com.salman.entity.Profile;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileTransformer {

    public static ProfileDTO toDTO(Profile profile) {
        if (profile == null) return null;

        ProfileDTO dto = new ProfileDTO();
        dto.setProfile_id(String.valueOf(profile.getProfile_id()));
        dto.setBiodata(profile.getBiodata());

        // Avoid setting student to prevent recursion
        //dto.setStudent(null);

        return dto;
    }

    public static Profile toEntity(ProfileDTO dto) {
        if (dto == null) return null;

        Profile profile = new Profile();
        profile.setProfile_id(Long.parseLong(dto.getProfile_id()));
        profile.setBiodata(dto.getBiodata());

        // Optionally set student if required
        //profile.setStudent(StudentTransformer.toEntity(dto.getStudent()));

        return profile;
    }

    public static List<ProfileDTO> toDTOList(List<Profile> profiles) {
        if (profiles == null) return null;

        return profiles.stream()
                .map(ProfileTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Profile> toEntityList(List<ProfileDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(ProfileTransformer::toEntity)
                .collect(Collectors.toList());
    }
}
