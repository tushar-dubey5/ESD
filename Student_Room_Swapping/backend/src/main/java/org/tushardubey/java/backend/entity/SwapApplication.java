package org.tushardubey.java.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "swap_application")
public class SwapApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private Student applicant; // The student requesting the swap

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id", nullable = false)
    private Student recipient; // The student receiving the request

    @Column(nullable = false)
    private String applicationMessage;

    @Column(nullable = false)
    private String status; // "PENDING", "ACCEPTED", "REJECTED"

    private String recipientMessage; // Message from recipient

    @ManyToOne
    @JoinColumn(name = "applicant_hostel_id")
    private Hostel applicantHostel;

    @ManyToOne
    @JoinColumn(name = "recipient_hostel_id")
    private Hostel recipientHostel;

}

