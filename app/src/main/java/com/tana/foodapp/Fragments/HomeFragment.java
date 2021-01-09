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
import com.tana.foodapp.DishesAdapter;
import com.tana.foodapp.FavoriteFoodAdapter;
import com.tana.foodapp.Model.Dishes;
import com.tana.foodapp.Model.FavFood;
import com.tana.foodapp.Model.PopFood;
import com.tana.foodapp.PopularFoodAdapter;
import com.tana.foodapp.R;

import java.util.List;

public class HomeFragment extends Fragment {
    Toolbar toolbar;
    DrawerLayout mDrawer;
    NavigationView mNavView;
    RecyclerView favFoodList, popFoodList, dishesList;
//    FavoriteFoodAdapter mFavFoodAdapter;
//    PopularFoodAdapter mPopFoodAdapter;
//    LinearLayoutManager mLayoutManager;

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

        favFoodList = (RecyclerView) view.findViewById(R.id.top_list);
        popFoodList = (RecyclerView) view.findViewById(R.id.popular_food_list);
        dishesList = (RecyclerView) view.findViewById(R.id.dishes_list);
        mHelper = new DatabaseHelper();

        mDrawer = view.findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        favoriteFoodData();
        popularFoodData();
        dishesData();

    }

    private void dishesData() {
        mHelper.readAllDishes(new DatabaseHelper.DishesDataStatus() {
            @Override
            public void dataIsLoaded(List<Dishes> dishes, List<String> keys) {
                DishesAdapter adapter = new DishesAdapter(getContext(), dishes, keys);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

                dishesList.setLayoutManager(layoutManager);
                dishesList.setAdapter(adapter);
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

    private void popularFoodData() {
        mHelper.readPopularFood(new DatabaseHelper.PopFoodDataStatus() {
            @Override
            public void dataIsLoaded(List<PopFood> popFoods, List<String> keys) {
                PopularFoodAdapter adapter = new PopularFoodAdapter(getContext(), popFoods, keys);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

                popFoodList.setLayoutManager(layoutManager);
                popFoodList.setAdapter(adapter);
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

    private void favoriteFoodData() {
        mHelper.readFavoriteFood(new DatabaseHelper.FavFoodDataStatus() {
            @Override
            public void dataIsLoaded(List<FavFood> favFoods, List<String> keys) {
                FavoriteFoodAdapter adapter = new FavoriteFoodAdapter(getContext(), favFoods, keys);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

                favFoodList.setLayoutManager(layoutManager);
                favFoodList.setAdapter(adapter);
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