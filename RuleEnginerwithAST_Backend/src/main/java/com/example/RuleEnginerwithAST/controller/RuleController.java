package com.example.RuleEnginerwithAST.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RuleEnginerwithAST.model.Rule;
import com.example.RuleEnginerwithAST.service.RuleService;

@RestController
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;
    
    
   @PostMapping("/create")
    public boolean createRule(@RequestBody Map<String, Object> request) throws Exception {
       String ruleString = (String) request.get("ruleString");
       String ast = "..."; // Convert the request data into Node (AST)
       return ruleService.createRule(ruleString, ast);
    }

    @PostMapping("/combine")
    public Rule combineRules(@RequestBody Map<String, Object> request) throws Exception {
        @SuppressWarnings("unchecked")
		List<Long> ruleIds = (List<Long>) request.get("ruleIds");
        return ruleService.combineRules(ruleIds);
    }

    @PostMapping("/evaluate")
    public Map<String, Boolean> evaluateRule(@RequestBody Map<String, Object> request) throws Exception {
        Long ruleId = ((Number) request.get("ruleId")).longValue();
        @SuppressWarnings("unchecked")
		Map<String, Object> data = (Map<String, Object>) request.get("data");
        boolean result = ruleService.evaluateRule(ruleId, data);
        return Map.of("result", result);
    }

  }
