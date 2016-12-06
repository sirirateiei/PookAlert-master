package rtc.kanchana.sirirat.pookalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            }
        });



    }   // Main Method

}   // Main Class
