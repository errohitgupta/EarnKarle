package com.ect.earnkarle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ect.earnkarle.fragment.FirstFragment;
import com.ect.earnkarle.fragment.SecondFragment;
import com.ect.earnkarle.fragment.ThirdFragment;

/**
 * Created by ABC on 3/20/2016.
 */
public class AppTourFragmentActivity extends FragmentActivity implements View.OnClickListener {


    TextView skip_tv;
    TextView next_tv;
    ViewPager pager;
    int pageno = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apptour_screen);
        pager = (ViewPager) findViewById(R.id.viewPager);
        skip_tv = (TextView) findViewById(R.id.skip_tv);
        next_tv = (TextView) findViewById(R.id.next_tv);

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        setonclick();
    }

    private void setonclick() {

        skip_tv.setOnClickListener(this);
        next_tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.skip_tv:
                callloginscreen();
                break;
            case R.id.next_tv:
                pageno = pager.getCurrentItem() + 1;
                if (pageno < 3) {
                    pager.setCurrentItem(pageno, true);
                } else {
                    callloginscreen();
                }

                break;

        }
    }

    private void callloginscreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return FirstFragment.newInstance("");
                case 1:
                    return SecondFragment.newInstance("");
                case 2:
                    return ThirdFragment.newInstance("");
                default:
                    return ThirdFragment.newInstance("");

            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}