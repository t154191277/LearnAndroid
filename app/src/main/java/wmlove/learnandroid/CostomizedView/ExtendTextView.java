package wmlove.learnandroid.CostomizedView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import wmlove.android_customizeview.R;

/**
 * Created by Administrator on 2015/6/7.
 */
public class ExtendTextView extends TextView implements View.OnClickListener{
    private int selectionColor = Color.BLUE;
    private boolean selected = false;
    private int [] color = {Color.RED,Color.BLACK,Color.BLUE,Color.GRAY,Color.GREEN,Color.YELLOW,Color.WHITE};
    public ExtendTextView(Context context) {
        super(context);
        setWidth(150);
        setHeight(150);
        setBackground();
        setOnClickListener(this);
//        setBackgroundResource(R.drawable.test_text);
    }

    public ExtendTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.EntendTextView);
        String color = t.getString(R.styleable.EntendTextView_color);
        Log.i("TAG", "color=" + color);
        t.recycle();
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }

    public void setSelectionColor(final int selectionColor) {
        this.selectionColor = selectionColor;
        setBackground();

    }

    public void setBackground(){
        StateListDrawable drawable = new StateListDrawable();
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int i, int i1) {
                return new LinearGradient(0,0,0,0,selectionColor,selectionColor, Shader.TileMode.REPEAT);
            }
        });
        drawable.addState(new int[] {android.R.attr.state_selected},shapeDrawable);
        setBackground(drawable);
        return;
    }

    @Override
    public void onClick(View view) {
        Random random = new Random();
        int colorpick = random.nextInt(6);
        setSelectionColor(color[colorpick]);
        Log.i("TAG","test onclick");
        postInvalidate();
    }
}
