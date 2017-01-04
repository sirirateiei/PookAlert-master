package rtc.kanchana.sirirat.pookalert;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private TextView timeTextView, dateTextView;
    private ImageView imageView;
    private String timeString, dateString;
    private int dayAnInt, monthAnInt, hourAnInt, minusAnInt,
            mydayAnInt, myMonthAnInt, myHourAnInt, myMinusAnInt;
    private String tag = "4janV1", tag2 = "4janV2";
    private boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        timeTextView = (TextView) findViewById(R.id.textView);
        dateTextView = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);

        //Setup Time & Date
        Calendar calendar = Calendar.getInstance();
        dayAnInt = calendar.get(Calendar.DAY_OF_MONTH);
        monthAnInt = calendar.get(Calendar.MONTH);
        hourAnInt = calendar.get(Calendar.HOUR_OF_DAY);
        minusAnInt = calendar.get(Calendar.MINUTE);
        DateFormat timeDateFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        timeString = timeDateFormat.format(calendar.getTime());
        dateString = dateFormat.format(calendar.getTime());




        //Show View
        timeTextView.setText(timeString);
        dateTextView.setText(dateString);

        //Image Controller
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetupTime.class));
                finish();
            }
        });

        //get Value from mySQL
        try {

            SynToDo synToDo = new SynToDo(MainActivity.this, "0");
            synToDo.execute();
            String strJSoN = synToDo.get();
            Log.d(tag, "JSON ==> " + strJSoN);

            JSONArray jsonArray = new JSONArray(strJSoN);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            mydayAnInt = jsonObject.getInt("Day");
            myMonthAnInt = jsonObject.getInt("Month");
            myHourAnInt = jsonObject.getInt("Hour");
            myMinusAnInt = jsonObject.getInt("Minus");

            Log.d(tag, "myDay ==>  " + mydayAnInt);
            Log.d(tag, "myMonth ==>  " + myMonthAnInt);
            Log.d(tag, "myHour ==>  " + myHourAnInt);
            Log.d(tag, "myMinus ==>  " + myMinusAnInt);

            Log.d(tag, "Day ==>  " + dayAnInt);
            Log.d(tag, "Month ==>  " + monthAnInt);
            Log.d(tag, "Hour ==>  " + hourAnInt);
            Log.d(tag, "Minus ==>  " + minusAnInt);




        } catch (Exception e) {
            e.printStackTrace();
        }

        checkTimeNotification();


    }   // Main Method

    private void checkTimeNotification() {

        //To Do
        Log.d(tag2, "myDay ==>  " + mydayAnInt);
        Log.d(tag2, "myMonth ==>  " + myMonthAnInt);
        Log.d(tag2, "myHour ==>  " + myHourAnInt);
        Log.d(tag2, "myMinus ==>  " + myMinusAnInt);

        Log.d(tag2, "Day ==>  " + dayAnInt);
        Log.d(tag2, "Month ==>  " + monthAnInt);
        Log.d(tag2, "Hour ==>  " + hourAnInt);
        Log.d(tag2, "Minus ==>  " + minusAnInt);

        if (monthAnInt >= myMonthAnInt) {
            //ถึงกำหนดเดือน

            if (dayAnInt >= mydayAnInt) {
                //ถึงกำหนด day

                if (hourAnInt >= myHourAnInt) {
                    //ถึงกำหนดชัวโมง

                    if (minusAnInt >= myMinusAnInt) {

                        Log.d(tag2, "OK Notificaion");
                        startNotification();

                    }   //if

                }   //if

            }   // if

        }   // if


        //Post Delay
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (aBoolean) {
                    checkTimeNotification();
                }
            }
        }, 10000);


    }   // checkTime

    private void startNotification() {

        aBoolean = false;

        Intent intent = new Intent(MainActivity.this, Notification.class);

        Uri uri = RingtoneManager.getDefaultUri(android.app.Notification.DEFAULT_SOUND);

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, (int) System.currentTimeMillis(), intent, 0);
        android.app.Notification.Builder builder = new android.app.Notification.Builder(MainActivity.this);
        builder.setContentTitle("Title");
        builder.setContentText("Text");
        builder.setSmallIcon(R.drawable.nobita48);
        builder.setSound(uri);
        builder.setContentIntent(pendingIntent);

        android.app.Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification.flags |= android.app.Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);

    }   // Noti

}   // Main Class
