package com.tana.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tana.foodapp.Model.Dishes;

import java.util.List;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.DishesHolder> {
    private final Context mContext;
    private final List<Dishes> mDish;
    private final List<String> mKey;

    public DishesAdapter(Context mContext, List<Dishes> mDish, List<String> mKey) {
        this.mContext = mContext;
        this.mDish = mDish;
        this.mKey = mKey;
    }

    @NonNull
    @Override
    public DishesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_dishes_items, parent, false);
        return new DishesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DishesHolder holder, int position) {
        holder.bind(mDish.get(position), mKey.get(position));
    }

    @Override
    public int getItemCount() {
        return mDish.size();
    }

    public class DishesHolder extends RecyclerView.ViewHolder {
        public ImageView mDishImage;
        public String key;

        public DishesHolder(@NonNull View itemView) {
            super(itemView);
            mDishImage = (ImageView) itemView.findViewById(R.id.dishes_images);
        }

        public void bind(Dishes dish, String key) {
            Glide.with(mContext).load(dish.getImage_url()).into(mDishImage);
            this.key = key;
        }
    }
}
