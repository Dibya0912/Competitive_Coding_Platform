package com.centurion.repository;

import com.centurion.model.Testcase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestcaseRepository extends JpaRepository<Testcase, Long> {

    List<Testcase> findByProblemId(Long problemId);
}
