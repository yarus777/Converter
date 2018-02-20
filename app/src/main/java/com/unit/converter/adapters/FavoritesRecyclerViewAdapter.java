package com.unit.converter.adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unit.converter.R;
import com.unit.converter.conversions.Conversion;
import com.unit.converter.interfaces.IOnConversionClick;

import java.util.List;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.FavoriteItem> {

    private List<Conversion> mFavoriteConversions;
    private IOnConversionClick mListener;


    public FavoritesRecyclerViewAdapter(List<Conversion> mFavoriteConversions, IOnConversionClick listener) {
        this.mFavoriteConversions = mFavoriteConversions;
        this.mListener = listener;
    }

    @Override
    public FavoriteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item, parent, false);
        return new FavoritesRecyclerViewAdapter.FavoriteItem(view);
    }

    @Override
    public void onBindViewHolder(FavoriteItem holder, final int position) {

        holder.mFavoriteName.setText(mFavoriteConversions.get(position).getConversionName());
        holder.mFavoriteImage.setImageResource(mFavoriteConversions.get(position).getConversionImage());
        holder.mFavoriteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mListener.onConversionClick(mFavoriteConversions.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavoriteConversions.size();
    }

    class FavoriteItem extends RecyclerView.ViewHolder {

        TextView mFavoriteName;
        ImageView mFavoriteImage;
        CardView mFavoriteCard;

        FavoriteItem(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mFavoriteName = itemView.findViewById(R.id.favorite_name);
            mFavoriteImage = itemView.findViewById(R.id.favorite_image);
            mFavoriteCard = itemView.findViewById(R.id.favorite_card);
        }
    }
}
