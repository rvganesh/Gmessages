package com.rvg.android.util.ui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

import com.rvg.android.util.math.MathUtil;

/**
 * A very simple view showing a graph from an array of floats.<br/>
 * The graph is automatically scaled to fit the view.
 */
public class GraphView extends View {
    private static final int DOT_WIDTH = 8;

    private static String KEY_VALUES = "KEY_VALUES";
    private static String KEY_COLOR = "KEY_COLOR";
    private static String KEY_TYPE = "KEY_TYPE";

    public static enum Type {
        LINES, POINTS,
    }

    private static class SavedState extends BaseSavedState {
        private SparseArray<Object> mValues;

        public SavedState(Parcel source) {
            super(source);
            mValues = source.readSparseArray(getClass().getClassLoader());
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeSparseArray(mValues);
        }

        @SuppressWarnings({ "unused", "hiding" })
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        public void setValues(SparseArray<?> values) {
            mValues = (SparseArray<Object>) values;
        }

        public SparseArray<?> getValues() {
            return mValues;
        }
    }

    private SparseArray<Bundle> mValues = new SparseArray<Bundle>();
    private Paint mPaint;

    public GraphView(Context context) {
        super(context);
        init();
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1 * getResources().getDisplayMetrics().density);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.setValues(mValues);

        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        mValues = (SparseArray<Bundle>) ((SavedState) state).getValues();
    }

    private Bundle getBundle(int index) {
        Bundle res = mValues.get(index);
        if (res == null) {
            res = new Bundle();
            mValues.put(index, res);
        }
        return res;
    }

    public void setValues(int index, float[] values) {
        Bundle bundle = getBundle(index);
        bundle.putFloatArray(KEY_VALUES, values);
        invalidate();
    }

    public void setType(int index, Type type) {
        Bundle bundle = getBundle(index);
        bundle.putInt(KEY_TYPE, type.ordinal());
        invalidate();
    }

    public void setColor(int index, int color) {
        Bundle bundle = getBundle(index);
        bundle.putInt(KEY_COLOR, color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int total = mValues.size();
        float lineWidth = getResources().getDisplayMetrics().density;
        for (int i = 0; i < total; i++) {
            Bundle bundle = mValues.valueAt(i);
            float[] values = bundle.getFloatArray(KEY_VALUES);
            if (values == null) continue;

            Type type = Type.values()[bundle.getInt(KEY_TYPE, 0)];
            int color = bundle.getInt(KEY_COLOR, 0xFF000000);

            mPaint.setColor(color);

            float[] minMax = MathUtil.getMinMax(values);
            float minVal = minMax[0];
            float maxVal = minMax[1];

            int width = 0;
            int height = 0;
            switch (type) {
                case POINTS:
                    width = canvas.getWidth() - DOT_WIDTH;
                    height = canvas.getHeight() - DOT_WIDTH;
                    break;

                case LINES:
                    width = (int) (canvas.getWidth() - lineWidth * 2);
                    height = (int) (canvas.getHeight() - lineWidth * 2);
                    break;
            }

            int len = values.length;
            float xRatio = width / (float) (len - 1);
            float yRatio = height / total / (maxVal - minVal);


            int prevX = 0;
            int prevY = 0;
            for (int j = 0; j < len; j++) {
                float curVal = values[j];

                int x = (int) (j * xRatio);
                int y = (int) ((maxVal - curVal) * yRatio);
                y += height / total * i;

                switch (type) {
                    case POINTS:
                        canvas.drawRect(x, y, x + DOT_WIDTH, y + DOT_WIDTH, mPaint);
                        break;

                    case LINES:
                        if (j > 0) {
                            canvas.drawLine(prevX + lineWidth, prevY + lineWidth, x + lineWidth, y + lineWidth, mPaint);
                        }
                        break;
                }

                prevX = x;
                prevY = y;
            }
        }
    }
}
