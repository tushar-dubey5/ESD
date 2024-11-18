package org.tushardubey.java.khanakhajana.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be in the correct format")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Password is required")
        @JsonProperty("password")
        String password
) {}