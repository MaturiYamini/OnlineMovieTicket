package com.example.onlinemovieticket.components;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;

public class PaymentCard extends LinearLayout {

    public PaymentCard(Context context) {
        super(context);
        init();
    }

    public PaymentCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaymentCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(15f);
        
        // Create gradient effect
        int[] colors = {
            ContextCompat.getColor(getContext(), android.R.color.holo_blue_light),
            ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark)
        };
        drawable.setColors(colors);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        
        setBackground(drawable);
        setPadding(30, 25, 30, 25);
        setElevation(10f);
    }
}