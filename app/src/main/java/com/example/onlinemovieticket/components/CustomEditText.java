package com.example.onlinemovieticket.components;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

public class CustomEditText extends AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(15f);
        drawable.setStroke(2, ContextCompat.getColor(getContext(), android.R.color.darker_gray));
        drawable.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        setBackground(drawable);
        setPadding(40, 30, 40, 30);
    }
}