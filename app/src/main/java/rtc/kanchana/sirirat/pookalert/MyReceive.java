package rtc.kanchana.sirirat.pookalert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by masterUNG on 2/1/2017 AD.
 */

public class MyReceive extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, Notification.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);

    }   // Broadcast
}   // Main Class
