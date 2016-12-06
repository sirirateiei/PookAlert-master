package rtc.kanchana.sirirat.pookalert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ConfirmTime extends AppCompatActivity {

    //Explicit
    private TextView textView;
    private TimePicker timePicker;
    private int dateAnInt, monthAnInt, yearAnInt,
            hourAnInt, minusAnInt;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_time);

        //Bind Widget
        bindWidget();

        //Get Value From Intent
        getValue();

        //Show Date
        showDate();

        //Set Current Time
        setCurrentTime();

        //Button Controller
        buttonController();


    }   // Main Method

    private void buttonController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hourAnInt = timePicker.getCurrentHour();
                minusAnInt = timePicker.getCurrentMinute();



            }
        });
    }

    private void setCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        timePicker.setIs24HourView(true);
    }

    private void showDate() {
        textView.setText(Integer.toString(dateAnInt) + "/" +
                Integer.toString(monthAnInt + 1) + "/" +
                Integer.toString(yearAnInt));
    }

    private void getValue() {
        dateAnInt = getIntent().getIntExtra("Date", 0);
        monthAnInt = getIntent().getIntExtra("Month", 0);
        yearAnInt = getIntent().getIntExtra("Year", 0);
    }

    private void bindWidget() {
        textView = (TextView) findViewById(R.id.textView5);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        button = (Button) findViewById(R.id.button);
    }

}   // Main Class
