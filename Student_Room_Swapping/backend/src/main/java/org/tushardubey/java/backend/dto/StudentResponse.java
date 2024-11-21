package org.tushardubey.java.backend.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record StudentResponse(
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @JsonProperty("email")
        String email
) {
}
