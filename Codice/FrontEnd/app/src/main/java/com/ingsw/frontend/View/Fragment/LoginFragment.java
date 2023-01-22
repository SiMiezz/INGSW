package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Presenter.LoginPresenter;
import com.ingsw.frontend.View.Activity.HomeActivity;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Dialog.LoginFailDialog;

public class LoginFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String email,pwd;

    private TextView emailView,pwdView;

    private LoginPresenter loginPresenter;

    private Intent intent;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

        intent = new Intent(getActivity(), HomeActivity.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        emailView = (TextView) rootView.findViewById(R.id.editTextEmail);
        pwdView = (TextView) rootView.findViewById(R.id.editTextPassword);

        loginPresenter = new LoginPresenter(this);

        Button loginButton = (Button) rootView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailView.getText().toString();
                pwd = pwdView.getText().toString();


                if(!(pwd.isEmpty() || email.isEmpty())){
                    loginPresenter.checkUser(email,pwd);
                }
            }
        });

        return rootView;
    }

    public void openDialog(){
        LoginFailDialog dialog = new LoginFailDialog();
        dialog.show(requireActivity().getSupportFragmentManager(), "Login");
    }

    public void loginFail(){
        openDialog();
        pwdView.setText("");
    }

    public void loginSuccess(User user){
        intent.putExtra("user",user);
    }

    public void loadRestaurant(Restaurant restaurant){
        intent.putExtra("restaurant",restaurant);
    }

    public void loadMenu(Menu menu){
        intent.putExtra("menu",menu);
        getActivity().startActivity(intent);
    }
}