package com.rvg.android.util.dialog;

import android.app.Activity;

/**
 * Interface that an {@link Activity} can implement to {@link AlertDialogFragment} related events.
 */
public interface AlertDialogListener {
    void onClickPositive(int tag, Object payload);

    void onClickNegative(int tag, Object payload);

    void onClickListItem(int tag, int index, Object payload);
}
