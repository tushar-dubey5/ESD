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

import java.util.Optional;
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

        Student applicant = studentRepo.findById(request.getApplicantId())
                .orElseThrow(() -> new RuntimeException("Applicant not found: " + request.getApplicantId()));

        Student recipient = studentRepo.findById(request.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Recipient not found: " + request.getRecipientId()));
        Hostel applicantHostel = hostelRepo.findByStudentId(applicant.getId())
                .orElseThrow(() -> new RuntimeException("Applicant's hostel not found"));
        Hostel recipientHostel = hostelRepo.findByStudentId(recipient.getId())
                .orElseThrow(() -> new RuntimeException("Recipient's hostel not found"));

        Optional<SwapApplication> existingRequest = swapApplicationRepo.findByApplicantIdAndRecipientIdAndStatus(
                request.getApplicantId(),
                request.getRecipientId(),
                "PENDING"
        );

        if (existingRequest.isPresent()) {
            throw new RuntimeException("Already created request");
        }
        SwapApplication swapApplication = SwapApplication.builder()
                .applicant(applicant)
                .recipient(recipient)
                .applicationMessage(request.getApplicationMessage())
                .status("PENDING")
                .applicantHostel(applicantHostel) // Set the applicant's hostel
                .recipientHostel(recipientHostel)
                .build();

        SwapApplication savedRequest = swapApplicationRepo.save(swapApplication);
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
        // Fetch the swap request
        SwapApplication request = swapApplicationRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getStatus().equals("PENDING")) {
            throw new RuntimeException("Request already processed");
        }

        request.setStatus("ACCEPTED");
        request.setRecipientMessage(recipientMessage);

        // Fetch hostels for applicant and recipient
        Hostel applicantHostel = hostelRepo.findByStudentId(request.getApplicant().getId())
                .orElseThrow(() -> new RuntimeException("Applicant's room not found"));
        Hostel recipientHostel = hostelRepo.findByStudentId(request.getRecipient().getId())
                .orElseThrow(() -> new RuntimeException("Recipient's room not found"));

        // Temporarily set students to null to avoid unique constraint violation
        applicantHostel.setStudent(null);
        recipientHostel.setStudent(null);
        hostelRepo.save(applicantHostel);
        hostelRepo.save(recipientHostel);

        // Perform the swap
        Student applicantStudent = request.getApplicant();
        Student recipientStudent = request.getRecipient();

        applicantHostel.setStudent(recipientStudent);
        recipientHostel.setStudent(applicantStudent);

        // Save the updated hostels
        hostelRepo.save(applicantHostel);
        hostelRepo.save(recipientHostel);

        // Save the updated swap application status
        swapApplicationRepo.save(request);
    }
    public List<SwapResponse> getRequestsForApplicant(Long applicantId) {
        return swapApplicationRepo.findByApplicantId(applicantId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
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
    public Long getRoomNumberByStudentId(Long studentId) {
        return hostelRepo.findByStudentId(studentId)
                .map(Hostel::getRoomNumber) // Extract room number if present
                .orElseThrow(() -> new RuntimeException("Room not found for student ID: " + studentId));
    }

    private SwapResponse mapToResponse(SwapApplication request) {
        Long applicantId = request.getApplicant().getId();
        Long applicantRoomNumber = getRoomNumberByStudentId(applicantId);
        System.out.println("Applicant: "+ applicantRoomNumber);
        return SwapResponse.builder()
                .id(request.getId())
                .applicantName(request.getApplicant().getFirstName() + " " + request.getApplicant().getLastName())
                .recipientName(request.getRecipient().getFirstName() + " " + request.getRecipient().getLastName())
                .applicationMessage(request.getApplicationMessage())
                .status(request.getStatus())
                .recipientMessage(request.getRecipientMessage())
                .roomNumber(applicantRoomNumber)
                .build();

    }
}
