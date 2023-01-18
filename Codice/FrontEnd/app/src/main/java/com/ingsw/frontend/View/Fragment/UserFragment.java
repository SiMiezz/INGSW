package com.ingsw.frontend.View.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Activity.LoginActivity;

public class UserFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private Button arrowButton;
    private TextView userView;
    private ImageView imageView;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        arrowButton = (Button) rootView.findViewById(R.id.arrowButton);
        userView = (TextView) rootView.findViewById(R.id.TextUser);
        imageView = (ImageView) rootView.findViewById(R.id.ImageUser);

        Intent intent = getActivity().getIntent();

        User user = (User) intent.getSerializableExtra("user");

        loadUser(user);

        arrowButton.setOnClickListener(arrowButtonOListener());


        return rootView;
    }


    public View.OnClickListener arrowButtonOListener(){

        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                
                @SuppressLint("RestrictedApi") MenuBuilder menuBuilder =new MenuBuilder(getContext());
                MenuInflater inflater = new MenuInflater(getContext());
                inflater.inflate(R.menu.user_menu, menuBuilder);
                @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenu = new MenuPopupHelper(getContext(), menuBuilder, arrowButton);
                optionsMenu.setForceShowIcon(true);
                optionsMenu.show();

                MenuItem menuItem = (MenuItem) menuBuilder.getItem(1);

                menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);
                        return true;
                    }
                });

            }

        };

        return listener;
    }

    public void loadUser(User user){
        userView.setText(user.getName() + ", " + user.getSurname());

        switch(user.getJob().toString()){
            case "admin":
                imageView.setImageResource(R.drawable.icon_members_admin);
                break;
            case "supervisor":
                imageView.setImageResource(R.drawable.icon_members_supervisor);
                break;
            case "waiter":
                imageView.setImageResource(R.drawable.icon_members_waiter);
                break;
            case "chef":
                imageView.setImageResource(R.drawable.icon_members_chef);
                break;
        }
    }

}