package org.tushardubey.java.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tushardubey.java.backend.entity.SwapApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface SwapApplicationRepo extends JpaRepository<SwapApplication, Long> {
    List<SwapApplication> findByRecipientId(Long recipientId);
    @Query("SELECT sa FROM SwapApplication sa WHERE sa.applicant.id = :applicantId")
    List<SwapApplication> findAllByApplicantId(@Param("applicantId") Long applicantId);

    List<SwapApplication> findByApplicantId(Long applicantId);
    Optional<SwapApplication> findByApplicantIdAndRecipientIdAndStatus(
            Long applicantId,
            Long recipientId,
            String status
    );
}
