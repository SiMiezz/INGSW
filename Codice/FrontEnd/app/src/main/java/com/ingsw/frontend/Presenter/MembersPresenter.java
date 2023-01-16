package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.UserService;
import com.ingsw.frontend.View.Fragment.MembersAdminFragment;
import com.ingsw.frontend.View.Fragment.MembersChefsFragment;
import com.ingsw.frontend.View.Fragment.MembersSupervisorsFragment;
import com.ingsw.frontend.View.Fragment.MembersWaitersFragment;

import java.util.ArrayList;

public class MembersPresenter {

    private final MembersAdminFragment membersAdminFragment;
    private final MembersSupervisorsFragment membersSupervisorsFragment;
    private final MembersWaitersFragment membersWaitersFragment;
    private final MembersChefsFragment membersChefsFragment;

    private UserService userService;

    public MembersPresenter(MembersAdminFragment membersAdminFragment, MembersSupervisorsFragment membersSupervisorsFragment, MembersWaitersFragment membersWaitersFragment, MembersChefsFragment membersChefsFragment) {
        this.membersAdminFragment = membersAdminFragment;
        this.membersSupervisorsFragment = membersSupervisorsFragment;
        this.membersWaitersFragment = membersWaitersFragment;
        this.membersChefsFragment = membersChefsFragment;
        this.userService = new UserService();
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

            }
        }, restaurantName, job);
    }
}
