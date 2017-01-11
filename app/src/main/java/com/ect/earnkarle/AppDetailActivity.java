package com.ect.earnkarle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ect.earnkarle.fragment.CheeseListFragment;
import com.ect.earnkarle.servercommunication.IServerResponse;
import com.ect.earnkarle.webservice.ServiceHandler;
import com.ect.earnkarle.webservice.ServiceResponceAsyc;
import com.ect.earnkarle.webservice.WebServiceConstants;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

//import com.ect.earnkarle.constant.Constant;

/**
 * Created by ABC on 4/6/2016.
 */
public class AppDetailActivity extends Activity implements View.OnClickListener, IServerResponse {


    private TextView titel_tv, short_tv, rate_tv, instraction_tv, starttask_btn, pricetext_tv, timetext_tv, pricetext2_tv, timetext2_tv;
    private ImageView appicon_iv, lock_iv, lock2_iv;

    private AlertDialog.Builder alertDialog;
    Timer timer;
    TimerTask timerTask;
    int STRT = 0;
    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();
    Handler handlerDaycount;
    private Runnable runnable;
    String wallet = "";
    int wallet_current = 0;
    int TiemerMiliON = 0;
    String callbackURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appdetailscreen);
        alertDialog = new AlertDialog.Builder(this);
        intilize();
        ServiceAppDetailCall();
    }

    private void intilize() {
        titel_tv = (TextView) findViewById(R.id.titel_tv);
        short_tv = (TextView) findViewById(R.id.short_tv);
        rate_tv = (TextView) findViewById(R.id.rate_tv);
        instraction_tv = (TextView) findViewById(R.id.instraction_tv);
        starttask_btn = (TextView) findViewById(R.id.starttask_btn);
        pricetext_tv = (TextView) findViewById(R.id.pricetext_tv);
        timetext_tv = (TextView) findViewById(R.id.timetext_tv);
        pricetext2_tv = (TextView) findViewById(R.id.pricetext2_tv);
        timetext2_tv = (TextView) findViewById(R.id.timetext2_tv);
        appicon_iv = (ImageView) findViewById(R.id.appicon_iv);
        lock_iv = (ImageView) findViewById(R.id.lock_iv);
        lock2_iv = (ImageView) findViewById(R.id.lock2_iv);
        starttask_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.starttask_btn:
                checkappInstalled();
                break;
        }
    }

    private void callHomeScreen() {
        Intent intentlogin = new Intent(this, HomeActivity.class);
        startActivity(intentlogin);
        this.finish();
    }


    private void ServiceAppDetailCall() {
        // TODO Auto-generated method stub
        //		service_flage = 1;
        String api = WebServiceConstants.getMethodUrl(WebServiceConstants.METHOD_GETAPPDETAIL);
        //		String api ="http://gooddoc.in/api/v2/registerUser.php";
        List<NameValuePair> requestParaList = new ArrayList<NameValuePair>();
//        requestParaList.add(new BasicNameValuePair("offer_id", Constant.OfferID));
        Log.e("param", "..." + requestParaList);
        ServiceResponceAsyc park = new ServiceResponceAsyc(this, requestParaList, api, ServiceHandler.GET, WebServiceConstants.PID_GETAPPDETAIL);
        park.execute();

    }

    private void ServiceCallBack() {
        // TODO Auto-generated method stub
/*        wallet_current= Integer.parseInt(Constant.Wallet_Account)+Integer.parseInt(wallet);
        Constant.Wallet_Account = String.valueOf(wallet_current);
        String api = WebServiceConstants.getMethodUrl(WebServiceConstants.METHOD_APPCALLBACK);
        List<NameValuePair> requestParaList = new ArrayList<NameValuePair>();
        requestParaList.add(new BasicNameValuePair("uid", Constant.UserId));
        requestParaList.add(new BasicNameValuePair("offer_id", Constant.OfferID));
        requestParaList.add(new BasicNameValuePair("amount", Constant.Wallet_Account));

        Log.e("param", "..." + requestParaList);
        ServiceResponceAsyc park = new ServiceResponceAsyc(this, requestParaList, api, ServiceHandler.POST,WebServiceConstants.PID_APPCALLBACK);
        park.execute();*/

    }


    @Override
    public void serverResponse(String response, int processid) {
        Message msg = new Message();
        msg.arg1 = processid;
        msg.obj = response;
        _handler.sendMessage(msg);
    }

    private Handler _handler = new Handler() {
        public void handleMessage(Message msg) {
            String respons = msg.obj.toString();
            switch (msg.arg1) {

                case WebServiceConstants.PID_GETAPPDETAIL:

                    if (respons != null) {
                        System.out.println("respons PID_GETDOCTORLIST = " + respons.toString());

                        try {
                            //					JSONArray jsonarray = new JSONArray(respons);
                            wallet = "";
                            JSONObject _jOb = new JSONObject(respons);
                            String _status = _jOb.getString("success");
                            JSONObject dataobj = _jOb.getJSONObject("data");

                            if (_status.equalsIgnoreCase("true")) {

                                String offer_id = dataobj.getString("offer_id");
                                String product_id = dataobj.getString("product_id");
                                //String title = dataobj.getString("title");
                                //String price = dataobj.getString("price");
                                // String icon = dataobj.getString("icon");
                                String image_path = dataobj.getString("image_path");
                                String description = dataobj.getString("description");
                                String voting_star = dataobj.getString("voting_star");
                                String instruction = dataobj.getString("instruction");
                                String postback_url = dataobj.getString("postback_url");
                                String package_name = dataobj.getString("package_name");
                                String startDateOne = dataobj.getString("remaining_time1");
                                String startDateTwo = dataobj.getString("remaining_time2");
                                String remanigTimeOne = dataobj.getString("remaining_time1");
                                String remanigTimeTwo = dataobj.getString("remaining_time2");
                                callbackURL = dataobj.getString("remaining_time2");
                                //Constant.Wallet_Account = Constant.Wallet_Account+dataobj.getString("price");
                                wallet = dataobj.getString("price");
                                titel_tv.setText(dataobj.getString("title"));
                                short_tv.setText(dataobj.getString("description"));
                                rate_tv.setText(dataobj.getString("price"));
                                instraction_tv.setText(dataobj.getString("instruction"));

                                pricetext_tv.setText("Get Rs." + dataobj.getString("date1_price") + " On");
                                pricetext2_tv.setText("Get Rs." + dataobj.getString("date2_price") + " On");
                                if (remanigTimeOne.equalsIgnoreCase("")) {
                                    timetext_tv.setText(dataobj.getString("start_date") + " Left");
                                } else if (remanigTimeOne.equalsIgnoreCase("0")) {
                                    timetext_tv.setText("00:00:00");
                                } else {
                                    TiemerMiliON = Integer.parseInt(remanigTimeOne);
                                    //timetext_tv.setText(remanigTimeOne + " Left");
                                    if (CheeseListFragment.ScreenName.equalsIgnoreCase("installed")) {
                                        countDownStart();
                                    }
                                }
                                if (remanigTimeTwo.equalsIgnoreCase("")) {
                                    timetext2_tv.setText(dataobj.getString("last_date") + " Left");
                                } else if (remanigTimeTwo.equalsIgnoreCase("0")) {
                                    timetext_tv.setText("00:00:00");
                                } else {
                                    if (remanigTimeOne.equalsIgnoreCase("0")) {
                                        TiemerMiliON = Integer.parseInt(remanigTimeTwo);
                                        if (CheeseListFragment.ScreenName.equalsIgnoreCase("installed")) {
                                            countDownStart();
                                        }
                                    }
                                    //timetext2_tv.setText(remanigTimeTwo+" Left");
                                }

                            } else {
                                Toast.makeText(AppDetailActivity.this, "No data fond !", Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            //
                            System.out.println("Exception ==" + e);
                        }
                    }

                    break;
                case WebServiceConstants.PID_APPCALLBACK:

                    if (respons != null) {
                        System.out.println("respons PID_GETDOCTORLIST = " + respons.toString());

                        try {
                            //					JSONArray jsonarray = new JSONArray(respons);
                            JSONObject _jOb = new JSONObject(respons);

                            JSONObject dataobj = _jOb.getJSONObject("data");
                            String _msg = dataobj.getString("success");
                            String _status = dataobj.getString("success");
                            if (_status.equalsIgnoreCase("true")) {
                                callHomeScreen();
//                                Toast.makeText(AppDetailActivity.this, "No data fond !", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AppDetailActivity.this, "No data fond !", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            //
                            System.out.println("Exception ==" + e);
                        }
                    }

                    break;
                default:
                    break;
            }
        }
    };

    private void checkappInstalled() {
        //packageName = appbean.getPackage_name();
       /* Intent intent = getPackageManager().getLaunchIntentForPackage(Constant.PackageName);

        boolean installed = appInstalledOrNot(Constant.PackageName);
        if(installed) {
            //This intent will help you to launch if the package is already installed
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(Constant.PackageName);
            startActivity(LaunchIntent);
            Toast.makeText(this, "App is already installed on your phone", Toast.LENGTH_SHORT).show();
            //System.out.println("App is already installed on your phone");

        } else {
            Toast.makeText(this, "App is not currently installed on your phone", Toast.LENGTH_SHORT).show();
//            Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("market://details?id="+Constant.PackageName));
            Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(callbackURL));

            startActivity(goToMarket);
            startTimer();
        }*/

    }

    private boolean ISInstalled() {
       /* //packageName = appbean.getPackage_name();
        Intent intent = getPackageManager().getLaunchIntentForPackage(Constant.PackageName);

        boolean installed = appInstalledOrNot(Constant.PackageName);

        return installed;*/
        return false;
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = this.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, 10000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        if (ISInstalled()) {
                            stoptimertask();
                            ServiceCallBack();
                            //System.out.print("System="+ISInstalled());
                        } else {
                            // System.out.print("System="+ISInstalled());

                        }
                        // System.out.print("System  "+STRT);
//                        toast.show();
                    }
                });
            }
        };
    }

    // //////////////COUNT DOWN START/////////////////////////
    private static final String FORMAT = "%02d:%02d:%02d";

    int seconds, minutes;

    public void countDownStart() {
        new CountDownTimer(TiemerMiliON, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                timetext2_tv.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + " Left");
                //timetext2_tv.setText(diff+":"+days+":"+hours+":"+minutes+":"+seconds+" Left");
            }

            public void onFinish() {
                //text1.setText("done!");
                timetext2_tv.setText("done!");
            }
        }.start();

    }

    // //////////////COUNT DOWN END/////////////////////////
}
