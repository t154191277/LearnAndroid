package wmlove.learnandroid.CostomizedView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2015/6/10.
 */
public class ExtendView extends View implements View.OnClickListener{
    private Paint mPaint;
    private int [] color = {Color.RED,Color.BLACK,Color.BLUE,Color.GRAY,Color.GREEN,Color.YELLOW,Color.WHITE};
    public ExtendView(Context context) {
        super(context,null);
    }

    public ExtendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        setOnClickListener(this);
    }

    public ExtendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(0,0,getWidth(),getHeight(),mPaint);
    }

    public void setBackGroud(int color){
        mPaint.setColor(color);
    }

    @Override
    public void onClick(View view) {
        Random random = new Random();
        int colorpick = random.nextInt(6);
        mPaint.setColor(color[colorpick]);
        postInvalidate();
    }
}
