package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Interface.IUserService;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Retrofit.RetrofitService;
import com.ingsw.frontend.Retrofit.UserApi;

public class UserService implements IUserService {

    private UserApi userApi;

    public UserService() {
        this.userApi = RetrofitService.getRetrofit().create(UserApi.class);
    }

    @Override
    public void create(User user){}

    @Override
    public void deleteById(String id){}

    @Override
    public void getByRestaurantName(String name){}

    @Override
    public void getById(String id){}
}
