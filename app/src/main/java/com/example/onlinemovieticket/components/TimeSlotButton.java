package com.example.onlinemovieticket.components;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

public class TimeSlotButton extends AppCompatButton {
    private boolean isSelected = false;

    public TimeSlotButton(Context context) {
        super(context);
        init();
    }

    public TimeSlotButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimeSlotButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        updateAppearance();
        setPadding(30, 20, 30, 20);
        setAllCaps(false);
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        updateAppearance();
    }

    private void updateAppearance() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(20f);
        
        if (isSelected) {
            drawable.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_green_dark));
            setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        } else {
            drawable.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
            drawable.setStroke(2, ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark));
            setTextColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark));
        }
        
        setBackground(drawable);
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }
}