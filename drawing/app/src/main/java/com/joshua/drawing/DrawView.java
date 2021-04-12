package com.joshua.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    int dy = 20;
    int[] xs = new int[10];
    int[] ys = new int[10];

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for(int x =0; x<xs.length; x++){
            xs[x] = getWidth()*x/xs.length;
        }
        for(int x =0; x<ys.length; x++){
            ys[x] = getHeight()*(x)/ys.length;
        }
    }

    Paint p = new Paint();
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.BLUE);

        for(int x =0; x<xs.length; x++){
            for(int y=0; y<ys.length; y++){
                int ex = 0;
                if(x%2==0){
                    ex = 10;
                }
                canvas.drawCircle(xs[x], (ex+(float)xs.length/2-x)*5+ys[y], 10.5f, p);
                //System.out.println("value of " + x + " and " + y + ": "+ xs[x] + ys[y]);
            }
        }
        p.setColor(Color.rgb(222, 184,135));
        canvas.drawRoundRect(getWidth()*3/8f, getHeight()/4f*3, getWidth()*5/8f, getHeight()/4f, 5, 5, p);
        p.setColor(Color.GREEN);
        canvas.drawRect(0,getHeight()*2/3f,getWidth(),getHeight(),p);
        p.setColor(Color.MAGENTA);
        canvas.drawCircle(getWidth()/2f, getHeight()/4f, 150f, p);
        canvas.drawCircle(getWidth()*6/16f, getHeight()*5/16f, 150f, p);
        canvas.drawCircle(getWidth()*10/16f, getHeight()*5/16f, 150f, p);
        canvas.drawCircle(getWidth()/2f, getHeight()*5/16f, 150f, p);
        canvas.drawCircle(getWidth()*6/16f, getHeight()*5/16f, 150f, p);
        canvas.drawCircle(getWidth()*10/16f, getHeight()*5/16f, 150f, p);

        p.setColor(Color.GRAY);
        canvas.drawCircle(getWidth()*14/16f, getHeight()/16f, 125f, p);
        canvas.drawCircle(getWidth()*13/16f, getHeight()/16f, 100f, p);
        canvas.drawCircle(getWidth()*15/16f, getHeight()/16f, 100f, p);

        canvas.drawCircle(getWidth()*5/16f, getHeight()/16f, 125f, p);
        canvas.drawCircle(getWidth()*6/16f, getHeight()/16f, 100f, p);
        canvas.drawCircle(getWidth()*4/16f, getHeight()/16f, 100f, p);


        for(int y=0; y<ys.length; y++){
            ys[y] = ys[y]+dy;
            ys[y] = ys[y]%getHeight();
        }
        invalidate();
    }
}
