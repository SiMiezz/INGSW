package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.DTO.CategoryDTO;
import com.ingsw.backend.Model.DTO.UserDTO;
import com.ingsw.backend.Model.User;
import com.ingsw.backend.Service.Interface.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

    @Autowired
    private ModelMapper modelMapper;

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
    public UserDTO getByEmailAndPassword(@PathVariable String email, @PathVariable String pwd){
        Optional<User> user = userService.getByEmailAndPassword(email,pwd);

        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = convertDTO(user.get());

        return userDTO;
    }

    private UserDTO convertDTO(User user) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDTO userDTO = new UserDTO();
        userDTO = modelMapper.map(user, UserDTO.class);

        String restaurant_name = user.getRestaurant().getName();
        userDTO.setRestaurantName(restaurant_name);
        return userDTO;
    }

}
