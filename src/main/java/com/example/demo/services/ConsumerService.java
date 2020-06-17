package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConsumerService {


    @Autowired
    private RestService serviceProviderOfRest;


    public void consume() {
        serviceProviderOfRest.getInst();
        Thread threads[] = new Thread[5];
        for (int i = 0 ; i < threads.length ; i++) {
             threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                            while (serviceProviderOfRest.getCoffeQueue().size() != 0) {
                                System.out.println(Thread.currentThread().getId() + " : " + serviceProviderOfRest.retriveItem());

                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                }
            });
            threads[i].start();
        }

        }
    }

