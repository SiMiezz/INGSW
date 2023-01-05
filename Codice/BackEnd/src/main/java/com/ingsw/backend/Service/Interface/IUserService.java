package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.User;

public interface IUserService {

    public User create(User user);

    public Boolean deleteById(String id);
}
