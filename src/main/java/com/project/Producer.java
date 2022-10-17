package com.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private String filename;
    private FileType fileType;
    private BlockingQueue<FileRecord> queue;

    public Producer(String filename, FileType fileType, BlockingQueue<FileRecord> queue) {
        this.filename = filename;
        this.fileType = fileType;
        this.queue = queue;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                queue.offer(new FileRecord(fileType, filename, ++lineNumber, line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
