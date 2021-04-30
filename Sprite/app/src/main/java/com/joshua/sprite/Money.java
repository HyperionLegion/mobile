package com.joshua.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.KeyEvent;

public class Money extends RectF{
    private int dX, dY, color;
    private Bitmap bitmap;
    private int currentFrame=0, iconWidth, iconHeight, animationDelay=20;
    private static final int DOWN=0, LEFT=1, RIGHT=2, UP=3;
    private static final int BMP_COLUMNS = 4;
    private static final int BMP_ROWS = 4;
    public Money(float left, float top, float right, float bottom, int dX, int dY, int color) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }


    public Money(float left, float top, float right, float bottom) {
        this(left, top, right, bottom, 1, 1, Color.MAGENTA);


    }

    public Money(int dX, int dY, int color) {
        this(1, 1, 110, 110, dX, dY, color);
    }

    public Money() {
        this(0, 0, Color.MAGENTA);
    }

    public void draw(Canvas c){
            Paint paint = new Paint();
            paint.setColor(this.color);//sets its color
            c.drawCircle(centerX(), centerY(), width() / 2, paint);//draws circle
    }
}
