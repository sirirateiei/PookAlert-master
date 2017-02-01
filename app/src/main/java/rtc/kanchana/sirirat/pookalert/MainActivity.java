package rtc.kanchana.sirirat.pookalert;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private TextView timeTextView, dateTextView;
    private ImageView imageView;
    private String timeString, dateString;
    private int dayAnInt, monthAnInt, hourAnInt, minusAnInt,
            mydayAnInt, myMonthAnInt, myHourAnInt, myMinusAnInt;
    private String tag = "4janV1", tag2 = "4janV2";
    private boolean aBoolean = true;
    private Calendar[] calendars;
    private boolean aBoolean2 = true;
    private Calendar calendar, alertCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        timeTextView = (TextView) findViewById(R.id.textView);
        dateTextView = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);

        //Setup Time & Date
        calendar = Calendar.getInstance();
        dayAnInt = calendar.get(Calendar.DAY_OF_MONTH);
        monthAnInt = calendar.get(Calendar.MONTH);
        hourAnInt = calendar.get(Calendar.HOUR_OF_DAY);
        minusAnInt = calendar.get(Calendar.MINUTE);
        DateFormat timeDateFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        timeString = timeDateFormat.format(calendar.getTime());
        dateString = dateFormat.format(calendar.getTime());

        Log.d("31janV1", "เวลาปัจจุบัน ==> " + calendar.getTime().toString());




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

            calendars = new Calendar[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                mydayAnInt = jsonObject.getInt("Day");
                myMonthAnInt = jsonObject.getInt("Month");
                myHourAnInt = jsonObject.getInt("Hour");
                myMinusAnInt = jsonObject.getInt("Minus");

                calendars[i] = Calendar.getInstance();
                calendars[i].set(Calendar.DAY_OF_MONTH, mydayAnInt);
                calendars[i].set(Calendar.MONTH, myMonthAnInt);
                calendars[i].set(Calendar.HOUR_OF_DAY, myHourAnInt);
                calendars[i].set(Calendar.MINUTE, myMinusAnInt);
                calendars[i].set(Calendar.SECOND, 0);

                Log.d("31janV1", "calendars(" + i + ") ==> " + calendars[i].getTime().toString());

                Log.d(tag, "myDay ==>  " + mydayAnInt);
                Log.d(tag, "myMonth ==>  " + myMonthAnInt);
                Log.d(tag, "myHour ==>  " + myHourAnInt);
                Log.d(tag, "myMinus ==>  " + myMinusAnInt);

                Log.d(tag, "Day ==>  " + dayAnInt);
                Log.d(tag, "Month ==>  " + monthAnInt);
                Log.d(tag, "Hour ==>  " + hourAnInt);
                Log.d(tag, "Minus ==>  " + minusAnInt);

            }   // for




        } catch (Exception e) {
            e.printStackTrace();
        }

       // checkTimeNotification();

        //startNotification();

        //myCheckTime();


    }   // Main Method

    private void myCheckTime() {

        Log.d("31janV1", "aBoolean2 ก่อนลูป ==> " + aBoolean2);

        for (int i=0;i<calendars.length;i++) {

            Log.d("31janV1", "aBoolean2 ในลูป ==> " + aBoolean2);
            if (aBoolean2) {
                if (calendar.getTime().before(calendars[i].getTime())) {
                    aBoolean2 = false;
                    Log.d("31janV1", "เวลาที่ Alert ==> " + calendars[i].getTime().toString());
                    alertCalendar = Calendar.getInstance();
                    alertCalendar = calendars[i];
                    //startNotification();
                    myStartMasterNoti();
                }   //if
            }   // if

        }   //for

      //  Log.d("31janV1", "alertCal ==>" + alertCalendar.getTime().toString());
       // checkTimeAlert();


    }   // myCheck

    private void myStartMasterNoti() {

        Random random = new Random();
        int intBroadcast = random.nextInt(1000);

        Intent intent = new Intent(getBaseContext(), MyReceive.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),
                intBroadcast, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertCalendar.getTimeInMillis(),pendingIntent);


    }

    @Override
    protected void onResume() {
        super.onResume();

       myCheckTime();
        //checkTimeAlert();

    }

    private void checkTimeAlert() {
        if (calendar.equals(alertCalendar)) {
            startNotification();
        }

    }

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
