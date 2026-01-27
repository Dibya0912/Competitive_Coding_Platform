package com.centurion.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String description;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Testcase> testcases;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Testcase> getTestcases() {
        return testcases;
    }
}
