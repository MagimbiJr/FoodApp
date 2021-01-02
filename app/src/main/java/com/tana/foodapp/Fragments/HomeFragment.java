package com.tana.foodapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.tana.foodapp.DatabaseHelper;
import com.tana.foodapp.FavoriteFoodAdapter;
import com.tana.foodapp.Model.Favorite;
import com.tana.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    Toolbar toolbar;
    DrawerLayout mDrawer;
    NavigationView mNavView;
    RecyclerView mFavFoodList;
    FavoriteFoodAdapter mFavFoodAdapter;
    LinearLayoutManager mLayoutManager;
    List<Favorite> mFavFoods;
    List<String> mKeys;

    DatabaseHelper mHelper;



    public HomeFragment() {
        // Required empty public constructor
    }
//    // TODO: Rename and change types and number of parameters
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.lightGray));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        mFavFoodList = view.findViewById(R.id.top_list);
        mHelper = new DatabaseHelper();

        mDrawer = view.findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        favoriteFoodData();

    }

    private void favoriteFoodData() {
        mHelper.readFavoriteFood(new DatabaseHelper.DataStatus() {
            @Override
            public void dataIsLoaded(List<Favorite> foods, List<String> keys) {
                mFavFoodAdapter = new FavoriteFoodAdapter(getContext(), foods, keys);
                mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

                mFavFoodList.setLayoutManager(mLayoutManager);
                mFavFoodList.setAdapter(mFavFoodAdapter);

            }

            @Override
            public void dataIsInserted() {

            }

            @Override
            public void dataIsUpdated() {

            }

            @Override
            public void dataIsDeleted() {

            }
        });
    }


}