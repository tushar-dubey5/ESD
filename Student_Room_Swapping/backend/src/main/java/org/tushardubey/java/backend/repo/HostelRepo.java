package org.tushardubey.java.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tushardubey.java.backend.entity.Hostel;

import java.util.Optional;

public interface HostelRepo extends JpaRepository<Hostel, Long> {
    Optional<Hostel> findByStudentId(Long studentId);
}
