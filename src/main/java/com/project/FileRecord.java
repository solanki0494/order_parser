package com.project;

import com.google.gson.JsonObject;

public class FileRecord {
    private FileType fileType;
    private String filename;
    private int lineNumber;
    private String line;

    public FileRecord(FileType fileType, String filename, int lineNumber, String line) {
        this.fileType = fileType;
        this.filename = filename;
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public OrderResponse parse() {
        return fileType.parse(line);
    }

    @Override
    public String toString() {
        JsonObject json = new JsonObject();

        OrderResponse orderResponse = parse();
        json.addProperty("id", orderResponse.getOrder().getOrderId());
        json.addProperty("orderId", orderResponse.getOrder().getOrderId());
        json.addProperty("amount", orderResponse.getOrder().getAmount());
        json.addProperty("comment", orderResponse.getOrder().getComment());
        json.addProperty("filename", filename);
        json.addProperty("line", lineNumber);

        if(orderResponse.getResult() == Result.OK) json.addProperty("result", "OK");
        else json.addProperty("result", orderResponse.getParseException());
        return json.toString();
    }




}
