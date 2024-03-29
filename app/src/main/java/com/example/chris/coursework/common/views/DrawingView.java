package com.example.chris.coursework.common.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Chris on 06/02/2018.
 */

public class DrawingView  extends View {

    private Context context;

    private Bitmap bitmap;
    private Canvas canvas;

    private Path path;
    private Paint bitMapPaint;
    private Paint paintOptions;

    private Paint pointerSettings;
    private Path pointerPath;

    private boolean disbaled = false;


    public DrawingView(Context context) {
        super(context);
        this.context = context;

        this.path = new Path();
        this.bitMapPaint = new Paint(Paint.DITHER_FLAG);

        this.pointerSettings = new Paint();
        this.pointerPath = new Path();

        this.pointerSettings.setAntiAlias(true);
        this.pointerSettings.setColor(Color.BLUE);
        this.pointerSettings.setStyle(Paint.Style.STROKE);
        this.pointerSettings.setStrokeWidth(4f);

        if(this.paintOptions == null) {
            this.paintOptions = new Paint();
        }
    }

    public DrawingView(Context context, Paint paintStyle) {
        this(context);
        this.paintOptions = paintStyle;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(this.bitmap, 0, 0, this.bitMapPaint);
        canvas.drawPath(this.path, this.paintOptions);
        canvas.drawPath(this.pointerPath, this.pointerSettings);

    }

    private float x, y;
    private static final float TOUCH_TOLERANCE = 4;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(!disbaled) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
        }
        return true;
    }

    private void touch_start(float x, float y) {
        path.reset();
        path.moveTo(x, y);
        this.x = x;
        this.y = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - this.x);
        float dy = Math.abs(y - this.y);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(this.x, this.y, (x + this.x) / 2, (y + this.y) / 2);
            this.x = x;
            this.y = y;

            pointerPath.reset();
            pointerPath.addCircle(this.x, this.y, 30, Path.Direction.CW);
        }
    }

    private void touch_up() {
        this.path.lineTo(this.x, this.y);
        this.pointerPath.reset();
        this.canvas.drawPath(this.path, this.paintOptions);
        this.path.reset();
    }

    public Paint getPaintOptions() {
        return paintOptions;
    }

    public void setPaintOptions(Paint paintOptions) {
        this.paintOptions = paintOptions;
    }

    public Paint getPointerSettings() {
        return pointerSettings;
    }

    public void setPointerSettings(Paint pointerSettings) {
        this.pointerSettings = pointerSettings;
    }

    public void enable() {
        this.disbaled = false;
    }

    public void disbale() {
        this.disbaled = true;
    }
}
