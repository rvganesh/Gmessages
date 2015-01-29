package com.rvg.android.util.ui.dontpresswithparent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * A LinearLayout that doesn't propagate its pressed state to its parents.<br/>
 * See <a href="http://cyrilmottier.com/2011/11/23/listview-tips-tricks-4-add-several-clickable-areas/">this article</a> to understand the usefulness of doing
 * this.
 */
public class DontPressWithParentLinearLayout extends LinearLayout {
    public DontPressWithParentLinearLayout(Context context) {
        super(context);
    }

    public DontPressWithParentLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPressed(boolean pressed) {
        if (pressed && getParent() instanceof View && ((View) getParent()).isPressed()) {
            return;
        }
        super.setPressed(pressed);
    }
}
