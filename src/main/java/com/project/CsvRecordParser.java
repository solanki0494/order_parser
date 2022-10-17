package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvRecordParser implements RecordParser {

    public OrderResponse parse(String line) {
        boolean parsed = true;

        List<String> exceptions = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        String[] values = line.split(",");

        Order order = new Order();

        try{
            order.setOrderId(Integer.parseInt(values[0]));
        } catch (Exception ex){
            exceptions.add("Order Id is not in correct format");
            parsed = false;
        }

        try{
            order.setAmount(Double.parseDouble(values[1]));
        } catch (Exception ex){
            exceptions.add("Amount is not in correct format");
            parsed = false;
        }

        order.setCurrency(values[2]);
        order.setComment(values[3]);

        if(parsed) return new OrderResponse(order, Result.OK, null);
        else return new OrderResponse(order, Result.ERROR, exceptions.stream().collect(Collectors.joining(",")));

    }
}
