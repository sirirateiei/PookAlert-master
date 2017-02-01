package rtc.kanchana.sirirat.pookalert;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = new Intent(Notification.this, Notification.class);

        Uri uri = RingtoneManager.getDefaultUri(android.app.Notification.DEFAULT_SOUND);

        PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, (int) System.currentTimeMillis(), intent, 0);
        android.app.Notification.Builder builder = new android.app.Notification.Builder(Notification.this);
        builder.setContentTitle("Title");
        builder.setContentText("Text");
        builder.setSmallIcon(R.drawable.nobita48);
        builder.setSound(uri);
        builder.setContentIntent(pendingIntent);

        android.app.Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags |= android.app.Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);


    }
}
