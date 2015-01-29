package com.rvg.android.util.ui;

import android.util.SparseArray;
import android.view.View;

/**
 * A class implementing the View Holder pattern.<br/>
 * This class is useful because of this issue: <a href="http://b.android.com/18273">http://b.android.com/18273</a>.
 */
public class ViewHolder {
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View res = viewHolder.get(id);
        if (res == null) {
            res = view.findViewById(id);
            viewHolder.put(id, res);
        }
        return (T) res;
    }
}
