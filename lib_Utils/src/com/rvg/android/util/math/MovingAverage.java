package com.rvg.android.util.math;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Helper to calculate the moving average of an array.
 */
public class MovingAverage {
    private Queue<Float> mWindow = new LinkedList<Float>();
    private int mPeriod;
    private Float mSum = 0f;

    public MovingAverage(int period) {
        mPeriod = period;
    }

    public void add(Float value) {
        mSum = mSum + value;
        mWindow.add(value);
        if (mWindow.size() > mPeriod) {
            mSum = mSum - mWindow.remove();
        }
    }

    public Float getAverage() {
        if (mWindow.isEmpty()) {
            // Technically the average is undefined
            return 0f;
        }
        return mSum / mWindow.size();
    }
}
