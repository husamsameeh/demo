package com.example.demo.controllers;

import com.example.demo.clients.CoffeClient;
import com.example.demo.Constants;
import com.example.demo.data.layers.DataAccess;
import com.example.demo.data.objects.CoffeeDao;
import com.example.demo.data.objects.CoffeeDto;
import com.example.demo.data.storage.DataBase;
import com.example.demo.services.ConsumerService;
import com.example.demo.services.RestService;
import com.example.Coffe;
import com.example.GetidResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.concurrent.BlockingQueue;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    CoffeClient client;

    DataAccess dataAccess = new DataAccess();

    private DataBase db;

    @Autowired
    private RestService restservice = RestService.getInst();

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("/getById")
    public String getbyID(@RequestParam int id) {
        System.out.println(client);

        GetidResponse response = client.getByid(id);
        try {
            return response.getCoffe().getType();
        } catch (NullPointerException e) {
            return Constants.COFFE_NOT_FOUND;
        }
    }

    @RequestMapping("/display")
    public BlockingQueue<Coffe> getallCoffees() {
        return restservice.getCoffeQueue();
    }


    @PostMapping("/addDB")
    public String addIntoDB(@RequestBody CoffeeDto coffe) throws ParseException {

        return dataAccess.addIntoDB(coffe);
    }

    @GetMapping("/selectFromDb")
    public CoffeeDto getFromDbById(@RequestParam int id)
    {
        System.out.println(id);
        return dataAccess.getFromDB(id);
    }


    @GetMapping("/displayDB")
    private String displayDB() {
        return dataAccess.displayDB();
    }

    @GetMapping("/DeleteFromDB")
    private String deleteDB(@RequestParam int id) {
        return dataAccess.deleteDB(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addCoffe(@RequestBody Coffe coffe) {
        restservice.addCoffe(coffe);
    }

    @RequestMapping("/consume")
    public void StartConsumers() {
        consumerService.consume();
    }
}
