package com.ect.earnkarle.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ect.earnkarle.AppDetailActivity;
import com.ect.earnkarle.R;
import com.ect.earnkarle.bean.InstalledApplistBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC on 4/11/2016.
 */
public class InstalledRecyclerViewAdapter extends RecyclerView.Adapter<InstalledRecyclerViewAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Object> mValues;
    private Intent intent;
    //        String packageName;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View mView;
        public final ImageView appicon_iv;
        public final TextView titel_tv, shortdec_tv, rate_tv;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            appicon_iv = (ImageView) view.findViewById(R.id.appicon_iv);
            titel_tv = (TextView) view.findViewById(R.id.titel_tv);
            shortdec_tv = (TextView) view.findViewById(R.id.shortdec_tv);
            rate_tv = (TextView) view.findViewById(R.id.rate_tv);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titel_tv.getText();
        }
    }

    public Object getValueAt(int position) {
        return mValues.get(position);
    }

    //        public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
//            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
//            mBackground = mTypedValue.resourceId;
//            mValues = items;
//        }
    public InstalledRecyclerViewAdapter(Context context, ArrayList<Object> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appitem_row, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final InstalledApplistBean appbean = (InstalledApplistBean) mValues.get(position);
/*
            holder.mBoundString = mValues.get(position);
            holder.mTextView.setText(mValues.get(position));
*/
        holder.mBoundString = appbean.getIcon();
        holder.titel_tv.setText(appbean.getTitle());
        holder.shortdec_tv.setText(appbean.getSmall_description());
        holder.rate_tv.setText(appbean.getPrice());

//            //packageName = appbean.getPackage_name();
//            intent = context.getPackageManager().getLaunchIntentForPackage(packageName);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(v.getContext(), "Item Clicked!", Toast.LENGTH_SHORT).show();
                //Put the package name here...
               /* Constant.OfferID = appbean.getOffer_id();
                Constant.PackageName = appbean.getPackage_name();*/
                callappdetailscreen();
                   /* packageName = appbean.getPackage_name();
                    boolean installed = appInstalledOrNot(packageName);
                    if(installed) {
                        //This intent will help you to launch if the package is already installed
                        Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                        context.startActivity(LaunchIntent);
                        Toast.makeText(v.getContext(), "App is already installed on your phone", Toast.LENGTH_SHORT).show();
                        //System.out.println("App is already installed on your phone");

                    } else {
                        Toast.makeText(v.getContext(), "App is not currently installed on your phone", Toast.LENGTH_SHORT).show();
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("market://details?id=" + packageName));
                        context.startActivity(goToMarket);
                        Intent intent = new Intent(context, AppCheckService.class);
                        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
                        AlarmManager am =  (AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
                        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
                                System.currentTimeMillis()
                                        + (2 * 1000), pendingIntent);
                        // System.out.println("App is not currently installed on your phone");
                    }*/

            }
        });

//            Glide.with(holder.appicon_iv.getContext())
//                    .load(Cheeses.getRandomCheeseDrawable())
//                    .fitCenter()
//                    .into(holder.appicon_iv);
        Glide.with(holder.appicon_iv.getContext())
                .load(R.drawable.amazone_icon)
                .fitCenter()
                .into(holder.appicon_iv);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private void callappdetailscreen() {
        Intent intent = new Intent(context, AppDetailActivity.class);
        context.startActivity(intent);

    }
}
