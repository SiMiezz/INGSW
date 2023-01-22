package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Presenter.MembersPresenter;
import com.ingsw.frontend.Presenter.UserPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Activity.HomeActivity;
import com.ingsw.frontend.View.Adapter.MemberAdapter;
import com.ingsw.frontend.View.Dialog.UserDialog;

import java.util.ArrayList;

public class MembersChefsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageButton removeButton;
    private ImageButton addButton;
    private ImageButton backButton;
    private ImageButton confirmButton;

    private MemberAdapter memberAdapter;
    private ArrayList<User> userArrayList;
    private RecyclerView recyclerView;
    private MembersPresenter memberPresenter;

    private Intent intent;
    private Restaurant restaurant;

    private UserPresenter userPresenter = new UserPresenter(this);

    public MembersChefsFragment() {
        // Required empty public constructor
    }

    public static MembersChefsFragment newInstance(String param1, String param2) {
        MembersChefsFragment fragment = new MembersChefsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_members_chefs, container, false);

        userArrayList = new ArrayList<>();
        removeButton = rootView.findViewById(R.id.remove_chef_button);
        addButton = rootView.findViewById(R.id.add_chef_button);
        backButton = rootView.findViewById(R.id.back_chef_button);
        confirmButton = rootView.findViewById(R.id.confirm_chef_button);
        recyclerView = rootView.findViewById(R.id.member_chef_listview);

        memberAdapter = new MemberAdapter(getContext(), userArrayList);

        memberPresenter = new MembersPresenter(null, null, null, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(memberAdapter);

        intent = getActivity().getIntent();

        restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        memberPresenter.getByRestaurantNameAndUserType(restaurant.getName(), User_Type.valueOf("chef"));

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(memberAdapter.getCurrentLayout() == -1){
                    memberAdapter.setCurrentLayout(-2);
                    memberAdapter.notifyDataSetChanged();
                }

                removeButton.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.VISIBLE);

                addButton.setVisibility(View.INVISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(memberAdapter.getCurrentLayout() == -2){
                    memberAdapter.setCurrentLayout(-1);
                    memberAdapter.notifyDataSetChanged();
                }

                backButton.setVisibility(View.INVISIBLE);
                removeButton.setVisibility(View.VISIBLE);

                confirmButton.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.VISIBLE);

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(User_Type.valueOf("chef"),restaurant.getName());
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeSelectedItems();
                ((HomeActivity)getActivity()).changeFragment(new MembersFragment());
                memberAdapter.setCurrentLayout(-1);
            }
        });



        return rootView;
    }

    public void loadUser(ArrayList<User> userList){
        memberAdapter.clearList();
        memberAdapter.setArrayList(userList);
        memberAdapter.notifyDataSetChanged();
    }

    public void removeSelectedItems() {
        ArrayList<User> users = memberAdapter.getSelectedItemsArrayList();
        for (User user: users) {
            memberPresenter.deleteById(user.getEmail());
        }
    }

    public void createUser(User user){
        userPresenter.create(user);
    }

    public void openDialog(User_Type job, String restaurant){
        UserDialog userDialog = new UserDialog(job,restaurant);
        userDialog.show(requireActivity().getSupportFragmentManager(),"User");
    }
}