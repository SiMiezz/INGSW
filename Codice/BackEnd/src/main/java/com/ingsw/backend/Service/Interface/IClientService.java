package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Client;

public interface IClientService {

    Client create(Client client);

    Boolean deleteById(Integer id);
}
