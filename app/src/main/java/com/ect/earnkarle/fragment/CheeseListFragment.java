/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ect.earnkarle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ect.earnkarle.HomeActivity;
import com.ect.earnkarle.R;
import com.ect.earnkarle.adapter.AppListRecyclerViewAdapter;
import com.ect.earnkarle.adapter.CashBackRecyclerViewAdapter;
import com.ect.earnkarle.adapter.DoTaskRecyclerViewAdapter;
import com.ect.earnkarle.adapter.FeaturedRecyclerViewAdapter;
import com.ect.earnkarle.adapter.InstalledRecyclerViewAdapter;

public class CheeseListFragment extends Fragment {
    private int mIndex;
    public static String ScreenName = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_cheese_list, container, false);
        setupRecyclerView(rv);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");
//        if (savedInstanceState == null) {
//            mIndex = getArguments().getInt("index");
//            ((AppCompatActivity) getActivity()).getSupportActionBar()
//                    .setTitle("from arguments:" + mIndex);
//        } else {
//            mIndex = savedInstanceState.getInt("index");
//            ((AppCompatActivity) getActivity()).getSupportActionBar()
//                    .setTitle("from savedInstanceState:" + mIndex);
//        }
        return rv;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", mIndex);
        Log.d("test", "call onSaveInstanceState:" + mIndex);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        switch (HomeActivity.Position) {
            case 0:
                ScreenName = "";
                recyclerView.setAdapter(new AppListRecyclerViewAdapter(getActivity(), HomeFragment.AppList));
                break;
            case 1:
                ScreenName = "";
                recyclerView.setAdapter(new DoTaskRecyclerViewAdapter(getActivity(), HomeFragment.DoTaskList));
                break;
            case 2:
                ScreenName = "";
                recyclerView.setAdapter(new CashBackRecyclerViewAdapter(getActivity(), HomeFragment.CashBackCouponsList));
                break;
            case 3:
                ScreenName = "";
                recyclerView.setAdapter(new FeaturedRecyclerViewAdapter(getActivity(), HomeFragment.FeaturedList));
                break;
            case 4:
                ScreenName = "installed";
                recyclerView.setAdapter(new InstalledRecyclerViewAdapter(getActivity(), HomeFragment.InstalledApplist));
                break;
        }


//        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),
//                getRandomSublist(Cheeses.sCheeseStrings, 30)));

    }

    /*private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }*/


}
