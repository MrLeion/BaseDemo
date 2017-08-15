package org.tzl.basedemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Gravity;

import org.tzl.basedemo.R;
import org.tzl.baselibrary.utils.L;

/**
 * author: tangzenglei
 * created on: 2017/8/9 下午11:25
 * description:
 */

public class LeanTextView extends android.support.v7.widget.AppCompatTextView {
    public static final int LEFT_TOP     = 0;
    private static final int LEFT_BOTTOM = 2;
    public static final int RIGHT_TOP = 1;
    public static final int RIGHT_BOTTOM = 3;
    //    public int getmDegrees() {
//        return mDegrees;
//    }
//
//    public void setmDegrees(int mDegrees) {
//        this.mDegrees = mDegrees;
//        invalidate();
//    }

    private int orientation;
    private int background_color;


    public LeanTextView(Context context) {
        super(context, null);
    }

    public LeanTextView(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.textViewStyle);
        this.setGravity(Gravity.CENTER);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LeanTextView);
        orientation = a.getInt(R.styleable.LeanTextView_orientation, LEFT_TOP);
        background_color = a.getColor(R.styleable.LeanTextView_background_color, Color.BLACK);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }




    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        Paint paint = new Paint();
        paint.setColor(background_color);
        // 设置画笔的锯齿效果
        paint.setAntiAlias(false);
        Path path = new Path();


        switch (orientation) {
            case LEFT_TOP://左上

                path.moveTo(this.getLeft(), 0);
                path.lineTo(this.getLeft()+this.getWidth(),0);
                path.lineTo(this.getLeft(), this.getHeight());
                path.close();
                canvas.drawPath(path,paint);
                canvas.rotate(-45, this.getWidth() / 4f, this.getHeight() / 2f);
                break;
            case RIGHT_TOP://右上

                path.moveTo(this.getLeft(), 0);
                path.lineTo(this.getLeft()+this.getWidth(),0);
                path.lineTo(this.getLeft()+this.getWidth(), this.getHeight());
                path.close();
                canvas.drawPath(path,paint);
                canvas.rotate(45, this.getWidth() *3/ 4f, this.getHeight() / 2f);
                L.e("width:"+this.getWidth()+"height:"+this.getHeight()+"left:"+this.getLeft()+"top:"+this.getTop());
                break;
            case LEFT_BOTTOM://左下
                path.moveTo(this.getLeft(), 0);
                path.lineTo(this.getLeft()+this.getWidth(),this.getHeight());
                path.lineTo(this.getLeft(), this.getHeight());
                path.close();
                canvas.drawPath(path,paint);
                canvas.rotate(-135, this.getWidth() /2f, this.getHeight()*2/3f);
                break;
            case RIGHT_BOTTOM://右下
                path.moveTo(this.getLeft()+getWidth(),0 );
                path.lineTo(this.getLeft()+this.getWidth(),this.getHeight());
                path.lineTo(this.getLeft(), this.getHeight());
                path.close();
                canvas.drawPath(path,paint);
                canvas.rotate(-215, this.getWidth()/2f, this.getHeight()*2/3f);
                break;
        }



        // 三角形
        super.onDraw(canvas);
        canvas.restore();
    }

}

