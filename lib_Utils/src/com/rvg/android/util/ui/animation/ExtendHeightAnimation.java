package com.rvg.android.util.ui.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * An animation to extend/reduce the height of a view.
 */
public class ExtendHeightAnimation extends Animation {
    private final View mView;
    private final int mTargetHeight;
    private final boolean mExtend;

    public ExtendHeightAnimation(View view, int targetHeight, boolean extend) {
        mView = view;
        mTargetHeight = targetHeight;
        mExtend = extend;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;
        if (mExtend) {
            newHeight = (int) (mTargetHeight * interpolatedTime);
        } else {
            newHeight = (int) (mTargetHeight * (1 - interpolatedTime));
        }
        mView.getLayoutParams().height = newHeight;
        mView.requestLayout();
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
