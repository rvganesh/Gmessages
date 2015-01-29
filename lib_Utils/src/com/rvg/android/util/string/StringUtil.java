package com.rvg.android.util.string;

import android.content.Intent;
import android.os.Bundle;

public class StringUtil {
    /**
     * Returns a String representation of the bundle, or {@code "null"}.
     */
    public static String toString(Bundle bundle) {
        if (bundle == null) return "null";
        bundle.size(); // This call unparcels the data
        return bundle.toString();
    }

    /**
     * Returns a String representation of the intent, or {@code "null"}.
     */
    public static String toString(Intent intent) {
        if (intent == null) return "null";
        return intent.toString() + ", extras=" + toString(intent.getExtras());
    }
}
