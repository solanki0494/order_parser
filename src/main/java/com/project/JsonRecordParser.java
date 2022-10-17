package com.project;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonRecordParser implements RecordParser {

    public OrderResponse parse(String line) {
        boolean parsed = true;

        List<String> exceptions = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        final JsonObject obj = JsonParser.parseString(line).getAsJsonObject();
        Order order = new Order();

        try{
            order.setOrderId(obj.get("orderId").getAsInt());
        } catch (Exception ex){
            exceptions.add("Order Id is not in correct format");
            parsed = false;
        }

        try{
            order.setAmount(obj.get("amount").getAsDouble());
        } catch (Exception ex){
            exceptions.add("Amount is not in correct format");
            parsed = false;
        }

        order.setCurrency(obj.get("currency").getAsString());
        order.setComment(obj.get("comment").getAsString());

        if(parsed) return new OrderResponse(order, Result.OK, null);
        else return new OrderResponse(order, Result.ERROR, exceptions.stream().collect(Collectors.joining(",")));
    }
}
