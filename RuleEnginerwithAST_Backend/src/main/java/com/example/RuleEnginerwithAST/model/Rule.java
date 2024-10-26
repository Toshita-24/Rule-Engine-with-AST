package com.example.RuleEnginerwithAST.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleString;

    @Lob
    @Column(nullable = false)
    private String ast;

    // Constructors, Getters, and Setters
    public Rule() {}

    public Rule(String ruleString, String ast) {
        this.ruleString = ruleString;
        this.ast = ast;
    }

    public Long getId() {
        return id;
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }
}

