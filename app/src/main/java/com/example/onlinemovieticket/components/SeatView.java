package com.example.onlinemovieticket.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SeatView extends View {
    private Paint paint;
    private boolean isSelected = false;
    private boolean isBooked = false;

    public SeatView(Context context) {
        super(context);
        init();
    }

    public SeatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SeatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 3;
        
        if (isBooked) {
            paint.setColor(Color.RED);
        } else if (isSelected) {
            paint.setColor(Color.GREEN);
        } else {
            paint.setColor(Color.LTGRAY);
        }
        
        // Draw seat back
        canvas.drawRect(width * 0.2f, height * 0.1f, width * 0.8f, height * 0.6f, paint);
        
        // Draw seat base
        canvas.drawRect(width * 0.1f, height * 0.6f, width * 0.9f, height * 0.9f, paint);
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        invalidate();
    }

    public void setBooked(boolean booked) {
        this.isBooked = booked;
        invalidate();
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isBooked() {
        return isBooked;
    }
}