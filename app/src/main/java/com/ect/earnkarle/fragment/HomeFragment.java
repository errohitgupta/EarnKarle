package com.ect.earnkarle.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ect.earnkarle.HomeActivity;
import com.ect.earnkarle.R;
import com.ect.earnkarle.SpacesItemDecoration;
import com.ect.earnkarle.bean.AppListBean;
import com.ect.earnkarle.bean.CashBackListBean;
import com.ect.earnkarle.bean.DoTaskListBean;
import com.ect.earnkarle.bean.FeaturedListBean;
import com.ect.earnkarle.bean.InstalledApplistBean;
import com.ect.earnkarle.servercommunication.IServerResponse;
import com.ect.earnkarle.webservice.ServiceHandler;
import com.ect.earnkarle.webservice.ServiceResponceAsyc;
import com.ect.earnkarle.webservice.WebServiceConstants;
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

//import com.ect.earnkarle.constant.Constant;


/**
 * Created by Ravi on 29/07/15.
 */
public class HomeFragment extends Fragment implements IServerResponse {

    protected RecyclerViewPager mRecyclerView;
    private FragmentsAdapter mAdapter;
    Activity activity;
    public static ArrayList<Object> AppList = new ArrayList<Object>();
    public static ArrayList<Object> DoTaskList = new ArrayList<Object>();
    public static ArrayList<Object> CashBackCouponsList = new ArrayList<Object>();
    public static ArrayList<Object> FeaturedList = new ArrayList<Object>();
    public static ArrayList<Object> InstalledApplist = new ArrayList<Object>();
    public static ArrayList<String> InstalledApplistJSON = new ArrayList<String>();
    TabLayout tabLayout;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //initViewPager(rootView);
        // initTabLayout(rootView);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        mRecyclerView = (RecyclerViewPager) rootView.findViewById(R.id.viewpager);


        ServiceAppListCall();
        // ServiceUnistallAppCall();

        // Inflate the layout for this fragment
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

//    private void initTabLayout(View rootView) {
//        //给TabLayout增加Tab, 并关联ViewPager
//         tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
//
//    }

