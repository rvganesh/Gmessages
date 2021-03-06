package com.rvg.android.util.math;

public class MathUtil {
    /**
     * Get the min and the max values of the given array.
     * 
     * @param values The array in which to look for the min and max values.
     * @return an array containing the min at index 0 and the max at index 1.
     */
    public static float[] getMinMax(float... values) {
        if (values.length == 0) return new float[] { Float.MIN_VALUE, Float.MAX_VALUE };
        float min = values[0];
        float max = values[0];
        int len = values.length;
        for (int i = 1; i < len; i++) {
            float curVal = values[i];
            if (curVal < min) {
                min = curVal;
            }
            if (curVal > max) {
                max = curVal;
            }
        }
        return new float[] { min, max };
    }

    /**
     * Get the amplitude (difference between the min and the max) of the given array.
     * 
     * @param values The array for which to calculate the amplitude.
     * @return The amplitude of the given arrat.
     */
    public static float getAmplitude(float... values) {
        if (values.length == 0) return 0;
        float[] minMax = getMinMax(values);
        return minMax[1] - minMax[0];
    }

    /**
     * Get the average of the given array.
     * 
     * @param values The array for which to calculate the average.
     * @return the average.
     */
    public static float getAverage(float... values) {
        float sum = 0;
        for (float val : values) {
            sum += val;
        }
        return sum / values.length;
    }

    /**
     * Get the moving average of the given array.
     * 
     * @param values The array for which to calculate the moving average.
     * @param period The period to use (number of values to average).
     * @return the average.
     */
    public static float[] getMovingAverage(float[] values, int period) {
        float[] res = new float[values.length];
        MovingAverage movingAverage = new MovingAverage(period);
        int i = 0;
        for (float v : values) {
            movingAverage.add(v);
            res[i] = movingAverage.getAverage();
            i++;
        }
        return res;
    }
}
