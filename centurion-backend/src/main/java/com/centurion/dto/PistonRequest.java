package com.centurion.dto;

import java.util.List;

public class PistonRequest {

    private String language;
    private String version;
    private List<FileData> files;
    private String stdin;

    public static class FileData {
        private String name;
        private String content;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public List<FileData> getFiles() { return files; }
    public void setFiles(List<FileData> files) { this.files = files; }

    public String getStdin() { return stdin; }
    public void setStdin(String stdin) { this.stdin = stdin; }
}
