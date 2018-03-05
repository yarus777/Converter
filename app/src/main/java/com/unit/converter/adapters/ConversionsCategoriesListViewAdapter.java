package com.unit.converter.adapters;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unit.converter.R;
import com.unit.converter.conversions.Conversions;
import com.unit.converter.interfaces.IOnConversionClick;
import com.unit.converter.utils.ShowHideViewAnimator;

public class ConversionsCategoriesListViewAdapter extends BaseAdapter{

    private Context mContext;
    private Conversions mConversions;
    private IOnConversionClick mListener;
    private LayoutInflater lInflater;
    private SparseBooleanArray mLayoutStateSparseBooleanArray = new SparseBooleanArray();

    public ConversionsCategoriesListViewAdapter(Context mContext, Conversions mConversions, IOnConversionClick mListener ) {
        this.mContext = mContext;
        this.mConversions = mConversions;
        this.mListener = mListener;
        lInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < mConversions.getSize(); i++) {
            mLayoutStateSparseBooleanArray.append(i, false);
        }
    }

    @Override
    public int getCount() {
        return mConversions.getSize();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.category_list_item, viewGroup, false);
        }
        TextView mCategoryName = view.findViewById(R.id.category_name);
        ImageView mCategoryImage = view.findViewById(R.id.category_image);
        final RecyclerView mSubcategoryRecyclerView = view.findViewById(R.id.subcategory_recycler_view);
        final ImageView mArrow = view.findViewById(R.id.drop_arrow);
        final LinearLayout mAnimationLayout = view.findViewById(R.id.animation_layout);
        mCategoryName.setText(mConversions.getById(position).getConversionCategoryName());
        mCategoryImage.setImageResource(mConversions.getById(position).getConversionCategoryIcon());
        ConversionsSubCategoriesRecyclerViewAdapter mConversionsSubCategoriesRecyclerViewAdapter = new ConversionsSubCategoriesRecyclerViewAdapter(mContext, mConversions.getById(position).getConversionCategoryList(), mListener);
        mSubcategoryRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mSubcategoryRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mSubcategoryRecyclerView.setAdapter(mConversionsSubCategoriesRecyclerViewAdapter);
        mSubcategoryRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mSubcategoryRecyclerView.getMeasuredHeight() > 0) {
                    mSubcategoryRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mSubcategoryRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mSubcategoryRecyclerView.getMeasuredHeight()));
                }
            }
        });
        if (mLayoutStateSparseBooleanArray.get(position)) {
            mAnimationLayout.setVisibility(View.VISIBLE);
        } else {
            mAnimationLayout.setVisibility(View.GONE);
        }
        final ShowHideViewAnimator animator = new ShowHideViewAnimator(new ShowHideViewAnimator.OnAnimationChangeState() {
            @Override
            public void onAnimationEnd(boolean isHide) {
                mLayoutStateSparseBooleanArray.put(position, isHide);
            }

            @Override
            public void onAnimationStart(boolean isHiding) {
            }
        });
        view.post(new Runnable() {
            @Override
            public void run() {
                animator.setAnimatedView(mAnimationLayout)
                        .initAllData(ShowHideViewAnimator.Orientation.VERTICAL, ShowHideViewAnimator.AnimationType.SIZE_AND_ALPHA, 200L)
                        .setAnimationDirection(ShowHideViewAnimator.AnimationDirection.TOP);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animator.startAnimation(mLayoutStateSparseBooleanArray.get(position));
                if (mLayoutStateSparseBooleanArray.get(position)) {
                    mArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_arrow_drop_down));
                    mArrow.setColorFilter(mContext.getResources().getColor(R.color.arrow_color));
                }
                else {
                    mArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_arrow_drop_up));
                    mArrow.setColorFilter(mContext.getResources().getColor(R.color.icons_color));
                }
            }
        });
        return view;
    }
}
