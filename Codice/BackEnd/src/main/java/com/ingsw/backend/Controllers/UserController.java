package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.User;
import com.ingsw.backend.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("mainUserService")
    private IUserService userService;

    @PostMapping("/")
    public User create(User user){
        return userService.create(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        boolean delete = userService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public List<User> getByRestaurantName(@PathVariable String name){
        return userService.getByRestaurantName(name);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable String id){
        Optional<User> user = userService.getById(id);

        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return user.get();
    }
}
