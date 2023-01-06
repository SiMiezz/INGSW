package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.User;
import com.ingsw.backend.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    @Autowired
    @Qualifier("mainUserService")
    private IUserService userService;

    @PostMapping("/user")
    public User create(User user){
        return userService.create(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable String id){
        boolean delete = userService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
