package com.rvg.android.util.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.rvg.android.util.R;

/**
 * A simple dialog showing a progress message.
 */
public class ProgressDialogFragment extends DialogFragment {
    private static final String PREFIX = ProgressDialogFragment.class.getName() + ".";
    public static final String FRAGMENT_TAG = PREFIX + "FRAGMENT_TAG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog res = new ProgressDialog(getActivity());
        res.setMessage(getString(R.string.common_pleaseWait));
        return res;
    }

    public void show(FragmentManager manager) {
        show(manager, FRAGMENT_TAG);
    }
}
