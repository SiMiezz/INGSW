package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.User;
import com.ingsw.backend.Repository.UserRepository;
import com.ingsw.backend.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainUserService")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(){}

    @Override
    public User create(User user){
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteById(String id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            return false;
        }

        userRepository.delete(optionalUser.get());
        return true;
    }
}
