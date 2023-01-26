package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.UserService;
import com.ingsw.frontend.View.Fragment.LoginFragment;
import com.ingsw.frontend.View.Fragment.MembersAdminFragment;
import com.ingsw.frontend.View.Fragment.MembersChefsFragment;
import com.ingsw.frontend.View.Fragment.MembersSupervisorsFragment;
import com.ingsw.frontend.View.Fragment.MembersWaitersFragment;
import com.ingsw.frontend.View.Fragment.UserFragment;

import java.util.ArrayList;

public class UserPresenter {

    private MembersAdminFragment membersAdminFragment;
    private MembersSupervisorsFragment membersSupervisorsFragment;
    private MembersWaitersFragment membersWaitersFragment;
    private MembersChefsFragment membersChefsFragment;
    private UserFragment userFragment;
    private LoginFragment loginFragment;

    private UserService userService;

    private RestaurantPresenter restaurantPresenter;

    // CONSTRUCTORS

    public UserPresenter() {
        userService = new UserService();
    }

    public UserPresenter(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
        restaurantPresenter = new RestaurantPresenter(loginFragment);
        userService = new UserService();
    }

    public UserPresenter(UserFragment userFragment) {
        this.userFragment = userFragment;
        userService = new UserService();
    }

    public UserPresenter(MembersAdminFragment membersAdminFragment) {
        this.membersAdminFragment = membersAdminFragment;
        userService = new UserService();
    }

    public UserPresenter(MembersSupervisorsFragment membersSupervisorsFragment) {
        this.membersSupervisorsFragment = membersSupervisorsFragment;
        userService = new UserService();
    }

    public UserPresenter(MembersWaitersFragment membersWaitersFragment) {
        this.membersWaitersFragment = membersWaitersFragment;
        userService = new UserService();
    }

    public UserPresenter(MembersChefsFragment membersChefsFragment) {
        this.membersChefsFragment = membersChefsFragment;
        userService = new UserService();
    }

    // GETTER AND SETTER

    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public void setLoginFragment(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
    }

    public UserFragment getUserFragment() {
        return userFragment;
    }

    public void setUserFragment(UserFragment userFragment) {
        this.userFragment = userFragment;
    }

    public MembersAdminFragment getMembersAdminFragment() {
        return membersAdminFragment;
    }

    public void setMembersAdminFragment(MembersAdminFragment membersAdminFragment) {
        this.membersAdminFragment = membersAdminFragment;
    }

    public MembersSupervisorsFragment getMembersSupervisorsFragment() {
        return membersSupervisorsFragment;
    }

    public void setMembersSupervisorsFragment(MembersSupervisorsFragment membersSupervisorsFragment) {
        this.membersSupervisorsFragment = membersSupervisorsFragment;
    }

    public MembersWaitersFragment getMembersWaitersFragment() {
        return membersWaitersFragment;
    }

    public void setMembersWaitersFragment(MembersWaitersFragment membersWaitersFragment) {
        this.membersWaitersFragment = membersWaitersFragment;
    }

    public MembersChefsFragment getMembersChefsFragment() {
        return membersChefsFragment;
    }

    public void setMembersChefsFragment(MembersChefsFragment membersChefsFragment) {
        this.membersChefsFragment = membersChefsFragment;
    }

    public void delete(User user) {
        userService.delete(new Callback(){

            @Override
            public void returnResult(Object o) {
                getByRestaurantNameAndUserType(user.getRestaurantName(),user.getJob());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },user);
    }

    public void create(User user){
        userService.create(new Callback() {
            @Override
            public void returnResult(Object o) {
                getByRestaurantNameAndUserType(user.getRestaurantName(),user.getJob());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },user);
    }

    public void update(User user){
        userService.update(new Callback() {
            @Override
            public void returnResult(Object o) {}

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },user);
    }

    public void checkUser(String email, String pwd){
        userService.checkUser(new Callback(){
            @Override
            public void returnResult(Object o) {
                Boolean res = (Boolean) o;

                if(res){
                    getUser(email,pwd);
                }
                else{
                    loginFragment.loginFail();
                }
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },email,pwd);
    }

    public void getUser(String email, String pwd){
        userService.getUser(new Callback(){
            @Override
            public void returnResult(Object o) {
                User user = (User) o;

                loginFragment.loginSuccess(user);
                restaurantPresenter.getByName(user.getRestaurantName());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },email,pwd);
    }

    public void getByRestaurantNameAndUserType(String restaurantName, User_Type job){
        userService.getByRestaurantNameAndJob(new Callback(){

            @Override
            public void returnResult(Object o) {
                ArrayList<User> userArrayList = (ArrayList<User>) o;

                if(job == User_Type.valueOf("admin"))
                    membersAdminFragment.loadUser(userArrayList);
                else if(job == User_Type.valueOf("supervisor"))
                    membersSupervisorsFragment.loadUser(userArrayList);
                else if(job == User_Type.valueOf("waiter"))
                    membersWaitersFragment.loadUser(userArrayList);
                else if(job == User_Type.valueOf("chef"))
                    membersChefsFragment.loadUser(userArrayList);

            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, restaurantName, job);
    }
}
