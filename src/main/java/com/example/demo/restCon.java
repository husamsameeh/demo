package com.example.demo;

import io.spring.guides.gs_producing_web_service.Coffe;
import io.spring.guides.gs_producing_web_service.GetidResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.BlockingQueue;

@RestController
public class restCon {
    @Autowired

    CoffeClient client ;
    private DataBase db;
    @Autowired
    private restService ss = restService.getInst();

    @Autowired
    private ConsumerService consumerService;


    @RequestMapping("/getById")
    public String getbyID(@RequestParam int id)
    {

        GetidResponse response = client.getByid(id);
       try {
           return "Coffe type : " + response.getCoffe().getType();
       }
       catch (NullPointerException e)
       {
           return "the coffe you are looking for was not found  :( ";
       }
    }

    @RequestMapping("/display")
    public BlockingQueue<Coffe> getallCoffees(){

        return ss.getCoffeQueue();
    }

    @RequestMapping("/getDB")
    public String GetFromDb(@RequestParam int id){
        db = DataBase.getInstance();
        try {


            return db.getItem(id);
        }
        catch (NullPointerException e)
        {
            return "item was not found in the database";
        }
    }
    @PostMapping("/addDB")
    public void addIntoDB(@RequestBody Coffe coffe){
        db = DataBase.getInstance();
        db.addItem(coffe);
    }

    @GetMapping("/displayDB")
    private String displayDB(){
        db = DataBase.getInstance();
        return db.display().toString();
    }

    @GetMapping("/DeleteFromDB")
    private void deleteDB(@RequestParam int id)
    {
        db = DataBase.getInstance();
        db.deleteItem(id);
    }

    @RequestMapping(method = RequestMethod.POST , value = "add")
    public void addCoffe(@RequestBody Coffe coffe){
        ss.addCoffe(coffe);
    }

    @RequestMapping("/consume")
    public void StartConsumers(){
            consumerService.consume();

    }

}
