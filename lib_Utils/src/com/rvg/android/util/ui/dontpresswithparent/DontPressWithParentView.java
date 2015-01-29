package com.rvg.android.util.ui.dontpresswithparent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * A view that doesn't propagate its pressed state to its parents.<br/>
 * See <a href="http://cyrilmottier.com/2011/11/23/listview-tips-tricks-4-add-several-clickable-areas/">this article</a> to understand the usefulness of doing
 * this.
 */
public class DontPressWithParentView extends View {

    public DontPressWithParentView(Context context) {
        super(context);
    }

    public DontPressWithParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DontPressWithParentView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
        if (pressed && getParent() instanceof View && ((View) getParent()).isPressed()) {
            return;
        }
        super.setPressed(pressed);
    }
}
