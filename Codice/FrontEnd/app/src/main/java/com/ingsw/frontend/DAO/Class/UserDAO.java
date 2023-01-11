package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.IUserDAO;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Retrofit.RetrofitService;
import com.ingsw.frontend.Retrofit.TableRestaurantRetrofit;
import com.ingsw.frontend.Retrofit.UserRetrofit;

public class UserDAO implements IUserDAO {

    private UserRetrofit userRetrofit;

    public UserDAO() {
        this.userRetrofit = RetrofitService.getRetrofit().create(UserRetrofit.class);
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
