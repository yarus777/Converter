package com.unit.converter.adapters;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unit.converter.R;
import com.unit.converter.conversions.Conversions;
import com.unit.converter.interfaces.IOnConversionClick;

public class ConversionsCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<ConversionsCategoriesRecyclerViewAdapter.ConversionCategoryItem> {

    private Context mContext;
    private Conversions mConversions;
    private ConversionsSubCategoriesRecyclerViewAdapter mConversionsSubCategoriesRecyclerViewAdapter;
    private IOnConversionClick mListener;

    public ConversionsCategoriesRecyclerViewAdapter(Context mContext, Conversions mConversions, IOnConversionClick mListener ) {
        this.mContext = mContext;
        this.mConversions = mConversions;
        this.mListener = mListener;
    }

    @Override
    public ConversionCategoryItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ConversionsCategoriesRecyclerViewAdapter.ConversionCategoryItem(view);
    }

    @Override
    public void onBindViewHolder(ConversionCategoryItem holder, int position) {
        holder.mCategoryName.setText(mConversions.getById(position).getConversionCategoryName());
        holder.mCategoryImage.setImageResource(mConversions.getById(position).getConversionCategoryIcon());
        mConversionsSubCategoriesRecyclerViewAdapter = new ConversionsSubCategoriesRecyclerViewAdapter(mContext, mConversions.getById(position).getConversionCategoryList(), mListener);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.mSubcategoryRecyclerView.setLayoutManager(layoutManager);
        holder.mSubcategoryRecyclerView.setAdapter(mConversionsSubCategoriesRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return mConversions.getSize();
    }

    public class ConversionCategoryItem extends RecyclerView.ViewHolder{

        TextView mCategoryName;
        ImageView mCategoryImage;
        RecyclerView mSubcategoryRecyclerView;

        public ConversionCategoryItem(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mCategoryName = itemView.findViewById(R.id.category_name);
            mCategoryImage = itemView.findViewById(R.id.category_image);
            mSubcategoryRecyclerView = itemView.findViewById(R.id.subcategory_recycler_view);
        }
    }
}
