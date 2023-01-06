package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.TableRestaurant;
import com.ingsw.backend.Repository.TableRestaurantRepository;
import com.ingsw.backend.Service.Interface.ITableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mainTableRestaurantService")
public class TableRestaurantService implements ITableRestaurantService {

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    public TableRestaurantService(){}

    @Override
    public List<TableRestaurant> getByRestaurantName(String name){
        return tableRestaurantRepository.findByRestaurantName(name);
    }
}
