package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.TableRestaurant;
import com.ingsw.backend.Repository.TableRestaurantRepository;
import com.ingsw.backend.Service.Interface.ITableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mainTableRestaurantService")
public class TableRestaurantService implements ITableRestaurantService {

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    public TableRestaurantService(){}

    @Override
    public List<TableRestaurant> getByRestaurantName(String name){
        return tableRestaurantRepository.findByRestaurantName(name);
    }

    @Override
    public void update(TableRestaurant tableRestaurant){
        tableRestaurantRepository.save(tableRestaurant);
    }

    @Override
    public Optional<TableRestaurant> getById(Integer id){
        return tableRestaurantRepository.findById(id);
    }

    @Override
    public Long countByRestaurantName(String name) {
        return tableRestaurantRepository.countByRestaurantName(name);
    }

    @Override
    public Long countByRestaurantNameAndFree(String name, boolean free) {
        return tableRestaurantRepository.countByRestaurantNameAndFree(name,free);
    }

}
