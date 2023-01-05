package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Repository.ClientRepository;
import com.ingsw.backend.Service.Interface.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainClientService")
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService() {
    }
}
