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

public class SynToDo extends AsyncTask<Void, Void, String>{

    private static final String urlPHP = "http://swiftcodingthai.com/puk1/get_todo_where.php";
    private Context context;
    private String statusString;

    public SynToDo(Context context,
                   String statusString) {
        this.context = context;
        this.statusString = statusString;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Status", statusString)
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
