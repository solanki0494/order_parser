package com.project;

public enum Result {
    OK("Ok"), ERROR("Error");

    private String description;

    Result(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
