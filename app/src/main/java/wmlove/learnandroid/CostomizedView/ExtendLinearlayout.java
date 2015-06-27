package wmlove.learnandroid.CostomizedView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Administrator on 2015/6/7.
 */
public class ExtendLinearlayout extends LinearLayout implements View.OnClickListener{
    private List<ExtendTextView> tvs = new ArrayList<ExtendTextView>();
    private int [] color = {Color.RED,Color.BLACK,Color.BLUE,Color.GRAY,Color.GREEN,Color.YELLOW,Color.WHITE};
    public ExtendLinearlayout(Context context) {
        super(context,null);
    }

    public ExtendLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExtendLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
        for(int i=0;i<7;i++){
            ExtendTextView tv = new ExtendTextView(context);
            tv.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            tv.setTextSize(20);
            tv.setText(i + "");
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(this);
            addView(tv);
            tvs.add(tv);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setOrientation(HORIZONTAL);
    }

    @Override
    public void onClick(View view) {
        if(view instanceof ExtendTextView){
            for(ExtendTextView other : tvs){
                other.setSelected(false);
            }
            ExtendTextView t = (ExtendTextView) view;
            t.setSelected(true);
            Random random = new Random();
            int colorpick = random.nextInt(6);
            t.setSelectionColor(color[colorpick]);
            postInvalidate();
        }
    }

    public List<ExtendTextView> getTvs() {
        return tvs;
    }
}
