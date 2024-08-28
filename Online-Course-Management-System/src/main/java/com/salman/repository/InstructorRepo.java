package com.salman.repository;

import com.salman.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepo extends JpaRepository<Instructor, Long> {
    // Add custom query methods if needed
    boolean existsById(Long ids);

}
