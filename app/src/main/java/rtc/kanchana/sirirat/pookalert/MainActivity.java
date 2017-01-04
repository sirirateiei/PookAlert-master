package rtc.kanchana.sirirat.pookalert;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private String tag = "4janV1";
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        checkTimeNotification();


    }   // Main Method

    private void checkTimeNotification() {

        //To Do


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

}   // Main Class
