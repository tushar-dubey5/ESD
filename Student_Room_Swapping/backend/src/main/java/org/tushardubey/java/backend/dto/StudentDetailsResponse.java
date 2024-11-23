package org.tushardubey.java.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDetailsResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long roomNumber;
    private String hostelName;
    private int floor;
}
