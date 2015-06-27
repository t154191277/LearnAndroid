package wmlove.learnandroid.CostomizedView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2015/6/8.
 */
public class ExtendFrameLayout extends FrameLayout implements View.OnClickListener,View.OnTouchListener{
    private List<ExtendLinearlayout> linearlayouts = new ArrayList<ExtendLinearlayout>();
    private boolean flag = true;
    private int cnt = 6;
    private GestureDetector mGestureDetector;

    public ExtendFrameLayout(Context context) {
        super(context);
    }

    public ExtendFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View root = getdata(context);
        addView(root);
        mGestureDetector = new GestureDetector(context,new MyGestureDetectorListener());
        setOnTouchListener(this);
        Log.i("TAG", "childcount=" + getChildCount());
    }

    public void initdata(){
        cnt--;
        removeAllViews();
        addView(getdata(getContext()));
    }

    private class MyGestureDetectorListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("TAG","onDown");
            return super.onDown(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("TAG","onLongPress");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("TAG","onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG","onDoubleTap");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("TAG","onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("TAG","onFling");
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG","onSingleTapConfirmed");
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("TAG","onSingleTapUp");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("TAG","onShowPress");
            super.onShowPress(e);
        }

    }

    private View getdata(Context context) {
        LinearLayout root = new LinearLayout(context);
        root.setOrientation(LinearLayout.VERTICAL);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(lp);
        Log.i("TAG", cnt+"");
        for(int i=0;i<cnt;i++){
            ExtendLinearlayout el = new ExtendLinearlayout(context,null);
            lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            el.setLayoutParams(lp);
            Log.i("TAG", 100 + i * 50 + "");
            el.setOrientation(LinearLayout.HORIZONTAL);
            linearlayouts.add(el);
            root.addView(el);
        }
        return root;
    }


    public ExtendFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    public void onClick(View view) {
        if(view instanceof ExtendLinearlayout){
            for(ExtendLinearlayout other : linearlayouts){
                for(ExtendTextView e : other.getTvs()){
                    e.setSelected(false);
                }
            }
            ExtendLinearlayout el = (ExtendLinearlayout) view;
            el.setSelected(true);
            postInvalidate();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("TAG","onTouch");
        return mGestureDetector.onTouchEvent(motionEvent);
    }
}
