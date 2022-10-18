package com.project;

public enum Result {
    OK("OK"), ERROR("Error");

    private String description;

    Result(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
