package com.centurion.service;

import com.centurion.dto.PistonResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PistonService {

    private static final String PISTON_URL =
            "https://emkc.org/api/v2/piston/execute";

    private final RestTemplate restTemplate = new RestTemplate();

    public PistonResult run(
            String language,
            String version,
            String code,
            String input
    ) {

        // ===== REQUEST PAYLOAD =====
        Map<String, Object> payload = new HashMap<>();
        payload.put("language", language);

        if (version != null && !version.isBlank()) {
            payload.put("version", version);
        }

        Map<String, String> file = new HashMap<>();
        file.put("name", getFileName(language));
        file.put("content", code);

        payload.put("files", List.of(file));
        payload.put("stdin", input == null ? "" : input);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(payload, headers);

        Map<String, Object> response =
                restTemplate.postForObject(PISTON_URL, entity, Map.class);

        PistonResult result = new PistonResult();

        if (response == null) {
            result.setStderr("No response from Piston API");
            result.setExitCode(1);
            return result;
        }

        // ===== COMPILATION ERROR =====
        if (response.containsKey("compile") && response.get("compile") != null) {
            Map<?, ?> compile = (Map<?, ?>) response.get("compile");

            result.setStdout("");
            result.setStderr(
                    compile.get("stderr") != null
                            ? compile.get("stderr").toString()
                            : "Compilation error"
            );
            result.setExitCode(1);
            result.setTime(0);
            return result;
        }

        // ===== RUN RESULT =====
        Map<?, ?> run = (Map<?, ?>) response.get("run");

        if (run == null) {
            result.setStderr("Runtime execution failed");
            result.setExitCode(1);
            return result;
        }

        result.setStdout(run.get("stdout") != null ? run.get("stdout").toString() : "");
        result.setStderr(run.get("stderr") != null ? run.get("stderr").toString() : "");
        result.setExitCode(
                run.get("code") != null
                        ? Integer.parseInt(run.get("code").toString())
                        : 0
        );
        result.setTime(
                run.get("time") != null
                        ? Double.parseDouble(run.get("time").toString())
                        : 0
        );

        return result;
    }

    private String getFileName(String language) {
        return switch (language) {
            case "java" -> "Main.java";
            case "python" -> "main.py";
            case "c" -> "main.c";
            default -> "main.txt";
        };
    }
}
