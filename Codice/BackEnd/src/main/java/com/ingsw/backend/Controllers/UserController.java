package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.DTO.UserDTO;
import com.ingsw.backend.Model.Enumerations.User_Type;
import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Model.User;
import com.ingsw.backend.Service.Interface.IRestaurantService;
import com.ingsw.backend.Service.Interface.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("mainUserService")
    private IUserService userService;

    @Autowired
    @Qualifier("mainRestaurantService")
    private IRestaurantService restaurantService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public void create(@RequestBody UserDTO userDTO){
        User user = this.convertEntity(userDTO);

        userService.create(user);
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

    @GetMapping("/get/restaurant/{restaurant_name}/{job}")
    public List<UserDTO> getByRestaurantNameAndJob(@PathVariable String restaurant_name,@PathVariable User_Type job){

        List<User> userList = userService.getByRestaurantNameAndJob(restaurant_name,job);

        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userList) {
            userDTOS.add(convertDTO(user));
        }

        return userDTOS;
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

    private User convertEntity(UserDTO userDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        User user = new User();
        user = modelMapper.map(userDTO, User.class);

        //Mapping
        String name = userDTO.getRestaurantName();
        Optional<Restaurant> restaurantOptional = this.restaurantService.getByName(name);

        if(!restaurantOptional.isEmpty()){
            user.setRestaurant(restaurantOptional.get());
        }

        return user;
    }

}
