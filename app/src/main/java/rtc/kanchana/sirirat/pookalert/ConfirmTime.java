package rtc.kanchana.sirirat.pookalert;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ConfirmTime extends AppCompatActivity {

    //Explicit
    private TextView textView;
    private TimePicker timePicker;
    private int dateAnInt, monthAnInt, yearAnInt,
            hourAnInt, minusAnInt;
    private Button button;
    private String string;
    private EditText editText;

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

                string = editText.getText().toString().trim();

                confirmData();

            }
        });
    }

    private void confirmData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmTime.this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.bird48);
        builder.setTitle("Please Confirm Data");
        builder.setMessage("วันที่เลือก = " + textView.getText().toString() + "\n" +
                Integer.toString(hourAnInt) + ":" + Integer.toString(minusAnInt) + "\n" +
        string);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                uploadData();
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }   // confirmData

    private void uploadData() {

        try {

            String myDate = Integer.toString(yearAnInt) + "-"
                    + Integer.toString(monthAnInt) + "-"
                    + Integer.toString(dateAnInt) + " " + findHour(hourAnInt) + ":" + findMinius(minusAnInt) + ":00";

            Log.d("31janV1", "myDate ==> " + myDate);

            MyUploadDataToServer myUploadDataToServer = new MyUploadDataToServer(ConfirmTime.this,
                    Integer.toString(dateAnInt),
                    Integer.toString(monthAnInt),
                    Integer.toString(yearAnInt),
                    Integer.toString(hourAnInt),
                    Integer.toString(minusAnInt),
                    string, myDate);
            myUploadDataToServer.execute();

            if (Boolean.parseBoolean(myUploadDataToServer.get())) {
                startActivity(new Intent(ConfirmTime.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(ConfirmTime.this, "ไม่สามารถ อัพเดด้าได้", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String findMinius(int minusAnInt) {

        String minus = Integer.toString(minusAnInt);

        if (minus.length() == 1) {
            return "0" + minus;
        } else {
            return minus;
        }


    }

    private String findHour(int hourAnInt) {

        String hour = Integer.toString(hourAnInt);
        if (hour.length() == 1) {
            return "0" + hour;
        } else {
            return hour;
        }


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
        dateAnInt = getIntent().getIntExtra("Day", 0);
        monthAnInt = getIntent().getIntExtra("Month", 0);
        yearAnInt = getIntent().getIntExtra("Year", 0);
    }

    private void bindWidget() {
        textView = (TextView) findViewById(R.id.textView5);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
    }

}   // Main Class
