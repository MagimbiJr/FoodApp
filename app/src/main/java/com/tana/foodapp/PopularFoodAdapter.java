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
import com.tana.foodapp.Model.PopFood;

import java.util.List;


public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.PopularFoodHolder> {
    private final Context mContext;
    private final List<PopFood> mPopFood;
    private final List<String> mKeys;

    public PopularFoodAdapter(Context mContext, List<PopFood> mPopFood, List<String> mKeys) {
        this.mContext = mContext;
        this.mPopFood = mPopFood;
        this.mKeys = mKeys;
    }

    @NonNull
    @Override
    public PopularFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_popular_item, parent, false);
        return new PopularFoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularFoodHolder holder, int position) {
        holder.bind(mPopFood.get(position), mKeys.get(position));
    }

    @Override
    public int getItemCount() {
        return mPopFood.size();
    }

    public class PopularFoodHolder extends RecyclerView.ViewHolder {
        public TextView mName, mType, mDescription, mPrice;
        public ImageView mFoodImage;
        public String key;

        public PopularFoodHolder(@NonNull View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.popular_food_name);
            mType = (TextView) itemView.findViewById(R.id.popular_food_type);
            mDescription = (TextView) itemView.findViewById(R.id.popular_food_desc);
            mPrice = (TextView) itemView.findViewById(R.id.popular_food_price);
            mFoodImage = (ImageView) itemView.findViewById(R.id.popular_food_image);
        }

        public void bind(PopFood popFood, String key) {
            mName.setText(popFood.getName());
            mType.setText(popFood.getType());
            mDescription.setText(popFood.getDescription());
            mPrice.setText(popFood.getPrice());
            Glide.with(mContext).load(popFood.getImage_url()).into(mFoodImage);
            this.key = key;
        }
    }
}
