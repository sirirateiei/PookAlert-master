package rtc.kanchana.sirirat.pookalert;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

public class SetupTime extends AppCompatActivity {
    //Explicit
    private CalendarView calendarView;
    private String string;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_time);
        //Bind Widget
        calendarView = (CalendarView) findViewById(R.id.calendarView);


        //Calendarview Controller
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                Log.d("pookV1","date ==> " + day + "/" + (month + 1) + "/" + year);
                Toast.makeText(SetupTime.this, "คุณเลือก " + day + "/" + (month + 1) + "/" + year,
                        Toast.LENGTH_SHORT).show();

                confirmDate(day, month, year);

            }   //onSelected
        });


    }   // Main Method

    private void confirmDate(final int day, final int month, final int year) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.nobita48);
        builder.setTitle("Confirm Date");
        builder.setMessage("You Choose ==> " +
                Integer.toString(day) + "/" +
                Integer.toString(month + 1) +
                "/" + Integer.toString(year));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(SetupTime.this, ConfirmTime.class);
                intent.putExtra("Day", day);
                intent.putExtra("Month", month);
                intent.putExtra("Year", year);
                startActivity(intent);
                finish();
            }
        });
        builder.show();

    }


}   // Main Class
