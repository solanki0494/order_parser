package com.project;

import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.commons.csv.CSVRecord;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<FileRecord> queue;

    public Consumer(BlockingQueue<FileRecord> queue){
        this.queue = queue;
    }

    public void run(){
        while(true){
            FileRecord fileRecord = queue.poll();
            if(fileRecord == null && !ParserJavaApplication.isProducerAlive()) return;
            if(fileRecord != null){
                System.out.println(fileRecord.toString());
            }

        }
    }
}
