package com.salman.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "profile_id")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profile_id;

    private String biodata;
    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;
}
//    @OneToOne
//    @JoinColumn(name = "student_id")
//    @JsonBackReference
//    private Student student;

//    @OneToOne
//    @JoinColumn(name = "student_id")
//    //@JsonBackReference
//    @JsonIgnore
//    private Student student;


//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id")
////    @JsonIgnoreProperties("profile")  // To avoid circular references during serialization
//    @JsonBackReference
//    private Student student;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id")
//    @JsonBackReference
//    private Student student;

//@JsonIgnoreProperties("profile")
// @JsonBackReference
// @JsonIgnore
//@JsonIgnore

//@Data
//@Builder
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "profile_id")