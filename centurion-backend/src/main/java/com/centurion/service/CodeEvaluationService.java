package com.centurion.service;

import com.centurion.dto.CodeSubmissionRequest;
import com.centurion.dto.PistonResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CodeEvaluationService {

    private final PistonService pistonService;

    public CodeEvaluationService(PistonService pistonService) {
        this.pistonService = pistonService;
    }

    public Map<String, Object> evaluate(CodeSubmissionRequest req) {

        Map<String, Object> response = new HashMap<>();

        PistonResult result = pistonService.run(
                req.getLanguage(),
                req.getVersion(),
                req.getCode(),   // ✅ CORRECT
                req.getInput()
        );

        // ✅ COMPILATION ERROR
        if (result.getStderr() != null &&
                result.getStderr().toLowerCase().contains("error")) {

            response.put("status", "COMPILATION_ERROR");
            response.put("error", result.getStderr());
            return response;
        }

        // ✅ RUNTIME ERROR
        if (result.getExitCode() != 0) {
            response.put("status", "RUNTIME_ERROR");
            response.put("error", result.getStderr());
            return response;
        }

        // ✅ SUCCESS
        response.put("status", "SUCCESS");
        response.put("output", result.getStdout());
        response.put("time", result.getTime());

        return response;
    }
}
