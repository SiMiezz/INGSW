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

    @PostMapping("/create")
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteByEmail(@PathVariable String email){
        boolean delete = userService.deleteByEmail(email);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/restaurant/{name}")
    public List<User> getByRestaurantName(@PathVariable String name){
        return userService.getByRestaurantName(name);
    }

    @GetMapping("/get/{email}/{pwd}")
    public User getByEmailAndPassword(@PathVariable String email,@PathVariable String pwd){
        Optional<User> user = userService.getByEmailAndPassword(email,pwd);

        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return user.get();
    }


}
