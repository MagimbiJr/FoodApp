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
import com.tana.foodapp.Model.FavFood;

import java.util.List;

public class FavoriteFoodAdapter extends RecyclerView.Adapter<FavoriteFoodAdapter.FavoriteFoodHolder> {
    private final Context mContext;
    private final List<FavFood> mFavoriteFavFood;
    private final List<String> mKeys;

    public FavoriteFoodAdapter(Context mContext, List<FavFood> mFavoriteFavFood, List<String> mKeys) {
        this.mContext = mContext;
        this.mFavoriteFavFood = mFavoriteFavFood;
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
        holder.bind(mFavoriteFavFood.get(position), mKeys.get(position));
    }

    @Override
    public int getItemCount() {
        return mFavoriteFavFood.size();
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

        public void bind(FavFood favoriteFavFood, String key) {
            mFoodTitle.setText(favoriteFavFood.getName());
            mItemAmount.setText(favoriteFavFood.getAmount());
            Glide.with(mContext).load(favoriteFavFood.getImage_url()).into(mFoodImage);
            this.key = key;

        }
    }
}
