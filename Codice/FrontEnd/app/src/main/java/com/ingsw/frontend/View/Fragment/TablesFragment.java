package com.ingsw.frontend.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ingsw.frontend.R;

public class TablesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FirebaseAnalytics firebaseAnalytics;

    private TablesNumberFragment tablesNumberFragment;
    private TablesAllFragment tablesAllFragment;
    private TablesSelectedFragment tablesSelectedFragment;

    public TablesFragment() {
        // Required empty public constructor
    }

    public static TablesFragment newInstance(String param1, String param2) {
        TablesFragment fragment = new TablesFragment();
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

        firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "Schermata Tables");
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "TablesFragment");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        tablesNumberFragment = new TablesNumberFragment();
        tablesSelectedFragment = new TablesSelectedFragment(tablesNumberFragment);
        tablesAllFragment = new TablesAllFragment(tablesSelectedFragment,tablesNumberFragment);

        fragmentTransaction.replace(R.id.number_tables_container, tablesNumberFragment);
        fragmentTransaction.replace(R.id.all_tables_container, tablesAllFragment);
        fragmentTransaction.replace(R.id.selected_table_container, tablesSelectedFragment);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tables, container, false);
    }
}