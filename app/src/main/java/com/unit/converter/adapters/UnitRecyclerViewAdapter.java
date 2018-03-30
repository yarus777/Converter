package com.unit.converter.adapters;


import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unit.converter.R;
import com.unit.converter.conversions.Unit;
import com.unit.converter.interfaces.IOnEditWasChanged;
import com.unit.converter.sharedpreferences.Preferences;
import com.unit.converter.views.MyKeyboardView;

import java.util.List;

public class UnitRecyclerViewAdapter extends RecyclerView.Adapter<UnitRecyclerViewAdapter.UnitItem> implements MyKeyboardView.IOnKeyboardListener {

    private Context mContext;
    private List<Unit> mUnits;
    private IOnEditWasChanged mListener;
    private List<String> mResults;
    private MyKeyboardView mKeyboardView;

    private boolean onBind;
    private int mPosition = -1;
    private boolean isUpdating;
    private EditText unitEdit;

    MyTouchListener touchListener;


    public UnitRecyclerViewAdapter(Context mContext, List<Unit> mUnits, IOnEditWasChanged mListener, List<String> mResults, MyKeyboardView mKeyboardView) {
        this.mContext = mContext;
        this.mUnits = mUnits;
        this.mListener = mListener;
        this.mResults = mResults;
        this.mKeyboardView = mKeyboardView;
        mKeyboardView.setIOnKeyboardListener(this);
    }

    @Override
    public UnitItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit_item, parent, false);
        return new UnitRecyclerViewAdapter.UnitItem(view);
    }

    @Override
    public void onBindViewHolder(UnitItem holder, final int position) {

        holder.mUnitName.setText(mUnits.get(position).getUnitName());
        onBind = true;
        touchListener = new MyTouchListener(holder.mUnitEdit);
        holder.mUnitEdit.setOnTouchListener(touchListener);
        holder.mUnitEdit.setTag(position);
        disableSoftInputFromAppearing(holder.mUnitEdit);

        if (mResults.size() == mUnits.size()) {
            isUpdating = true;
            holder.mUnitEdit.setText(mResults.get(position));
            holder.mUnitEdit.setSelection(holder.mUnitEdit.getText().length());

        }

        if (position == mPosition) {
            holder.mUnitEdit.requestFocus();
            unitEdit = holder.mUnitEdit;
            mKeyboardView.setCursorPosition(holder.mUnitEdit.getText().length());
        }
        onBind = false;
    }

    private void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
            editText.setShowSoftInputOnFocus(false);
        } else { // API 11-20
            editText.setTextIsSelectable(true);
        }
    }


    public void update(int position, int lastPosition) {
        //mPosition = position;
        mPosition = lastPosition;
        if (!onBind) {
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        return mUnits.size();
    }

    public void setListener(int lastPosition) {
        mPosition = lastPosition;
        isUpdating = false;
        /*if (unitEdit != null) {
            unitEdit.requestFocus();
        }*/
    }

    @Override
    public void onInsertKeyEvent(String text, int position) {
        if (unitEdit != null) {
            String symbol = Preferences.getInstance(mContext).getDecimalSeparator();
            String str = unitEdit.getText().toString();
            if (text.equals("-")) {
                position = 0;
            }
            if (str.contains("-") && position == 0 && !text.equals("-")) {
                position = 1;
            }
            if (/*str.contains("-") && (text.equals("-") ||*/ (position < 1 && text.equals(symbol)))/*)*/ {

            } else if ((str.contains(symbol) || position == 0) && text.equals(symbol)) {

            }
            else if (str.contains("-") && text.equals("-")) {
                unitEdit.setText(str.substring(1));
            }
            else {
                String str1 = str.substring(0, position);
                String str2 = str.substring(position);
                unitEdit.setText(str1 + text + str2);
            }

        }

    }

    @Override
    public void onDeleteKeyEvent(int position) {
        if (unitEdit != null) {
            if (position > 0) {
                unitEdit.getText().delete(position - 1, position);
            }
        }
    }

    @Override
    public void onCleanKeyEvent() {
        if (unitEdit != null) {
            unitEdit.getText().clear();
        }
    }

    class UnitItem extends RecyclerView.ViewHolder {

        TextView mUnitName;
        EditText mUnitEdit;
        MyTextWatcher textWatcher;

        UnitItem(View itemView) {
            super(itemView);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mUnitName = itemView.findViewById(R.id.unit_name);
            mUnitEdit = itemView.findViewById(R.id.unit_edit);
            textWatcher = new MyTextWatcher(mUnitEdit);
            mUnitEdit.addTextChangedListener(textWatcher);
        }
    }

    class MyTextWatcher implements TextWatcher {
        private EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            int position = (int) editText.getTag();
            if (!isUpdating) {
                mListener.onEditWasChanged(position, s.toString());
                mKeyboardView.setCursorPosition(editText.getText().length());
            }
        }
    }

    class MyTouchListener implements View.OnTouchListener {

        private EditText editText;

        public MyTouchListener(EditText editText) {
            this.editText = editText;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    unitEdit = editText;
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    int touchPosition = editText.getOffsetForPosition(x, y);
                    mKeyboardView.setCursorPosition(touchPosition);
                    mListener.onEditWasTouched((int) editText.getTag());
                    break;
            }
            return false;

        }
    }


}



