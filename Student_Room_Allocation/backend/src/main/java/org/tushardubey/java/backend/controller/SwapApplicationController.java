package org.tushardubey.java.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tushardubey.java.backend.dto.SwapRequest;
import org.tushardubey.java.backend.dto.SwapResponse;
import org.tushardubey.java.backend.service.SwapApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/swap")
@RequiredArgsConstructor
public class SwapApplicationController {
    private final SwapApplicationService swapApplicationService;

    @PostMapping("/request")
    public ResponseEntity<SwapResponse> createSwapRequest(@RequestBody SwapRequest request) {
        return ResponseEntity.ok(swapApplicationService.createSwapRequest(request));
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<List<SwapResponse>> getRequestsForRecipient(@PathVariable Long recipientId) {
        return ResponseEntity.ok(swapApplicationService.getRequestsForRecipient(recipientId));
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(
            @PathVariable Long requestId,
            @RequestParam String recipientMessage
    ) {
        swapApplicationService.acceptRequest(requestId, recipientMessage);
        return ResponseEntity.ok("Request Accepted");
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(
            @PathVariable Long requestId,
            @RequestParam String recipientMessage
    ) {
        swapApplicationService.rejectRequest(requestId, recipientMessage);
        return ResponseEntity.ok("Request Rejected");
    }
}