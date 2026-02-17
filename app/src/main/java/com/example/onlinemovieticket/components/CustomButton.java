package com.example.onlinemovieticket.components;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import com.example.onlinemovieticket.R;

public class CustomButton extends AppCompatButton {

    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(25f);
        drawable.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark));
        setBackground(drawable);
        setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        setPadding(40, 30, 40, 30);
        setAllCaps(false);
    }
}