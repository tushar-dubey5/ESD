package org.tushardubey.java.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SwapResponse {
    private Long id;
    private String applicantName;
    private String recipientName;
    private String applicationMessage;
    private String status;
    private String recipientMessage;
}
