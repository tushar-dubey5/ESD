package org.tushardubey.java.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SwapRequest {
    @NotBlank
    private Long applicantId;

    @NotBlank
    private Long recipientId;

    @NotBlank
    private String applicationMessage;
}
