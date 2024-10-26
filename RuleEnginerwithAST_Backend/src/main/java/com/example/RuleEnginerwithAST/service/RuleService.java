package com.example.RuleEnginerwithAST.service;



import com.example.RuleEnginerwithAST.model.Rule;
import com.example.RuleEnginerwithAST.repository.RuleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.json.*;
import java.util.List;
import java.util.Map;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Rule createRule(String ruleString, Node ast) throws Exception {
        String astJson = objectMapper.writeValueAsString(ast);
        Rule rule = new Rule(ruleString, astJson);
        return ruleRepository.save(rule);
    }

    public Rule combineRules(List<Long> ruleIds) throws Exception {
        List<Rule> rules = ruleRepository.findAllById(ruleIds);
        Node combinedAst = new Node(); // Combine AST nodes here
        String combinedRuleString = rules.stream()
            .map(Rule::getRuleString)
            .reduce((r1, r2) -> r1 + " AND " + r2)
            .orElse("");
        return createRule(combinedRuleString, combinedAst);
    }

    public boolean evaluateRule(Long ruleId, Map<String, Object> data) throws Exception {
        Rule rule = ruleRepository.findById(ruleId).orElseThrow();
        Node ast = objectMapper.readValue(rule.getAst(), Node.class);
        return evaluateAst(ast, data);
    }

    public boolean evaluateAst(Node ast, Map<String, Object> data) {
        if ("operator".equals(ast.getType())) {
            if ("AND".equals(ast.getValue())) {
                return evaluateAst(ast.getLeft(), data) && evaluateAst(ast.getRight(), data);
            } else if ("OR".equals(ast.getValue())) {
                return evaluateAst(ast.getLeft(), data) || evaluateAst(ast.getRight(), data);
            }
        } else if ("operand".equals(ast.getType())) {
            String[] parts = ast.getValue().split(" ");
            String left = parts[0];
            String operator = parts[1];
            String right = parts[2];
            Object leftValue = data.get(left);
            Object rightValue = right.matches("\\d+") ? Integer.parseInt(right) : right.replace("'", "");
            return evaluateOperand(leftValue, operator, rightValue);
        }
        return false;
    }

    private boolean evaluateOperand(Object leftValue, String operator, Object rightValue) {
        switch (operator) {
            case ">":
                return ((Comparable) leftValue).compareTo(rightValue) > 0;
            case "<":
                return ((Comparable) leftValue).compareTo(rightValue) < 0;
            case "=":
                return leftValue.equals(rightValue);
            default:
                return false;
        }
    }

    public Rule modifyRule(Long ruleId, String newRuleString, String newAst) throws Exception {
        Rule rule = ruleRepository.findById(ruleId).orElseThrow();
        rule.setRuleString(newRuleString);
        String astJson = objectMapper.writeValueAsString(newAst);
        rule.setAst(astJson);
        return ruleRepository.save(rule);
    }

	public boolean createRule(String ruleString, String ast) {
		// TODO Auto-generated method stub
		return false;
	}

	}
