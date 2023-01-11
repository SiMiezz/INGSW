package com.ingsw.frontend.DAO.Interface;

import com.ingsw.frontend.Model.Client;

public interface IClientDAO {

    void create(Client client);

    void deleteById(Integer id);
}
