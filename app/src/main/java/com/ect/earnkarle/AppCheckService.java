package com.ect.earnkarle;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC on 4/4/2016.
 */
public class AppCheckService extends Service {

    private static final Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        handler.post(action);
        System.out.println("call had AppCheckService");
        Toast.makeText(this, "call had AppCheckService", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    private final Runnable action = new Runnable() {
        @Override
        public void run() {
            new ExecuteTask().execute("2", "2");
        }
    };

    class ExecuteTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String res = PostData(params);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            //progressBar.setVisibility(View.GONE);
            //progess_msz.setVisibility(View.GONE);
            // Toast.makeText(getApplicationContext(), result, 3000).show();
        }

    }


    public String PostData(String[] valuse) {
        String s = "";
        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://app.evctweb.in/web_service/appsBack");

            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("offer_id", valuse[0]));
            list.add(new BasicNameValuePair("uid", valuse[1]));
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            s = readResponse(httpResponse);

        } catch (Exception exception) {
        }
        return s;


    }

    public String readResponse(HttpResponse res) {
        InputStream is = null;
        String return_text = "";
        try {
            is = res.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
        } catch (Exception e) {

        }
        return return_text;

    }
}




