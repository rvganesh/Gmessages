package com.rvg.android.util.system;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by Ganesh on 28/01/2015.
 */
public class SystemUtil {

    /**
     *  Hide the app icon on android app list screen
     *
     *  @param  context The context of the application
     *  @param  activityName The MainActivity name which is app launcher.
     */
    public static void hideAppIcon(Context context, Class activityName){
        PackageManager p = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, activityName); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
        p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     *  Show the app icon on android app list screen
     *
     *  @param  context The context of the application
     *   @param  activityName The MainActivity name which is app launcher.
     */
    public static void showAppIcon(Context context, Class activityName){
        PackageManager p = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, activityName);
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}
