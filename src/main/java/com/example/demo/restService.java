package com.example.demo;

//import com.techprimers.spring_boot_soap_example.Coffe;

import io.spring.guides.gs_producing_web_service.Coffe;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@org.springframework.stereotype.Service
public class restService {
    private BlockingQueue<Coffe> coffeQueue = new LinkedBlockingQueue<Coffe>();
    private static restService inst;

    private restService(){
    }

    public static restService getInst() {
        if (inst == null)
        {
            inst = new restService();
        }
        return inst;
    }

    public void addCoffe(Coffe coffe)
{
    coffeQueue.add(coffe);
}

public Coffe retriveItem(){
    synchronized (this.coffeQueue) {
        return coffeQueue.poll();
    }
}
public Coffe findCoffe(int id){

        for (int i = 0; i < coffeQueue.size(); i++)
        {
            if (  ((Coffe) coffeQueue.toArray()[i]).getId() == id )
            {
                return  (Coffe) coffeQueue.toArray()[i];
            }
        }
        return null;
}
    public BlockingQueue<Coffe> getCoffeQueue() {
        return coffeQueue;
    }
    public void setCoffeQueue(BlockingQueue<Coffe> coffeQueue) {
        this.coffeQueue = coffeQueue;
    }
}
