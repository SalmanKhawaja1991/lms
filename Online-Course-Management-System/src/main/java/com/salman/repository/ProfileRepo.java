package com.salman.repository;

import com.salman.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
    // Add custom query methods if needed
}
