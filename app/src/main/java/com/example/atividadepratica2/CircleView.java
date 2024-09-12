package com.example.atividadepratica2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class CircleView extends View {
    private int mCircleColor;
    private final Paint circlePaint;
    private final Paint linePaint;
    private final Paint borderPaint;
    private final Paint concentricCirclePaint;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0);

        try {
            a.getInt(R.styleable.CircleView_Radius, 100);
            mCircleColor = a.getColor(R.styleable.CircleView_CircleColor, 0xFF0000FF);
        } finally {
            a.recycle();
        }

        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(mCircleColor);

        linePaint = new Paint();
        linePaint.setColor(0xFF000000);
        linePaint.setStrokeWidth(5);

        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(0xFF000000);
        borderPaint.setStrokeWidth(10);

        concentricCirclePaint = new Paint();
        concentricCirclePaint.setStyle(Paint.Style.STROKE);
        concentricCirclePaint.setColor(0xFF000000);
        concentricCirclePaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int radius = Math.min(centerX, centerY) - 20;

        canvas.drawCircle(centerX, centerY, radius, circlePaint);
        canvas.drawCircle(centerX, centerY, radius, borderPaint);

        for (int i = 1; i <= 3; i++) {
            int smallerRadius = radius - (i * (radius / 4)); // Círculos de raio decrescente
            canvas.drawCircle(centerX, centerY, smallerRadius, concentricCirclePaint);
        }

        canvas.drawLine(centerX - radius, centerY, centerX + radius, centerY, linePaint); // Linha horizontal
        canvas.drawLine(centerX, centerY - radius, centerX, centerY + radius, linePaint); // Linha vertical
    }

    public void setCircleColor(int color) {
        mCircleColor = color;
        circlePaint.setColor(mCircleColor);
        invalidate();
    }

    public void setDefaultColor() {
        mCircleColor = 0xFF0000FF;
        circlePaint.setColor(mCircleColor);
        invalidate();
    }
}