    protected void initViewPager() {
        //  mRecyclerView = (RecyclerViewPager) rootView.findViewById(R.id.viewpager);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layout);
        mAdapter = new FragmentsAdapter(getChildFragmentManager());//getChildFragmentManager
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(50, mRecyclerView.getAdapter().getItemCount()));
        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
            }
        });

    }


    class FragmentsAdapter extends FragmentStatePagerAdapter implements TabLayoutSupport.ViewPagerTabLayoutAdapter {
        LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();

        public FragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position, Fragment.SavedState savedState) {
            HomeActivity.Position = position;
            Fragment f = mFragmentCache.containsKey(position) ? mFragmentCache.get(position)
                    : new CheeseListFragment();
            Log.e("test", "getItem:" + position + " from cache" + mFragmentCache.containsKey
                    (position));
            if (savedState == null || f.getArguments() == null) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                f.setArguments(bundle);
                Log.e("test", "setArguments:" + position);
            } else if (!mFragmentCache.containsKey(position)) {
                f.setInitialSavedState(savedState);
                Log.e("test", "setInitialSavedState:" + position);
            }
            mFragmentCache.put(position, f);
            return f;
        }

        @Override
        public void onDestroyItem(int position, Fragment fragment) {
            // onDestroyItem
            while (mFragmentCache.size() > 5) {
                Object[] keys = mFragmentCache.keySet().toArray();
                mFragmentCache.remove(keys[0]);
            }
        }

        @Override
        public String getPageTitle(int position) {
            return titel(position);
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    private String titel(int position) {
        String titel = "";
        switch (position) {
            case 0:
                titel = "Install";
                break;
            case 1:
                titel = "Do Task";
                break;
            case 2:
                titel = "Cash Back";
                break;
            case 3:
                titel = "Featured offers";
                break;
            case 4:
                titel = "Installed App";
                break;
        }
        return titel;
    }

    private void ServiceAppListCall() {
        // TODO Auto-generated method stub
        //		service_flage = 1;
        String api = WebServiceConstants.getMethodUrl(WebServiceConstants.METHOD_ALLAPP_LIST);
        //		String api ="http://gooddoc.in/api/v2/registerUser.php";
        List<NameValuePair> requestParaList = new ArrayList<NameValuePair>();
        /*requestParaList.add(new BasicNameValuePair("uid", Constant.UserId));
        requestParaList.add(new BasicNameValuePair("device_id", Constant.Deviceid));*/
        requestParaList.add(new BasicNameValuePair("package_name", (new JSONArray(HomeActivity.AllApparraylist)).toString()));
        Log.e("param", "..." + requestParaList);
        ServiceResponceAsyc park = new ServiceResponceAsyc(HomeFragment.this, HomeFragment.this.getActivity(), requestParaList, api, ServiceHandler.POST, WebServiceConstants.PID_ALLAPP_LIST);
        park.execute();

    }

    private void ServiceUnistallAppCall() {
        // TODO Auto-generated method stub

        //		service_flage = 1;
        String api = WebServiceConstants.getMethodUrl(WebServiceConstants.METHOD_GETUNISTALLED_APP);
        //		String api ="http://gooddoc.in/api/v2/registerUser.php";
        List<NameValuePair> requestParaList = new ArrayList<NameValuePair>();
//        requestParaList.add(new BasicNameValuePair("uid", Constant.UserId));
        requestParaList.add(new BasicNameValuePair("package_name", (new JSONArray(InstalledApplistJSON)).toString()));
        Log.e("param", "..." + requestParaList);
        ServiceResponceAsyc park = new ServiceResponceAsyc(HomeFragment.this, HomeFragment.this.getActivity(), requestParaList, api, ServiceHandler.POST, WebServiceConstants.PID_GETUNISTALLED_APP);
        park.execute();

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

                case WebServiceConstants.PID_ALLAPP_LIST:
                    if (respons != null) {
                        System.out.println("respons PID_GETDOCTORLIST = " + respons.toString());
                        try {
                            //					JSONArray jsonarray = new JSONArray(respons);

                            JSONObject _jOb = new JSONObject(respons);
//
                            String _messageApp = _jOb.getString("app_success");
                            String _messageTask = _jOb.getString("task_success");
                            String _messageCashback = _jOb.getString("cashback_success");
                            String _messageFeatured = _jOb.getString("featured_success");
                            String _messageInstalled = _jOb.getString("installed_success");

                            if (_messageApp.equalsIgnoreCase("true")) {
                                AppList.clear();

                                JSONArray _jsonarray = _jOb.getJSONArray("app_data");

                                System.out.println("_jsonarray = " + _jsonarray);

                                for (int i = 0; i < _jsonarray.length(); i++) {
                                    JSONObject jsonObj = _jsonarray.getJSONObject(i);
                                    AppListBean appbean = new AppListBean();
                                    appbean.setOffer_id(jsonObj.getString("offer_id"));
                                    appbean.setProduct_id(jsonObj.getString("product_id"));
                                    appbean.setIcon(jsonObj.getString("icon"));
                                    appbean.setTitle(jsonObj.getString("title"));
                                    appbean.setSmall_description(jsonObj.getString("small_description"));
                                    appbean.setVoting_star(jsonObj.getString("voting_star"));
                                    appbean.setPrice(jsonObj.getString("price"));
                                    appbean.setPackage_name(jsonObj.getString("package_name"));
                                    AppList.add(appbean);
                                }
                            }
                            if (_messageTask.equalsIgnoreCase("true")) {
                                DoTaskList.clear();

                                JSONArray _jsonarray = _jOb.getJSONArray("task_data");

                                System.out.println("_jsonarray = " + _jsonarray);

                                for (int i = 0; i < _jsonarray.length(); i++) {
                                    JSONObject jsonObj = _jsonarray.getJSONObject(i);
                                    DoTaskListBean appbean = new DoTaskListBean();
                                    appbean.setOffer_id(jsonObj.getString("offer_id"));
                                    appbean.setProduct_id(jsonObj.getString("product_id"));
                                    appbean.setIcon(jsonObj.getString("icon"));
                                    appbean.setTitle(jsonObj.getString("title"));
                                    appbean.setSmall_description(jsonObj.getString("small_description"));
                                    appbean.setVoting_star(jsonObj.getString("voting_star"));
                                    appbean.setPrice(jsonObj.getString("price"));
                                    appbean.setPackage_name(jsonObj.getString("package_name"));
                                    DoTaskList.add(appbean);
                                }
                            }
                            if (_messageCashback.equalsIgnoreCase("true")) {
                                CashBackCouponsList.clear();

                                JSONArray _jsonarray = _jOb.getJSONArray("cashback_data");

                                System.out.println("_jsonarray = " + _jsonarray);

                                for (int i = 0; i < _jsonarray.length(); i++) {
                                    JSONObject jsonObj = _jsonarray.getJSONObject(i);
                                    CashBackListBean appbean = new CashBackListBean();
                                    appbean.setOffer_id(jsonObj.getString("offer_id"));
                                    appbean.setProduct_id(jsonObj.getString("product_id"));
                                    appbean.setIcon(jsonObj.getString("icon"));
                                    appbean.setTitle(jsonObj.getString("title"));
                                    appbean.setSmall_description(jsonObj.getString("small_description"));
                                    appbean.setVoting_star(jsonObj.getString("voting_star"));
                                    appbean.setPrice(jsonObj.getString("price"));
                                    appbean.setPackage_name(jsonObj.getString("package_name"));
                                    CashBackCouponsList.add(appbean);
                                }
                            }
                            if (_messageFeatured.equalsIgnoreCase("true")) {
                                FeaturedList.clear();

                                JSONArray _jsonarray = _jOb.getJSONArray("featured_data");

                                System.out.println("_jsonarray = " + _jsonarray);

                                for (int i = 0; i < _jsonarray.length(); i++) {
                                    JSONObject jsonObj = _jsonarray.getJSONObject(i);
                                    FeaturedListBean appbean = new FeaturedListBean();
                                    appbean.setOffer_id(jsonObj.getString("offer_id"));
                                    appbean.setProduct_id(jsonObj.getString("product_id"));
                                    appbean.setIcon(jsonObj.getString("icon"));
                                    appbean.setTitle(jsonObj.getString("title"));
                                    appbean.setSmall_description(jsonObj.getString("small_description"));
                                    appbean.setVoting_star(jsonObj.getString("voting_star"));
                                    appbean.setPrice(jsonObj.getString("price"));
                                    appbean.setPackage_name(jsonObj.getString("package_name"));
                                    FeaturedList.add(appbean);
                                }
                            }
                            if (_messageInstalled.equalsIgnoreCase("true")) {
                                InstalledApplist.clear();

                                JSONArray _jsonarray = _jOb.getJSONArray("installed_data");

                                System.out.println("_jsonarray = " + _jsonarray);

                                for (int i = 0; i < _jsonarray.length(); i++) {
                                    JSONObject jsonObj = _jsonarray.getJSONObject(i);
                                    InstalledApplistBean appbean = new InstalledApplistBean();
                                    appbean.setOffer_id(jsonObj.getString("offer_id"));
                                    appbean.setProduct_id(jsonObj.getString("product_id"));
                                    appbean.setIcon(jsonObj.getString("icon"));
                                    appbean.setTitle(jsonObj.getString("title"));
                                    appbean.setSmall_description(jsonObj.getString("small_description"));
                                    appbean.setVoting_star(jsonObj.getString("voting_star"));
                                    appbean.setPrice(jsonObj.getString("price"));
                                    appbean.setPackage_name(jsonObj.getString("package_name"));
                                    InstalledApplistJSON.add(jsonObj.getString("package_name"));
                                    InstalledApplist.add(appbean);
                                }
                            }

                            initViewPager();
                            TabLayoutSupport.setupWithViewPager(tabLayout, mRecyclerView, mAdapter);
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

}
