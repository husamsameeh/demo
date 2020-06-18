package com.example.demo.business;

import com.example.demo.data.objects.CoffeeDao;
import com.example.demo.data.objects.CoffeeDto;
import com.example.demo.data.storage.DataBase;
import com.example.Coffe;
import org.modelmapper.ModelMapper;

import java.text.ParseException;

public class DataAcessLayer {

    ModelMapper modelMapper = new ModelMapper();

    DataBase db = DataBase.getInstance();
    public String addIntoDB(CoffeeDto coffe) throws ParseException{
        return db.addItem(convertToEntity(coffe));
    }
    public String displayDB(){
        return db.display().toString();
    }
    public String deleteDB(int id)
    {
        return db.deleteItem(id);
    }


    private CoffeeDto convertToDto(CoffeeDao coffe) {
        if (coffe == null)
        {
            return null;
        }
        CoffeeDto coffeeDto = modelMapper.map(coffe, CoffeeDto.class);
        return coffeeDto;
    }


    private CoffeeDao convertToEntity(CoffeeDto coffeeDto) throws ParseException {
        if (coffeeDto == null)
        {
            return null;
        }
        CoffeeDao coffee = modelMapper.map(coffeeDto, CoffeeDao.class);
        return coffee;
    }

    public CoffeeDto getFromDB(int id) {
        CoffeeDao sample = db.getItem(id);
        return convertToDto(sample);
    }
}
