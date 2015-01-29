package com.rvg.android.util.intent;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class IntentUtil {

    /**
     * Returns whether an intent is 'callable', that is there is at least one activity on the system that can handle it.
     * 
     * @param intent The Activity intent to test.
     * @return {@code true} if there exists such activity, {@code false} otherwise.
     */
    public static boolean isCallable(Context context, Intent intent) {
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
