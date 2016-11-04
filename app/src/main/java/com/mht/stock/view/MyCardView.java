package com.mht.stock.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;

import com.mht.stock.util.CommonUtils;
import com.mht.stock.util.Screen;

/**
 * Created by mht on 2016/6/29.
 */
public class MyCardView extends CardView {

    final static float SHADOW_MULTIPLIER = 1.5f;

    private Rect mContentPadding;
    private Drawable mRoundDrawable;

    public MyCardView(Context context) {
        super(context);
        initialize(context, null, 0);
    }

    public MyCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0);
    }

    public MyCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        if (Build.VERSION.SDK_INT < 21) {
            mRoundDrawable = makeShapeDrawable();
            mContentPadding = new Rect();
            mContentPadding.set(getContentPaddingLeft(), getContentPaddingTop(), getContentPaddingRight(), getContentPaddingBottom());
        }
    }

    @Override
    public void setContentPadding(int left, int top, int right, int bottom) {
        super.setContentPadding(left, top, right, bottom);
        if(mContentPadding != null) {
            mContentPadding.set(left, top, right, bottom);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(mRoundDrawable != null) {
            float cardElevation = (int) getCardElevation();
            mRoundDrawable.setBounds((int) (mContentPadding.left+cardElevation), (int) (mContentPadding.top+cardElevation*SHADOW_MULTIPLIER),
                    (int) (getWidth()-mContentPadding.right-cardElevation), (int) (getHeight()-mContentPadding.bottom-cardElevation*SHADOW_MULTIPLIER));
            mRoundDrawable.draw(canvas);
        }
    }

    private Drawable makeShapeDrawable() {
        float radius = getRadius();
        float[] outerR = new float[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        RectF inset = new RectF(0.1f, 0.1f, 0.1f, 0.1f);
        float[] innerR = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };
        ShapeDrawable drawable = new ShapeDrawable(new RoundRectShape(outerR, inset, innerR));
        drawable.getPaint().setColor(Color.WHITE);
        return drawable;
    }
}
