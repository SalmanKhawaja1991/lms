package com.salman.serviceImpl;

import com.salman.dto.InstructorDTO;
import com.salman.entity.Instructor;
import com.salman.repository.CourseInstructorRepo;
import com.salman.repository.InstructorRepo;
import com.salman.service.InstructorService;
import com.salman.transformer.InstructorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private CourseInstructorRepo courseInstructorRepo;



    @Override
    public Map<Long, Integer> getNumberOfCoursesPerInstructor() {
        // Call the repository method to get the number of courses per instructor
        List<Object[]> results = courseInstructorRepo.countCoursesPerInstructor();

        // Process the results and map instructor IDs to course counts
        Map<Long, Integer> instructorCourseCount = new HashMap<>();
        for (Object[] result : results) {
            Long instructorId = (Long) result[0];
            Integer count = ((Number) result[1]).intValue();
            instructorCourseCount.put(instructorId, count);
        }

        return instructorCourseCount;
    }


    @Transactional
    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        if (instructorDTO == null) {
            throw new IllegalArgumentException("InstructorDTO must not be null");
        }
        Instructor instructor = InstructorTransformer.toEntity(instructorDTO);
        instructor = instructorRepo.save(instructor);
        return InstructorTransformer.toDTO(instructor);
    }

    @Transactional
    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
        if (instructorDTO == null) {
            throw new IllegalArgumentException("InstructorDTO must not be null");
        }
        if (instructorDTO.getInstructor_id() == null) {
            throw new IllegalArgumentException("Instructor ID must be provided for update");
        }
        Instructor instructor = InstructorTransformer.toEntity(instructorDTO);
        instructor = instructorRepo.save(instructor);
        return InstructorTransformer.toDTO(instructor);
    }

    @Transactional
    @Override
    public void deleteInstructor(Long instructorId) {
        if (instructorId == null) {
            throw new IllegalArgumentException("Instructor ID must be provided for deletion");
        }
        if (!instructorRepo.existsById(instructorId)) {
            throw new IllegalArgumentException("Instructor not found with ID: " + instructorId);
        }
        instructorRepo.deleteById(instructorId);
    }

    @Override
    public InstructorDTO getInstructorById(Long instructorId) {
        if (instructorId == null) {
            throw new IllegalArgumentException("Instructor ID must be provided");
        }
        Optional<Instructor> instructor = instructorRepo.findById(instructorId);
        return instructor.map(InstructorTransformer::toDTO).orElse(null);
    }

    @Override
    public long getNumberOfCoursesByInstructorId(Long instructorId) {
        return courseInstructorRepo.countCoursesByInstructorId(instructorId);
    }

    @Override
    public List<InstructorDTO> getAllInstructors() {
        return InstructorTransformer.toDTOList(instructorRepo.findAll());
    }
}
