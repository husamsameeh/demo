package com.example.demo.data.storage;
import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.example.demo.Constants;
import com.example.Coffe;
import com.example.demo.data.objects.CoffeeDao;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase dataBaseInstance = null;

    private AerospikeClient client;
    private Key key;

    private DataBase(){
        client = new AerospikeClient(Constants.IP_ADDRESS, Constants.PORT);
        key = new Key(Constants.DATA_BASE_NAME_SPACE, Constants.DATA_BASE_SET_NAME, Constants.DATA_BASE_KEY);
    }

    public static DataBase getInstance()
    {
        if (dataBaseInstance == null)
            dataBaseInstance = new DataBase();

        return dataBaseInstance;
    }

    public CoffeeDao getItem(int id) {
        key = new Key(Constants.DATA_BASE_NAME_SPACE, Constants.DATA_BASE_SET_NAME, Constants.DATA_BASE_KEY+id);

        if (!client.exists(null,key))
        {
            return null;
        }

        Record record = client.get(null,key);
        CoffeeDao coffeeDao = new CoffeeDao(record.bins.get("type").toString(),Integer.parseInt(record.bins.get("id").toString()));
        return coffeeDao;
    }

    public String addItem(CoffeeDao coffe) {
        key = new Key(Constants.DATA_BASE_NAME_SPACE, Constants.DATA_BASE_SET_NAME, Constants.DATA_BASE_KEY+coffe.getId());
        Bin bin1 = new Bin(Constants.BIN_TWO_ID,coffe.getId());
        Bin bin2 = new Bin(Constants.BIN_ONE_TYPE,coffe.getType());

        if (client.exists(null,key))
        {
            return Constants.ALREADY_EXISTS;
        }
        client.put(new WritePolicy(),key,bin1,bin2);

        return Constants.ADDED_SUCCESSFULLY;
    }

    public String deleteItem(int id)
    {
        key = new Key(Constants.DATA_BASE_NAME_SPACE, Constants.DATA_BASE_SET_NAME, Constants.DATA_BASE_KEY+id);
        if (!client.exists(null,key))
        {
            return Constants.DOES_NOT_EXIST;
        }
        client.delete(null,key);
        return Constants.DELETED_SUCESSFULLY;
    }

    public List<String> display() {
        Statement query = new Statement();
        query.setNamespace(Constants.DATA_BASE_NAME_SPACE);
        query.setSetName(Constants.DATA_BASE_SET_NAME);
        RecordSet rs = client.query(null,query);

        List<String> recordsArrayList = new ArrayList<>();
        while(rs.next()){
            recordsArrayList.add(rs.getRecord().bins.get(Constants.BIN_TWO_ID).toString() + " : " +rs.getRecord().bins.get(Constants.BIN_ONE_TYPE).toString() );
        }
        return recordsArrayList;

    }
}
