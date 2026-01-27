package com.centurion.service;

import com.centurion.dto.StudentProfileRequest;
import com.centurion.model.StudentProfile;

public interface StudentProfileService {
    StudentProfile saveOrUpdate(StudentProfileRequest request);
    StudentProfile getByEmail(String email);
}
