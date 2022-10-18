package com.project;


import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ParserJavaApplication {

    private static final int QUEUE_SIZE = 100;
    private static BlockingQueue<FileRecord> queue;
    private static Collection<Thread> producerThreadCollection, allThreadCollection;


    public static void main(String[] args) {
        String csvFilename = args[0];
        String jsonFilename = args[1];

        producerThreadCollection = new ArrayList<>();
        allThreadCollection = new ArrayList<>();

        queue = new LinkedBlockingDeque<>(QUEUE_SIZE);

        createAndStartProducers(csvFilename, jsonFilename);
        createAndStartConsumers();

        for(Thread thread: allThreadCollection){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Parsing finished");

    }

    private static void createAndStartProducers(String csvFilename, String jsonFilename){
        Producer csvProducer = new Producer(csvFilename, FileType.CSV, queue);
        Thread csvProducerThread = new Thread(csvProducer,"producer-1");

        Producer jsonProducer = new Producer(jsonFilename, FileType.JSON, queue);
        Thread jsonProducerThread = new Thread(jsonProducer,"producer-2");

        producerThreadCollection.add(csvProducerThread);
        producerThreadCollection.add(jsonProducerThread);

        csvProducerThread.start();
        jsonProducerThread.start();

        allThreadCollection.addAll(producerThreadCollection);
    }

    private static void createAndStartConsumers(){
        for(int i=0; i<10; i++){
            Thread consumer = new Thread(new Consumer(queue), "consumer-"+i);
            allThreadCollection.add(consumer);
            consumer.start();
        }
    }

    public static boolean isProducerAlive(){
        for(Thread thread: producerThreadCollection){
            if(thread.isAlive())
                return true;
        }
        return false;
    }
}
