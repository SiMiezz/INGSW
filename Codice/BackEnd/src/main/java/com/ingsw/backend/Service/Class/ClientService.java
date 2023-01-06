package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Client;
import com.ingsw.backend.Repository.ClientRepository;
import com.ingsw.backend.Service.Interface.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainClientService")
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService() {
    }

    @Override
    public Client create(Client client){
        return clientRepository.save(client);
    }

    @Override
    public Boolean deleteById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);

        if(optionalClient.isEmpty()){
            return false;
        }

        clientRepository.delete(optionalClient.get());
        return true;
    }
}
