package com.unit.converter.adapters;


import android.content.Context;
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

public class ConversionsSubCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<ConversionsSubCategoriesRecyclerViewAdapter.ConversionSubCategoryItem> {

    private Context mContext;
    private List<Conversion> mConversionCategoryList;
    private IOnConversionClick mListener;

    public ConversionsSubCategoriesRecyclerViewAdapter(Context mContext, List<Conversion> mConversionCategoryList, IOnConversionClick mListener) {
        this.mContext = mContext;
        this.mConversionCategoryList = mConversionCategoryList;
        this.mListener = mListener;
    }

    @Override
    public ConversionsSubCategoriesRecyclerViewAdapter.ConversionSubCategoryItem onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_item, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_list_item, parent, false);
        return new ConversionsSubCategoriesRecyclerViewAdapter.ConversionSubCategoryItem(view);
    }

    @Override
    public void onBindViewHolder(ConversionsSubCategoriesRecyclerViewAdapter.ConversionSubCategoryItem holder, final int position) {
        holder.mSubCategoryImage.setImageResource(mConversionCategoryList.get(position).getConversionImage());
        holder.mSubCategoryName.setText(mConversionCategoryList.get(position).getConversionName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onConversionClick(mConversionCategoryList.get(position));
            }
        });
        if (position == mConversionCategoryList.size() - 1) {
            holder.mLine.setVisibility(View.GONE);
        }
        else {
            holder.mLine.setVisibility(View.VISIBLE);
        }
        /*holder.mSubCategoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onConversionClick(mConversionCategoryList.get(position));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mConversionCategoryList.size();
    }

    public class ConversionSubCategoryItem extends RecyclerView.ViewHolder{

        TextView mSubCategoryName;
        ImageView mSubCategoryImage;
        LinearLayout mLine;
        //CardView mSubCategoryCard;

        public ConversionSubCategoryItem(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mSubCategoryName = itemView.findViewById(R.id.subcategory_name);
            mSubCategoryImage = itemView.findViewById(R.id.subcategory_image);
            mLine = itemView.findViewById(R.id.line);
            //mSubCategoryCard = itemView.findViewById(R.id.subcategory_card);
        }
    }
}
