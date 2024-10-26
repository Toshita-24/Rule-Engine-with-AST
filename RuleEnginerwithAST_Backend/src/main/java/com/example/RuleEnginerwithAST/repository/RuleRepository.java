package com.example.RuleEnginerwithAST.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RuleEnginerwithAST.model.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
}
