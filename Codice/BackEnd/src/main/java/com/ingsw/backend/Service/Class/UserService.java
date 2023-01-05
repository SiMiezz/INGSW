package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Repository.UserRepository;
import com.ingsw.backend.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainUserService")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(){}
}
