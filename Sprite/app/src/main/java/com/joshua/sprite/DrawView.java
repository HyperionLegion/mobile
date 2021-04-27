package com.joshua.sprite;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite;
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite = new Sprite();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sprite.update();
        sprite.draw(canvas);
        invalidate();
    }

}
