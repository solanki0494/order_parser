package com.project;

public enum FileType {
    CSV(new CsvRecordParser()){
        public OrderResponse parse(String line) {
            return parser.parse(line);
        }
    },
    JSON(new JsonRecordParser()){
        public OrderResponse parse(String line) {
            return parser.parse(line);
        }
    };

    RecordParser parser;
    FileType(RecordParser parser){
        this.parser = parser;
    }

    public abstract OrderResponse parse(String line);
}
