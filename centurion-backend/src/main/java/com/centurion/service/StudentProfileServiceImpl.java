package com.centurion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.centurion.dto.StudentProfileRequest;
import com.centurion.model.StudentProfile;
import com.centurion.repository.StudentProfileRepository;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository repo;

    @Override
    public StudentProfile saveOrUpdate(StudentProfileRequest req) {

        StudentProfile profile =
                repo.findByEmail(req.email).orElse(new StudentProfile());

        profile.setEmail(req.email);
        profile.setName(req.name);
        profile.setUniversity(req.university);
        profile.setBio(req.bio);
        profile.setBranch(req.branch);
        profile.setYear(req.year);
        profile.setSection(req.section);
        profile.setRegNo(req.regNo);
        profile.setGender(req.gender);
        profile.setPhone(req.phone);

        return repo.save(profile);
    }

    @Override
    public StudentProfile getByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
