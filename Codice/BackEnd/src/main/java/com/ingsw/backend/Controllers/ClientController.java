package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Client;
import com.ingsw.backend.Service.Interface.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    @Qualifier("mainClientService")
    private IClientService clientService;

    @PostMapping("/")
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        boolean delete = clientService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
