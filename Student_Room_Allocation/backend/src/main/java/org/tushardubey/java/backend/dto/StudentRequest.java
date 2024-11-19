package org.tushardubey.java.backend.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
public record StudentRequest(
        @NotNull(message = "Student should be present")
        @NotEmpty(message = "Student should be present")
        @NotBlank(message = "Student should be present")
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @NotNull(message = "Student email is required")
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotNull(message = "Password should be present")
        @NotEmpty(message = "Password should be present")
        @NotBlank(message = "Password should be present")
        @JsonProperty("password")
        String password


) {
}
