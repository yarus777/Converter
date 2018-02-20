package com.unit.converter.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import com.unit.converter.R;
import com.unit.converter.sharedpreferences.Preferences;

import java.util.List;

public class MyKeyboardView extends KeyboardView implements KeyboardView.OnKeyboardActionListener{

    private int mDeleteWidth;
    private int mDeleteHeight;
    private int mDeleteBackgroundColor;
    private Drawable mDeleteDrawable;
    private Rect mDeleteDrawRect;
    private static final int KEYCODE_EMPTY = -10;
    private static final int KEYCODE_CLEAN = -7;

    private IOnKeyboardListener mOnKeyboardListener;
    private int mCursorPosition;

    public void setCursorPosition(int mCursorPosition) {
        this.mCursorPosition = mCursorPosition;
    }

    public interface IOnKeyboardListener {

        void onInsertKeyEvent(String text, int position);

        void onDeleteKeyEvent(int position);

        void onCleanKeyEvent();
    }

    public MyKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MyKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XNumberKeyboardView,
                defStyleAttr, 0);
        mDeleteDrawable = a.getDrawable(R.styleable.XNumberKeyboardView_xnkv_deleteDrawable);
        mDeleteBackgroundColor = a.getColor(
                R.styleable.XNumberKeyboardView_xnkv_deleteBackgroundColor, Color.TRANSPARENT);
        mDeleteWidth = a.getDimensionPixelOffset(R.styleable.XNumberKeyboardView_xnkv_deleteWidth,
                -1);
        mDeleteHeight = a.getDimensionPixelOffset(R.styleable.XNumberKeyboardView_xnkv_deleteHeight,
                -1);
        a.recycle();

        Keyboard keyboard;
        keyboard = Preferences.getInstance(context).getDecimalSeparator().equals(".") ? new Keyboard(context, R.xml.keyboard_number) : new Keyboard(context, R.xml.keyboard_comma);
        setKeyboard(keyboard);

        setEnabled(true);
        setPreviewEnabled(false);
        setOnKeyboardActionListener(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {

            if (key.codes[0] == KEYCODE_EMPTY) {
                drawKeyBackground(key, canvas, mDeleteBackgroundColor);
            }

            else if (key.codes[0] == Keyboard.KEYCODE_DELETE) {
                //drawKeyBackground(key, canvas, mDeleteBackgroundColor);
                //drawDeleteButton(key, canvas);
            }
        }
    }


    private void drawKeyBackground(Keyboard.Key key, Canvas canvas, int color) {
        ColorDrawable drawable = new ColorDrawable(color);
        drawable.setBounds(key.x, key.y+key.gap, key.x + key.width, key.y + key.height+2*key.gap);
        drawable.draw(canvas);

    }

    private void drawDeleteButton(Keyboard.Key key, Canvas canvas) {
        if (mDeleteDrawable == null) {
            return;
        }

        if (mDeleteDrawRect == null || mDeleteDrawRect.isEmpty()) {
            int drawWidth, drawHeight;
            int intrinsicWidth = mDeleteDrawable.getIntrinsicWidth();
            int intrinsicHeight = mDeleteDrawable.getIntrinsicHeight();

            if (mDeleteWidth > 0 && mDeleteHeight > 0) {
                drawWidth = mDeleteWidth;
                drawHeight = mDeleteHeight;
            } else if (mDeleteWidth > 0 && mDeleteHeight <= 0) {
                drawWidth = mDeleteWidth;
                drawHeight = drawWidth * intrinsicHeight / intrinsicWidth;
            } else if (mDeleteWidth <= 0 && mDeleteHeight > 0) {
                drawHeight = mDeleteHeight;
                drawWidth = drawHeight * intrinsicWidth / intrinsicHeight;
            } else {
                drawWidth = intrinsicWidth;
                drawHeight = intrinsicHeight;
            }

            if (drawWidth > key.width) {
                drawWidth = key.width;
                drawHeight = drawWidth * intrinsicHeight / intrinsicWidth;
            }
            if (drawHeight > key.height) {
                drawHeight = key.height;
                drawWidth = drawHeight * intrinsicWidth / intrinsicHeight;
            }

            int left = key.x + (key.width - drawWidth) / 2;
            int top = key.y + (key.height - drawHeight) / 2;
            mDeleteDrawRect = new Rect(left, top, left + drawWidth, top + drawHeight);
        }

        if (mDeleteDrawRect != null && !mDeleteDrawRect.isEmpty()) {
            mDeleteDrawable.setBounds(mDeleteDrawRect.left, mDeleteDrawRect.top,
                    mDeleteDrawRect.right, mDeleteDrawRect.bottom);
            mDeleteDrawable.draw(canvas);
        }
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        if (primaryCode == Keyboard.KEYCODE_DELETE) {
            if (mOnKeyboardListener != null)
                mOnKeyboardListener.onDeleteKeyEvent(mCursorPosition);
        }
        else if (primaryCode == KEYCODE_CLEAN) {
            mOnKeyboardListener.onCleanKeyEvent();
        }
        else if (primaryCode != KEYCODE_EMPTY) {
            if (mOnKeyboardListener != null) {
                mOnKeyboardListener.onInsertKeyEvent(Character.toString(
                        (char) primaryCode), mCursorPosition);
            }
        }

    }

    public void setIOnKeyboardListener(IOnKeyboardListener listener) {
        this.mOnKeyboardListener = listener;
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }


    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
