package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Client;

public interface IClientService {

    public Client create(Client client);

    public Boolean deleteById(int id);
}
