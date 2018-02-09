package com.example.chris.coursework.common.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Chris on 09/02/2018.
 */

public class Rectangle extends View {

    private float left, top, right, bottom;

    private Paint paintSettings;

    public Rectangle(Context context, float left, float top, float right, float bottom) {
        super(context);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Rectangle(Context context, float left, float top, float right, float bottom, Paint paintSettings) {
        this(context, left, top, right, bottom);
        this.paintSettings = paintSettings;
    }

    public Paint getPaintSettings() {
        return paintSettings;
    }

    public void setPaintSettings(Paint paintSettings) {
        this.paintSettings = paintSettings;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(paintSettings != null) {
            canvas.drawRect(left, top, right, bottom, paintSettings);
        }
    }
}
