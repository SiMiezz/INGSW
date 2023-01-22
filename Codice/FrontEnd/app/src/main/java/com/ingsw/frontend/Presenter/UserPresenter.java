package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.UserService;
import com.ingsw.frontend.View.Fragment.MembersAdminFragment;
import com.ingsw.frontend.View.Fragment.MembersChefsFragment;
import com.ingsw.frontend.View.Fragment.MembersSupervisorsFragment;
import com.ingsw.frontend.View.Fragment.MembersWaitersFragment;
import com.ingsw.frontend.View.Fragment.UserFragment;

public class UserPresenter {

    private MembersAdminFragment membersAdminFragment;
    private MembersSupervisorsFragment membersSupervisorsFragment;
    private MembersWaitersFragment membersWaitersFragment;
    private MembersChefsFragment membersChefsFragment;
    private UserFragment userFragment;

    private UserService userService;

    // CONSTRUCTORS

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

    public void create(User user){
        userService.create(new Callback() {
            @Override
            public void returnResult(Object o) {}

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
}
