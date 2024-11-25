package dev.tomco.simplegraph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GraphView extends View {

    private Paint paint;
    Context context;

    float width = 200;
    float height = 240;

    private float SIZE = 48f;
    private float MIN = 0f;
    private float MAX = 40f;

    Deque<Float> data;

    public GraphView(Context context) {
        super(context);
        initGraph(context);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.GraphView);
        SIZE = typedArray.getInt(R.styleable.GraphView_size, 48);
        initGraph(context);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.GraphView);
        SIZE = typedArray.getInt(R.styleable.GraphView_size, 48);
        initGraph(context);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.GraphView);
        SIZE = typedArray.getInt(R.styleable.GraphView_size, 48);
        initGraph(context);
    }

    public void setRanges(int min, int max) {
        MIN = min;
        MAX = max;
        invalidate();
    }

    public void setNumOfPoints(int size) {
        SIZE = size;
        data = new LinkedList<>();
    }

    public void refreshData(Deque<Float> newData) {
        this.data = newData;
        float min = newData.getFirst();
        float max = newData.getFirst();

        for (Float f : data) {
            max = Math.max(max, f);
            min = Math.min(min, f);
        }

        float padding = (max - min) / 10;
        MAX = max + padding;
        MIN = min - padding;

        invalidate();
    }

    public void addPoint(float val) {
        data.pollFirst();
        data.add(val);

        invalidate();
    }

    public void initGraph(Context context) {
        this.paint = new Paint();
        this.context = context;

        this.paint.setColor(Color.GRAY);

        data = new LinkedList<>();

        for (int i = 0; i < SIZE; i++) {
            data.add(0f);
        }
    }

    private float[] point(float x, float y, float xpad, float ypad) {
        return new float[]{
                xpad + (width / (SIZE - 1)) * x,
                ypad + height - (height / ((MAX - MIN) - 1)) * (y - MIN)
        };
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        float xPadding = (float) (getPaddingRight() + getPaddingLeft());
        float yPadding = (float) (getPaddingTop() + getPaddingBottom());

        width = getWidth() - (2 * xPadding);
        height = getHeight() - (2 * yPadding);

        paint.setColor(Color.GRAY);
        paint.setTextSize(50);

        float[] xStopPointsLine = new float[(int) SIZE];
        float[] yStopPointsLine = new float[(int) SIZE];

        int runningIndex = 0;
        for (float value : data) {
            float[] point = point(runningIndex, value, xPadding, yPadding);
            xStopPointsLine[runningIndex] = point[0];
            yStopPointsLine[runningIndex] = point[1];
            runningIndex++;
        }

        final float POINT_RADIUS = 4f;
        final float LINE_WIDTH = 2f;

        for (int i = 0; i < yStopPointsLine.length; i++) {
            paint.setColor(Color.GRAY);
            paint.setStrokeWidth(LINE_WIDTH);



            if (i == 0) {
                paint.setColor(yStopPointsLine[i+1]- yStopPointsLine[i] < 0 ? Color.GREEN: Color.RED);
                canvas.drawLine(xStopPointsLine[i], yStopPointsLine[i], xStopPointsLine[i + 1], yStopPointsLine[i + 1], paint);
                paint.setColor(Color.BLACK);
                canvas.drawCircle(xStopPointsLine[i], yStopPointsLine[i], POINT_RADIUS, paint);
            } else if (i > 0 && i < yStopPointsLine.length - 1) {
                paint.setColor(yStopPointsLine[i+1]- yStopPointsLine[i] < 0 ? Color.GREEN: Color.RED);
                canvas.drawLine(xStopPointsLine[i], yStopPointsLine[i], xStopPointsLine[i + 1], yStopPointsLine[i + 1], paint);
                paint.setColor(Color.BLACK);
                canvas.drawCircle(xStopPointsLine[i], yStopPointsLine[i], POINT_RADIUS, paint);
            } else if (i == yStopPointsLine.length - 1) {
                paint.setColor(Color.BLACK);
                canvas.drawCircle(xStopPointsLine[i], yStopPointsLine[i], POINT_RADIUS, paint);
            }
        }
    }
}

