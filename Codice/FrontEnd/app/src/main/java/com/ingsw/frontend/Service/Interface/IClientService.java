package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Client;

public interface IClientService {

    void create(Client client);

    void deleteById(Integer id);
}
