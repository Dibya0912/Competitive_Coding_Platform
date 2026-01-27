package com.centurion.model;

import jakarta.persistence.*;

@Entity
public class Testcase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String input;

    @Column(length = 2000)
    private String expectedOutput;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public String getInput() {
        return input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }
}
