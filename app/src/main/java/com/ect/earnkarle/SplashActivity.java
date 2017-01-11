package com.ect.earnkarle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {


    private final int DELAY = 3000;
    Timer timer;
    MyTimerTask myTimerTask;
    String device_ID = "";
    //    public static ArrayList<String> AllApparraylist           = new ArrayList<String>();
    Intent intent;
    //SharedprefClass sharedprefclass = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //GetInstalledAppList();
        setContentView(R.layout.splash_screen);
        //sharedprefclass = new SharedprefClass(this);
        // save = sharedprefclass.get("save");
        /*Constant.Deviceid =  Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);*/
        timer = new Timer();
        myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, DELAY);

    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    callAppTour();
//                    calloginscreen();
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                }
            });
        }
    }

    private void calloginscreen() {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void callAppTour() {
        intent = new Intent(this, AppTourFragmentActivity.class);
        startActivity(intent);
        this.finish();
    }


    /*private void callhomescreen()
    {
        Constant.USERTYPE = sharedprefclass.get("usertype");
        Constant.USERNAME = sharedprefclass.get("name");
        Constant.EMAILID = sharedprefclass.get("emailid");
        Constant.USERKEY = sharedprefclass.get("userkey");
        Constant.USERPROFILEPIC = sharedprefclass.get("userprfile");

        intent = new Intent(this,HomeFragmentActivity.class);
        startActivity(intent);
        this.finish();

    }*/
   /* void GetInstalledAppList()
    {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);

        for (Object object : pkgAppsList)
        {
            ResolveInfo info = (ResolveInfo) object;
            Drawable icon    = getBaseContext().getPackageManager().getApplicationIcon(info.activityInfo.applicationInfo);
            String strAppName  	= info.activityInfo.applicationInfo.publicSourceDir.toString();
            String strPackageName  = info.activityInfo.applicationInfo.packageName.toString();
            final String title 	= (String)((info != null) ? getBaseContext().getPackageManager().getApplicationLabel(info.activityInfo.applicationInfo) : "???");
            AllApparraylist.add(strPackageName);
        }
    }*/

}
