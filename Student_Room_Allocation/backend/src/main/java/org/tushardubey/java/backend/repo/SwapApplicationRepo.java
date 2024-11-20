package org.tushardubey.java.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tushardubey.java.backend.entity.SwapApplication;

import java.util.List;

public interface SwapApplicationRepo extends JpaRepository<SwapApplication, Long> {
    List<SwapApplication> findByRecipientId(Long recipientId);

    List<SwapApplication> findByApplicantId(Long applicantId);
}
