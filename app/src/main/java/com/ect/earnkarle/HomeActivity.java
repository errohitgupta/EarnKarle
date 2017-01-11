package com.ect.earnkarle;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ect.earnkarle.fragment.FragmentDrawer;
import com.ect.earnkarle.fragment.HomeFragment;
import com.ect.earnkarle.fragment.MyPromotersFragment;
import com.ect.earnkarle.fragment.ProfileFragment;
import com.ect.earnkarle.fragment.ShareAndEarnFragment;
import com.ect.earnkarle.fragment.SupportFragment;
import com.ect.earnkarle.fragment.TransactionFragment;
import com.ect.earnkarle.fragment.TransactionHistoryFragment;
import com.ect.earnkarle.fragment.WalletFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = HomeActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    Fragment profilefragment;
    public static int Position = 0;
    public static ArrayList<String> AllApparraylist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        // display the first navigation drawer view on app launch
//        displayView(0);
        displayViewhome(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem myMenuItem = menu.findItem(R.id.rupees_icon);
        /*SpannableString s = new SpannableString(Constant.Wallet_Account);
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
        myMenuItem.setTitle(s);*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notification_icon) {
            Toast.makeText(getApplicationContext(), "notification_icon action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.wallet_icon) {
            Toast.makeText(getApplicationContext(), "wallet_icon action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.rupees_icon) {
//            item = menu.findItem(R.id.the_item);
//            item.setTitle("bla");
            Toast.makeText(getApplicationContext(), "rupees_icon action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //    @Override
//     public boolean onPrepareOptionsMenu(Menu menu) {
//                   MenuItem myItem = menu.findItem(R.id.rupees_icon);
//                   myItem.setTitle("bla");
//                   return true;
//               }
    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                GetInstalledAppList();
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                this.finish();

//                fragment = new ProfileFragment();
//                title = getString(R.string.title_profile);
                break;
            case 1:
                fragment = new ProfileFragment();
                title = getString(R.string.title_profile);
                break;
            case 2:
                fragment = new ShareAndEarnFragment();
                title = getString(R.string.title_shareandearn);
                break;
            case 3:
                fragment = new WalletFragment();
                title = getString(R.string.title_wallet);
                break;
            case 4:
                fragment = new TransactionFragment();
                title = getString(R.string.title_transaction);
                break;
            case 5:
                fragment = new TransactionHistoryFragment();
                title = getString(R.string.title_transectionhistory);
                break;
            case 6:
                fragment = new SupportFragment();
                title = getString(R.string.title_support);
                break;
            case 7:
                fragment = new MyPromotersFragment();
                title = getString(R.string.title_mypromoter);
                break;
            case 8://Rate
//                fragment = new ShareAndEarnFragment();
//                title = getString(R.string.title_messages);
                break;
            case 9://Logout
//                fragment = new ShareAndEarnFragment();
//                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);

        }
    }

    private void displayViewhome(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    void GetInstalledAppList() {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);

        for (Object object : pkgAppsList) {
            ResolveInfo info = (ResolveInfo) object;
            Drawable icon = getBaseContext().getPackageManager().getApplicationIcon(info.activityInfo.applicationInfo);
            String strAppName = info.activityInfo.applicationInfo.publicSourceDir.toString();
            String strPackageName = info.activityInfo.applicationInfo.packageName.toString();
            final String title = (String) ((info != null) ? getBaseContext().getPackageManager().getApplicationLabel(info.activityInfo.applicationInfo) : "???");
            AllApparraylist.add(strPackageName);
        }
    }
}