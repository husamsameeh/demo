package com.example.demo.services;

//import com.techprimers.spring_boot_soap_example.Coffe;

import com.example.Coffe;
import com.example.demo.data.objects.CoffeeDto;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



@org.springframework.stereotype.Service
public class RestService {
    private BlockingQueue<Coffe> coffeQueue = new LinkedBlockingQueue<Coffe>();
    private static RestService inst;
    private RestService(){
    }
    public static RestService getInst() {
        if (inst == null)
        {
            inst = new RestService();
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
