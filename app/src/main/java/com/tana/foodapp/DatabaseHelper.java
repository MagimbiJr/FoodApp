package com.tana.foodapp;

import androidx.annotation.NonNull;
import androidx.core.app.RemoteInput;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tana.foodapp.Model.Dishes;
import com.tana.foodapp.Model.FavFood;
import com.tana.foodapp.Model.PopFood;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private List<FavFood> mFavFoods = new ArrayList<>();
    private List<PopFood> mPopFood = new ArrayList<>();
    private List<Dishes> mDishes = new ArrayList<>();

    public interface FavFoodDataStatus {
        void dataIsLoaded(List<FavFood> favFoods, List<String> keys);
        void dataIsInserted();
        void dataIsUpdated();
        void dataIsDeleted();
    }

    public interface PopFoodDataStatus {
        void dataIsLoaded(List<PopFood> popFoods, List<String> keys);
        void dataIsInserted();
        void dataIsUpdated();
        void dataIsDeleted();
    }

    public interface DishesDataStatus {
        void dataIsLoaded(List<Dishes> dishes, List<String> keys);
        void dataIsInserted();
        void dataIsUpdated();
        void dataIsDeleted();
    }
    public DatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    public void readFavoriteFood(final FavFoodDataStatus favFoodDataStatus) {
        mDatabaseRef = mDatabase.getReference("favorite food");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mFavFoods.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    FavFood favorite = keyNode.getValue(FavFood.class);
                    mFavFoods.add(favorite);
                }

                favFoodDataStatus.dataIsLoaded(mFavFoods, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readPopularFood(final PopFoodDataStatus popFood) {
        mDatabaseRef = mDatabase.getReference("popular food");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mPopFood.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    PopFood popularFood = keyNode.getValue(PopFood.class);
                    mPopFood.add(popularFood);
                }

                popFood.dataIsLoaded(mPopFood, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readAllDishes(final DishesDataStatus dishes){
        mDatabaseRef = mDatabase.getReference("Dishes");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mDishes.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Dishes allDishes = keyNode.getValue(Dishes.class);
                    mDishes.add(allDishes);
                }

                dishes.dataIsLoaded(mDishes, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
