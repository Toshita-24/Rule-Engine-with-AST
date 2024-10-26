package com.example.RuleEnginerwithAST.service;

public class Node {
	

	    private String type;
	    private String value;
	    private Node left;
	    private Node right;

	    // Constructors, Getters, and Setters
	    public Node() {}

	    public Node(String type, String value, Node left, Node right) {
	        this.type = type;
	        this.value = value;
	        this.left = left;
	        this.right = right;
	    }

	    public String getType() {
	        return type;
	    }

	    public String getValue() {
	        return value;
	    }

	    public Node getLeft() {
	        return left;
	    }

	    public Node getRight() {
	        return right;
	    }

	    // Convert Node to JSON structure (using Jackson ObjectMapper if needed)
	}

