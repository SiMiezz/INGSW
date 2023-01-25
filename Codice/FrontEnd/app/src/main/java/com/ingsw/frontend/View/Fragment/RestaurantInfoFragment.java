package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Presenter.RestaurantInfoPresenter;
import com.ingsw.frontend.R;

public class RestaurantInfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Intent intent;
    private Restaurant restaurant;
    private RestaurantInfoPresenter restaurantInfoPresenter;

    private TextView nameRestaurant;
    private TextView descriptionRestaurant;
    private TextView localityRestaurant;
    private TextView touristicRestaurant;
    private TextView qrRestaurant;


    public RestaurantInfoFragment() {
        // Required empty public constructor
    }

    public static RestaurantInfoFragment newInstance(String param1, String param2) {
        RestaurantInfoFragment fragment = new RestaurantInfoFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_restaurant_info, container, false);
        nameRestaurant = rootView.findViewById(R.id.info_nome_restaurant_text);
        descriptionRestaurant = rootView.findViewById(R.id.info_descrizione_restaurant_text);
        localityRestaurant = rootView.findViewById(R.id.info_locality_restaurant_text);
        touristicRestaurant = rootView.findViewById(R.id.info_touristic_restaurant_text);
        qrRestaurant = rootView.findViewById(R.id.info_qrcode_restaurant_text);

        restaurantInfoPresenter = new RestaurantInfoPresenter(this);

        intent = getActivity().getIntent();

        restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        restaurantInfoPresenter.getByName(restaurant.getName());

        return rootView;
    }


    public void loadInfo(Restaurant restaurant){
        nameRestaurant.setText(restaurant.getName());
        descriptionRestaurant.setText(restaurant.getDescription());
        localityRestaurant.setText(restaurant.getLocality());

        if(restaurant.isTouristic())
            touristicRestaurant.setText("Yes");
        else
            touristicRestaurant.setText("No");
    }
}