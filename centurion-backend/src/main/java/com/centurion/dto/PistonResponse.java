package com.centurion.dto;

public class PistonResponse {

    private Run run;

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public static class Run {
        private String stdout;
        private String stderr;
        private int code;

        public String getStdout() {
            return stdout;
        }

        public void setStdout(String stdout) {
            this.stdout = stdout;
        }

        public String getStderr() {
            return stderr;
        }

        public void setStderr(String stderr) {
            this.stderr = stderr;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}