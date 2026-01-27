package com.centurion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.centurion.dto.StudentProfileRequest;
import com.centurion.model.StudentProfile;
import com.centurion.service.StudentProfileService;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class StudentProfileController {

    @Autowired
    private StudentProfileService service;

    @PostMapping
    public StudentProfile save(@RequestBody StudentProfileRequest request) {
        return service.saveOrUpdate(request);
    }

    @GetMapping("/{email}")
    public StudentProfile get(@PathVariable String email) {
        return service.getByEmail(email);
    }
}
