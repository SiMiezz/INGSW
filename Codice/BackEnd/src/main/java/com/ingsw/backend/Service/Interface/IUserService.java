package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public User create(User user);

    public Boolean deleteById(String id);

    public List<User> getByRestaurantName(String name);

    public Optional<User> getById(String id);
}
