package com.centurion.controller;

import com.centurion.dto.CodeSubmissionRequest;
import com.centurion.dto.PistonResult;
import com.centurion.service.PistonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/code")
@CrossOrigin(origins = "http://localhost:3000")
public class CodeController {

    private final PistonService pistonService;

    public CodeController(PistonService pistonService) {
        this.pistonService = pistonService;
    }

    @PostMapping("/execute")
    public PistonResult execute(@RequestBody CodeSubmissionRequest request) {

        return pistonService.run(
                request.getLanguage(),
                request.getVersion(),
                request.getCode(),     // ✅ fixed
                request.getInput()     // ✅ fixed
        );
    }
}
