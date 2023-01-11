package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Interface.IClientService;
import com.ingsw.frontend.Model.Client;
import com.ingsw.frontend.Retrofit.ClientApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class ClientService implements IClientService {

    private ClientApi clientApi;

    public ClientService() {
        this.clientApi = RetrofitService.getRetrofit().create(ClientApi.class);
    }

    @Override
    public void create(Client client){}

    @Override
    public void deleteById(Integer id){}
}
