package com.example.tuner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class SemiCircleView extends View {

    private Paint mPaint;
    private Path mPath;

    public SemiCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Draw points
        mPaint.setStyle(Paint.Style.FILL);

        // Top point (Green)
        mPaint.setColor(Color.GREEN);
        mPath.reset();
        mPath.moveTo(width / 2, height / 8 - 10);
        mPath.lineTo(width / 2 - 10, height / 8 + 10);
        mPath.lineTo(width / 2 + 10, height / 8 + 10);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        // Left point (Red)
        mPaint.setColor(Color.RED);
        canvas.drawCircle(40, height-80, 10, mPaint);

        // Right point (Red)
        mPaint.setColor(Color.RED);
        canvas.drawCircle(width-40, height-80, 10, mPaint);
    }
}
