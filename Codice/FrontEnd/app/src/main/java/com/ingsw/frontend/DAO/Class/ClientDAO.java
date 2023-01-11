package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.IClientDAO;
import com.ingsw.frontend.Model.Client;
import com.ingsw.frontend.Retrofit.CategoryRetrofit;
import com.ingsw.frontend.Retrofit.ClientRetrofit;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class ClientDAO implements IClientDAO {

    private ClientRetrofit clientRetrofit;

    public ClientDAO() {
        this.clientRetrofit = RetrofitService.getRetrofit().create(ClientRetrofit.class);
    }

    @Override
    public void create(Client client){}

    @Override
    public void deleteById(Integer id){}
}
