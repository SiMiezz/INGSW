package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Enumerations.User_Type;
import com.ingsw.backend.Model.User;
import com.ingsw.backend.Repository.UserRepository;
import com.ingsw.backend.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mainUserService")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(){}

    @Override
    public void update(User user){
        userRepository.save(user);
    }

    @Override
    public void create(User user){
        userRepository.save(user);
    }

    @Override
    public void delete(User user){
        userRepository.delete(user);
    }

    @Override
    public List<User> getByRestaurantName(String name){
        return userRepository.findByRestaurantName(name);
    }

    @Override
    public Optional<User> getByEmailAndPassword(String email,String pwd){
        return userRepository.findByEmailAndPwd(email,pwd);
    }

    @Override
    public List<User> getByRestaurantNameAndJob(String restaurant_name, User_Type job) {
        return userRepository.findByRestaurantNameAndJob(restaurant_name, job);
    }
}
