package org.tushardubey.java.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tushardubey.java.backend.dto.SwapRequest;
import org.tushardubey.java.backend.dto.SwapResponse;
import org.tushardubey.java.backend.entity.Hostel;
import org.tushardubey.java.backend.entity.Student;
import org.tushardubey.java.backend.entity.SwapApplication;
import org.tushardubey.java.backend.repo.HostelRepo;
import org.tushardubey.java.backend.repo.StudentRepo;
import org.tushardubey.java.backend.repo.SwapApplicationRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SwapApplicationService {
    private final SwapApplicationRepo swapApplicationRepo;
    private final StudentRepo studentRepo;
    private final HostelRepo hostelRepo;

    public SwapResponse createSwapRequest(SwapRequest request) {
        System.out.println("Received SwapRequest: " + request);

        // Validate applicant existence
        Student applicant = studentRepo.findById(request.getApplicantId())
                .orElseThrow(() -> new RuntimeException("Applicant not found: " + request.getApplicantId()));

        // Validate recipient existence
        Student recipient = studentRepo.findById(request.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Recipient not found: " + request.getRecipientId()));

        // Build and save the swap request
        SwapApplication swapApplication = SwapApplication.builder()
                .applicant(applicant)
                .recipient(recipient)
                .applicationMessage(request.getApplicationMessage())
                .status("PENDING")
                .build();

        SwapApplication savedRequest = swapApplicationRepo.save(swapApplication);

        // Log the saved request
        System.out.println("Saved SwapApplication: " + savedRequest);

        return mapToResponse(savedRequest);
    }

    public List<SwapResponse> getRequestsForRecipient(Long recipientId) {
        return swapApplicationRepo.findByRecipientId(recipientId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public void acceptRequest(Long requestId, String recipientMessage) {
        SwapApplication request = swapApplicationRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getStatus().equals("PENDING")) {
            throw new RuntimeException("Request already processed");
        }

        request.setStatus("ACCEPTED");
        request.setRecipientMessage(recipientMessage);

        // Swap the rooms in the Hostel table
        Hostel applicantHostel = hostelRepo.findByRoomNumber(request.getApplicant().getId())
                .orElseThrow(() -> new RuntimeException("Applicant's room not found"));

        Hostel recipientHostel = hostelRepo.findByRoomNumber(request.getRecipient().getId())
                .orElseThrow(() -> new RuntimeException("Recipient's room not found"));

        Student tempStudent = applicantHostel.getStudent();
        applicantHostel.setStudent(recipientHostel.getStudent());
        recipientHostel.setStudent(tempStudent);

        hostelRepo.save(applicantHostel);
        hostelRepo.save(recipientHostel);

        swapApplicationRepo.save(request);
    }

    public void rejectRequest(Long requestId, String recipientMessage) {
        SwapApplication request = swapApplicationRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getStatus().equals("PENDING")) {
            throw new RuntimeException("Request already processed");
        }

        request.setStatus("REJECTED");
        request.setRecipientMessage(recipientMessage);

        swapApplicationRepo.save(request);
    }

    private SwapResponse mapToResponse(SwapApplication request) {
        return SwapResponse.builder()
                .id(request.getId())
                .applicantName(request.getApplicant().getFirstName() + " " + request.getApplicant().getLastName())
                .recipientName(request.getRecipient().getFirstName() + " " + request.getRecipient().getLastName())
                .applicationMessage(request.getApplicationMessage())
                .status(request.getStatus())
                .recipientMessage(request.getRecipientMessage())
                .build();
    }
}
