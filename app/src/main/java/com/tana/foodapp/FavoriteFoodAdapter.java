package com.tana.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tana.foodapp.Model.Favorite;

import java.util.List;

public class FavoriteFoodAdapter extends RecyclerView.Adapter<FavoriteFoodAdapter.FavoriteFoodHolder> {
    private final Context mContext;
    private final List<Favorite> mFavoriteFood;
    private final List<String> mKeys;

    public FavoriteFoodAdapter(Context mContext, List<Favorite> mFavoriteFood, List<String> mKeys) {
        this.mContext = mContext;
        this.mFavoriteFood = mFavoriteFood;
        this.mKeys = mKeys;
    }

    @NonNull
    @Override
    public FavoriteFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_top_items, parent, false);
        return new FavoriteFoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFoodHolder holder, int position) {
        holder.bind(mFavoriteFood.get(position), mKeys.get(position));
    }

    @Override
    public int getItemCount() {
        return mFavoriteFood.size();
    }

    public class FavoriteFoodHolder extends RecyclerView.ViewHolder {
        public TextView mFoodTitle, mItemAmount;
        public ImageView mFoodImage;
        public String key;

        public FavoriteFoodHolder(@NonNull View itemView) {
            super(itemView);
            mFoodTitle = (TextView) itemView.findViewById(R.id.favorite_food_title);
            mItemAmount = (TextView) itemView.findViewById(R.id.favirite_food_amount);
            mFoodImage = (ImageView) itemView.findViewById(R.id.food_image);
        }

        public void bind(Favorite favoriteFood, String key) {
            mFoodTitle.setText(favoriteFood.getName());
            mItemAmount.setText(favoriteFood.getAmount());
            Glide.with(mContext).load(favoriteFood.getImage_url()).into(mFoodImage);
            this.key = key;

        }
    }
}
