package com.example.onlinemovieticket.components;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;

public class MovieCard extends LinearLayout {

    public MovieCard(Context context) {
        super(context);
        init();
    }

    public MovieCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovieCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(20f);
        drawable.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        drawable.setStroke(1, ContextCompat.getColor(getContext(), android.R.color.darker_gray));
        
        setBackground(drawable);
        setPadding(20, 20, 20, 20);
        setElevation(8f);
    }
}