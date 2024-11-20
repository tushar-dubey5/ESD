package org.tushardubey.java.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hostel")
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false, unique = true)
    private Long roomNumber;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student; // Student occupying the room
}

