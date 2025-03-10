package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Activity.HomeActivity;
import com.ingsw.frontend.View.Dialog.LoginDefaultDialog;

public class SectionButtonsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    // **********************************************

    private Button restaurantButton;
    private Button menuButton;
    private Button tablesButton;
    private Button membersButton;
    private Button kitchenButton;

    // ********************************************

    private Intent intent;
    private User user;

    public SectionButtonsFragment() {
        // Required empty public constructor
    }

    public static SectionButtonsFragment newInstance(String param1, String param2) {
        SectionButtonsFragment fragment = new SectionButtonsFragment();
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
       View rootView = inflater.inflate(R.layout.fragment_section_buttons, container, false);

       return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantButton = (Button) getView().findViewById(R.id.restaurantButton);
        menuButton = (Button) getView().findViewById(R.id.menuButton);
        tablesButton = (Button) getView().findViewById(R.id.tablesButton);
        membersButton = (Button) getView().findViewById(R.id.membersButton);
        kitchenButton = (Button) getView().findViewById(R.id.kitchenButton);

        restaurantButton.setOnClickListener(restaurantButtonListener());
        menuButton.setOnClickListener(menuButtonListener());
        tablesButton.setOnClickListener(tablesButtonListener());
        membersButton.setOnClickListener(membersButtonListener());
        kitchenButton.setOnClickListener(kitchenButtonListener());

        intent = getActivity().getIntent();
        user = (User) intent.getSerializableExtra("user");

        if((!user.getPwd().equals(User.getDefaultPwd()))){
            switch(user.getJob().toString()){
                case "admin":
                    restaurantButton = setButton(restaurantButton);
                    lockButtons("admin");
                    break;
                case "supervisor":
                    menuButton = setButton(menuButton);
                    lockButtons("supervisor");
                    break;
                case "waiter":
                    tablesButton = setButton(tablesButton);
                    lockButtons("waiter");
                    break;
                case "chef":
                    kitchenButton = setButton(kitchenButton);
                    lockButtons("chef");
                    break;
            }
        }
        else{
            openDialog();
        }
    }

    public void openDialog(){
        LoginDefaultDialog dialog = new LoginDefaultDialog();
        dialog.show(requireActivity().getSupportFragmentManager(), "LoginDefault");
    }

    public View.OnClickListener restaurantButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if((!user.getPwd().equals(User.getDefaultPwd()))){
                    if(user.getJob().toString().equals("admin")){
                        restaurantButton = setButton(restaurantButton);
                        menuButton = deselectButton(menuButton);
                        tablesButton = deselectButton(tablesButton);
                        membersButton = deselectButton(membersButton);
                        kitchenButton = deselectButton(kitchenButton);
                        lockButtons("admin");
                        ((HomeActivity)getActivity()).changeFragment(new RestaurantFragment());
                    }
                }
            }
        };
        return listener;
    }

    public View.OnClickListener menuButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if((!user.getPwd().equals(User.getDefaultPwd()))){
                    if(user.getJob().toString().equals("admin") || user.getJob().toString().equals("supervisor")){
                        restaurantButton = deselectButton(restaurantButton);
                        menuButton = setButton(menuButton);
                        tablesButton = deselectButton(tablesButton);
                        membersButton = deselectButton(membersButton);
                        kitchenButton = deselectButton(kitchenButton);

                        if(user.getJob().toString().equals("admin"))
                            lockButtons("admin");
                        else if(user.getJob().toString().equals("supervisor"))
                            lockButtons("supervisor");

                        ((HomeActivity)getActivity()).changeFragment(new MenuFragment());
                    }
                }
            }
        };
        return listener;
    }

    public View.OnClickListener tablesButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if((!user.getPwd().equals(User.getDefaultPwd()))){
                    if(user.getJob().toString().equals("waiter")){
                        restaurantButton = deselectButton(restaurantButton);
                        menuButton = deselectButton(menuButton);
                        tablesButton = setButton(tablesButton);
                        membersButton = deselectButton(membersButton);
                        kitchenButton = deselectButton(kitchenButton);
                        lockButtons("waiter");
                        ((HomeActivity)getActivity()).changeFragment(new TablesFragment());
                    }
                }
            }
        };
        return listener;
    }

    public View.OnClickListener membersButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if((!user.getPwd().equals(User.getDefaultPwd()))){
                    if(user.getJob().toString().equals("admin")){
                        restaurantButton = deselectButton(restaurantButton);
                        menuButton = deselectButton(menuButton);
                        tablesButton = deselectButton(tablesButton);
                        membersButton = setButton(membersButton);
                        kitchenButton = deselectButton(kitchenButton);
                        lockButtons("admin");
                        ((HomeActivity)getActivity()).changeFragment(new MembersFragment());
                    }
                }
            }
        };
        return listener;
    }

    public View.OnClickListener kitchenButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if((!user.getPwd().equals(User.getDefaultPwd()))){
                    if(user.getJob().toString().equals("chef")){
                        restaurantButton = deselectButton(restaurantButton);
                        menuButton = deselectButton(menuButton);
                        tablesButton = deselectButton(tablesButton);
                        membersButton = deselectButton(membersButton);
                        kitchenButton = setButton(kitchenButton);
                        lockButtons("chef");
                        ((HomeActivity)getActivity()).changeFragment(new KitchenFragment());
                    }
                }
            }
        };
        return listener;
    }

    public Button setButton(Button button){

        Button newButton = button;

        newButton.setSelected(true);
        newButton.setBackgroundResource(R.drawable.shape_section_buttons_inverso);
        newButton.setTextColor(Color.WHITE);

        if(newButton.getText().equals("Restaurant")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_restaurant_inverso,0,R.drawable.icon_arrow_inverso, 0);
        }
        else if(newButton.getText().equals("Menu")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_menu_inverso,0,R.drawable.icon_arrow_inverso, 0);
        }
        else if(newButton.getText().equals("Tables")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_tables_inverso,0,R.drawable.icon_arrow_inverso, 0);
        }
        else if(newButton.getText().equals("Members")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_members_inverso,0,R.drawable.icon_arrow_inverso, 0);
        }
        else if(newButton.getText().equals("Kitchen")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_kitchen_inverso,0,R.drawable.icon_arrow_inverso, 0);
        }

        return newButton;
    }

    public Button deselectButton(Button button){

        Button newButton = button;

        newButton.setSelected(false);
        newButton.setBackgroundResource(R.drawable.shape_section_buttons);
        newButton.setTextColor(Color.parseColor("#787486"));

        if(newButton.getText().equals("Restaurant")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_restaurant,0,R.drawable.icon_arrow, 0);
        }
        else if(newButton.getText().equals("Menu")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_menu,0,R.drawable.icon_arrow, 0);
        }
        else if(newButton.getText().equals("Tables")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_tables,0,R.drawable.icon_arrow, 0);
        }
        else if(newButton.getText().equals("Members")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_members,0,R.drawable.icon_arrow, 0);
        }
        else if(newButton.getText().equals("Kitchen")){
            newButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_kitchen,0,R.drawable.icon_arrow, 0);
        }

        return newButton;

    }

    public void lockButtons(String job){
        switch (job){
            case "admin":
                tablesButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                kitchenButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                break;
            case "supervisor":
                restaurantButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                tablesButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                membersButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                kitchenButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                break;
            case "waiter":
                restaurantButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                menuButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                membersButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                kitchenButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                break;
            case "chef":
                restaurantButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                menuButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                tablesButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                membersButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_lock,0,0,0);
                break;
        }
    }



}