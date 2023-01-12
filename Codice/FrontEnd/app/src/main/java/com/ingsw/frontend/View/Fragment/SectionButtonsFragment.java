package com.ingsw.frontend.View.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ingsw.frontend.View.Activity.HomeActivity;
import com.ingsw.frontend.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectionButtonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionButtonsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // **********************************************

    private Button restaurantButton;
    private Button menuButton;
    private Button tablesButton;
    private Button membersButton;
    private Button kitchenButton;

    // ********************************************



    public SectionButtonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSectionButtons.
     */
    // TODO: Rename and change types and number of parameters
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

       restaurantButton = (Button) rootView.findViewById(R.id.restaurantButton);
       menuButton = (Button) rootView.findViewById(R.id.menuButton);
       tablesButton = (Button) rootView.findViewById(R.id.tablesButton);
       membersButton = (Button) rootView.findViewById(R.id.membersButton);
       kitchenButton = (Button) rootView.findViewById(R.id.kitchenButton);

       restaurantButton.setOnClickListener(restaurantButtonListener());
       menuButton.setOnClickListener(menuButtonListener());
       tablesButton.setOnClickListener(tablesButtonListener());
       membersButton.setOnClickListener(membersButtonListener());
       kitchenButton.setOnClickListener(kitchenButtonListener());

        restaurantButton = setButton(restaurantButton);

        return rootView;
    }


    public View.OnClickListener restaurantButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                restaurantButton = setButton(restaurantButton);
                menuButton = deselectButton(menuButton);
                tablesButton = deselectButton(tablesButton);
                membersButton = deselectButton(membersButton);
                kitchenButton = deselectButton(kitchenButton);

                ((HomeActivity)getActivity()).changeFragment(new RestaurantFragment());
            }
        };
        return listener;
    }

    public View.OnClickListener menuButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                restaurantButton = deselectButton(restaurantButton);
                menuButton = setButton(menuButton);
                tablesButton = deselectButton(tablesButton);
                membersButton = deselectButton(membersButton);
                kitchenButton = deselectButton(kitchenButton);

                ((HomeActivity)getActivity()).changeFragment(new MenuFragment());
            }
        };
        return listener;
    }

    public View.OnClickListener tablesButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                restaurantButton = deselectButton(restaurantButton);
                menuButton = deselectButton(menuButton);
                tablesButton = setButton(tablesButton);
                membersButton = deselectButton(membersButton);
                kitchenButton = deselectButton(kitchenButton);

                ((HomeActivity)getActivity()).changeFragment(new TablesFragment());
            }
        };
        return listener;
    }

    public View.OnClickListener membersButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                restaurantButton = deselectButton(restaurantButton);
                menuButton = deselectButton(menuButton);
                tablesButton = deselectButton(tablesButton);
                membersButton = setButton(membersButton);
                kitchenButton = deselectButton(kitchenButton);

                ((HomeActivity)getActivity()).changeFragment(new MembersFragment());
            }
        };
        return listener;
    }

    public View.OnClickListener kitchenButtonListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                restaurantButton = deselectButton(restaurantButton);
                menuButton = deselectButton(menuButton);
                tablesButton = deselectButton(tablesButton);
                membersButton = deselectButton(membersButton);
                kitchenButton = setButton(kitchenButton);

                ((HomeActivity)getActivity()).changeFragment(new KitchenFragment());
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



}