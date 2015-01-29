package gmessage.rvg.com.gmessage.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import gmessage.rvg.com.gmessage.ButterKnife.SecondActivity;

public class OutgoingCallInterceptor extends BroadcastReceiver {

    public OutgoingCallInterceptor() {
    }

    // This method is called when the BroadcastReceiver is receiving
    @Override
    public void onReceive(Context context, Intent intent) {

        final String oldNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        this.setResultData("*123#");
        final String newNumber = this.getResultData();
        String msg = "Intercepted outgoing call. Old number " + oldNumber + ", new number " + newNumber;
        if(oldNumber.equalsIgnoreCase(newNumber)){

            /*To show app icon*/
//            PackageManager p = context.getPackageManager();
//            ComponentName componentName = new ComponentName(context, SecondActivity.class);
//            p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

            Intent intent1 = new Intent(context, SecondActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);

            setResultData(null);
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }

    }
}
