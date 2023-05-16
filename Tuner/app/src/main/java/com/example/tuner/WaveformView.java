package com.example.tuner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WaveformView extends View {
    private final Path wavePath = new Path();
    private Paint paint;
    private final Paint wavePaint = new Paint();
    private List<Float> audioData = new ArrayList<>();


    public WaveformView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(6f);
        paint.setAntiAlias(true);

        int startColor = Color.parseColor("#FF4081");
        int endColor = Color.parseColor("blue");
        LinearGradient gradient = new LinearGradient(0, 0, 0, getHeight(), startColor, endColor, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
    }

    public void updateAudioData(@NonNull float[] audioData, String color) {
        this.audioData.clear();
        for (float sample : audioData) {
            this.audioData.add(sample);
        }

        int startColor;
        try {
            startColor = Color.parseColor(color);
        } catch (IllegalArgumentException e) {
            startColor = Color.parseColor("#FF4081"); // Default color if the provided color is invalid
        }

        int endColor = startColor;
        LinearGradient gradient = new LinearGradient(0, 0, 0, getHeight(), startColor, endColor, Shader.TileMode.CLAMP);
        paint.setShader(gradient);

        invalidate(); // Redraw the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (audioData != null && !audioData.isEmpty()) {
            float centerX = getWidth() / 2f;
            float centerY = getHeight() / 2f;
            float radius = Math.min(centerX, centerY) - paint.getStrokeWidth();
            float angleStep = 360f / (float) audioData.size();

            for (int i = 0; i < audioData.size(); i++) {
                float angle = (float) Math.toRadians(i * angleStep);
                float x1 = centerX + (float) Math.cos(angle) * (radius - audioData.get(i) * radius);
                float y1 = centerY + (float) Math.sin(angle) * (radius - audioData.get(i) * radius);
                float x2 = centerX + (float) Math.cos(angle) * radius;
                float y2 = centerY + (float) Math.sin(angle) * radius;

                // Check if the line's y-coordinate is above the center of the view
                if (y1 < centerY) {
                    canvas.drawLine(x1, y1, x2, y2, paint);
                }
            }
        }
    }
}