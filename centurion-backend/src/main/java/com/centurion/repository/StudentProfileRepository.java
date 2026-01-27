package com.centurion.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.centurion.model.StudentProfile;

public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {

    Optional<StudentProfile> findByEmail(String email);
}
