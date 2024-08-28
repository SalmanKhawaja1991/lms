package com.salman.service;

import com.salman.dto.InstructorDTO;
import java.util.List;
import java.util.Map;

public interface InstructorService {

    InstructorDTO createInstructor(InstructorDTO instructorDTO);

    InstructorDTO updateInstructor(InstructorDTO instructorDTO);

    void deleteInstructor(Long instructorId);

    InstructorDTO getInstructorById(Long instructorId);

    List<InstructorDTO> getAllInstructors();

    Map<Long, Integer> getNumberOfCoursesPerInstructor();

    long getNumberOfCoursesByInstructorId(Long instructorId);
}
