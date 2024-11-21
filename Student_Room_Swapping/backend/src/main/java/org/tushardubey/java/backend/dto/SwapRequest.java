package org.tushardubey.java.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SwapRequest {
    @NotNull
    private Long applicantId;

    @NotNull
    private Long recipientId;

    @NotBlank
    private String applicationMessage;
}
