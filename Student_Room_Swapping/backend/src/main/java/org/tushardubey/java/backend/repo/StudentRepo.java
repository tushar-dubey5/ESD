package org.tushardubey.java.backend.repo;
import org.tushardubey.java.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface StudentRepo  extends  JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

}
