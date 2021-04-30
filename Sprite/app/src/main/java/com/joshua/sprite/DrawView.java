package com.joshua.sprite;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite;
    Money money;
    public static int score=0;
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        System.out.println("KEY PRESSED");
        switch (keyCode) {
            case KeyEvent.KEYCODE_W:
                sprite.setdY(-5);;
                return true;
            case KeyEvent.KEYCODE_A:
                sprite.setdX(-5);
                return true;
            case KeyEvent.KEYCODE_S:
                sprite.setdY(5);
                return true;
            case KeyEvent.KEYCODE_D:
                sprite.setdX(5);
                return true;
            default:
                sprite.setdY(0);
                sprite.setdX(0);
                return false;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite = new Sprite();
        int l =(int)(Math.random()*(getWidth()-111));
        int t = (int)(Math.random()*(getWidth()-111));
        money = new Money(l, t, l+111, t+111);
        sprite.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.classywalk));
        sprite.grow(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sprite.update(canvas);
        sprite.draw(canvas);
        money.draw(canvas);
        if(RectF.intersects(sprite, money)){
            score++;
            int left =(int)(Math.random()*(getWidth()-111));
            int top = (int)(Math.random()*(getWidth()-111));
            money = new Money(left, top, left+111, top+111);
            System.out.println("SCORE: " + score);
            Toast toast = Toast.makeText(this.getContext(), "Score: " + score, Toast.LENGTH_LONG);
            toast.show();
        }
        invalidate();
    }

}
