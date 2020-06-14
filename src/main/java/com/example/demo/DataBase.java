package com.example.demo;

import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
//import com.techprimers.spring_boot_soap_example.Coffe;
import io.spring.guides.gs_producing_web_service.Coffe;
import org.luaj.vm2.ast.Str;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataBase {
    private static DataBase db_instance = null;
    private AerospikeClient client;
    private Key key;

    private DataBase(){
        client = new AerospikeClient("172.28.128.4", 3000);
        key = new Key("test", "coffee", "putgetkey");
    }

    public static DataBase getInstance()
    {
        if (db_instance == null)
            db_instance = new DataBase();

        return db_instance;
    }

    public String getItem(int id) {
        key = new Key("test", "coffee", "key"+id);
        Record record = client.get(null,key);

        return record.bins.get("type").toString();
    }

    public void addItem(Coffe coffe) {
        key = new Key("test", "coffee", "key"+coffe.getId());
        Bin bin1 = new Bin("id",coffe.getId());
        Bin bin2 = new Bin("type",coffe.getType());
        client.put(new WritePolicy(),key,bin1,bin2);
    }

    public void deleteItem(int id)
    {
        key = new Key("test", "coffee", "key"+id);
        client.delete(null,key);
    }

    public List<String> display() {
        Statement query = new Statement();
        query.setNamespace("test");
        query.setSetName("coffee");
        RecordSet rs = client.query(null,query);

        List<String> slist = new ArrayList<>();
        while(rs.next()){
            slist.add(rs.getRecord().bins.get("id").toString() + " : " +rs.getRecord().bins.get("type").toString() );
        }

        return slist;

    }
}
