package com.example.myhub.mvvm.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.myhub.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;


/**
 * Time:2020/1/14 20:26
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class MaskedCardView extends MaterialCardView {

    private ShapeAppearancePathProvider pathProvider;
    private Path path;
    private ShapeAppearanceModel shapeAppearanceModel;
    private RectF rectF;

    public MaskedCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs, defStyleAttr);
    }

    public MaskedCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs, R.attr.materialCardViewStyle);
    }

    private void initData(Context context, AttributeSet attrs, int defStyleAttr) {
        pathProvider = new ShapeAppearancePathProvider();
        path = new Path();
        shapeAppearanceModel = new ShapeAppearanceModel(context, attrs, defStyleAttr, R.style.Widget_MaterialComponents_CardView);
        rectF = new RectF(0f, 0f, 0f, 0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF.right = (float) w;
        rectF.bottom = (float) h;
        pathProvider.calculatePath(shapeAppearanceModel, 1f, rectF, path);
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
