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
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.MemberAdapter;

import java.util.ArrayList;

public class MembersSupervisorsFragment extends Fragment {

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

    public MembersSupervisorsFragment() {
        // Required empty public constructor
    }

    public static MembersSupervisorsFragment newInstance(String param1, String param2) {
        MembersSupervisorsFragment fragment = new MembersSupervisorsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_members_supervisors, container, false);

        userArrayList = new ArrayList<>();
        removeButton = rootView.findViewById(R.id.remove_supervisor_button);
        addButton = rootView.findViewById(R.id.add_supervisor_button);
        backButton = rootView.findViewById(R.id.back_supervisor_button);
        confirmButton = rootView.findViewById(R.id.confirm_supervisor_button);
        recyclerView = rootView.findViewById(R.id.member_supervisor_listview);

        memberAdapter = new MemberAdapter(getContext(), userArrayList);

        memberPresenter = new MembersPresenter(null, this, null, null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(memberAdapter);

        Intent intent = getActivity().getIntent();

        Restaurant restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        memberPresenter.getByRestaurantNameAndUserType(restaurant.getName(), User_Type.valueOf("supervisor"));

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MemberAdapter.currentLayout == -1){
                    MemberAdapter.currentLayout = -2;
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
                if(MemberAdapter.currentLayout == -2){
                    MemberAdapter.currentLayout = -1;
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

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeSelectedItems();
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
}