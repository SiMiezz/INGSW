package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Presenter.UserPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.MemberAdapter;

import java.util.ArrayList;

public class MembersAdminFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private MemberAdapter memberAdapter;
    private ArrayList<User> userArrayList;
    private RecyclerView recyclerView;

    private Intent intent;
    private Restaurant restaurant;
    private final User_Type job = User_Type.admin;

    private UserPresenter userPresenter = new UserPresenter(this);

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User_Type getJob() {
        return job;
    }

    public MembersAdminFragment() {
        // Required empty public constructor
    }

    public static MembersAdminFragment newInstance(String param1, String param2) {
        MembersAdminFragment fragment = new MembersAdminFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_members_admin, container, false);

        userArrayList = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.member_admin_listview);

        memberAdapter = new MemberAdapter(getContext(),userArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(memberAdapter);

        intent = getActivity().getIntent();

        restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        userPresenter.getByRestaurantNameAndUserType(restaurant.getName(), User_Type.valueOf("admin"));


        return rootView;
    }


    public void loadUser(ArrayList<User> userList){
        memberAdapter.clearList();
        memberAdapter.setArrayList(userList);
        memberAdapter.notifyDataSetChanged();
    }
}