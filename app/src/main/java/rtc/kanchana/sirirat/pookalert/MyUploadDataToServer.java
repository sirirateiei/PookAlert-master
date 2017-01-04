package rtc.kanchana.sirirat.pookalert;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterUNG on 1/4/2017 AD.
 */

public class MyUploadDataToServer extends AsyncTask<Void, Void, String>{

    private static final String urlPHP = "http://swiftcodingthai.com/puk1/add_todo.php";
    private Context context;
    private String dayString, monthString, yearString, hourString, minusString, todoString;

    public MyUploadDataToServer(Context context,
                                String dayString,
                                String monthString,
                                String yearString,
                                String hourString,
                                String minusString,
                                String todoString) {
        this.context = context;
        this.dayString = dayString;
        this.monthString = monthString;
        this.yearString = yearString;
        this.hourString = hourString;
        this.minusString = minusString;
        this.todoString = todoString;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Day", dayString)
                    .add("Month", monthString)
                    .add("Year", yearString)
                    .add("Hour", hourString)
                    .add("Minus", minusString)
                    .add("ToDo", todoString)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}   // Main Class
