package com.project;

public class OrderResponse {
    private Order order;
    private Result result;
    private String parseException;

    public OrderResponse(Order order, Result result, String parseException) {
        this.order = order;
        this.result = result;
        this.parseException = parseException;
    }

    public Order getOrder() {
        return order;
    }

    public Result getResult() {
        return result;
    }

    public String getParseException() {
        return parseException;
    }
}
