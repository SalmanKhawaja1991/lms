package com.salman.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "student_id")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    private String name;
    private String email;
    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Profile profile;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Enrollment> enrollments;




    // Method to calculate age
    public int getAge() {
        return LocalDate.now().getYear() - this.dateOfBirth.getYear();
    }
}
//    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY)
//   // @JsonManagedReference
//    private Profile profile;
//
//    @OneToMany(mappedBy = "student")
//    private List<Enrollment> enrollments;
// @JsonManagedReference

//    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @JsonIgnoreProperties("student")  // To avoid circular references during serialization
//    @JsonManagedReference
//    private Profile profile;

//    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Profile profile;

// @JsonManagedReference
// @JsonIgnoreProperties("student")

//@Builder
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "student_id")
//@Data