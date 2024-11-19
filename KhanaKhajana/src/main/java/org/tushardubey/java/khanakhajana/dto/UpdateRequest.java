package org.tushardubey.java.khanakhajana.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateRequest(
        @NotBlank(message = "Address cannot be blank")
        String address,

        @NotBlank(message = "Gender cannot be blank")
        @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
        String gender,

        @NotBlank(message = "Phone number cannot be blank")
        @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
        String phoneNumber
) {}
