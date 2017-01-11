package com.ect.earnkarle.webservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ect.earnkarle.AppCheckService;
import com.ect.earnkarle.fragment.HomeFragment;
import com.ect.earnkarle.servercommunication.IServerResponse;

import org.apache.http.NameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceResponceAsyc extends AsyncTask<Void, Void, Void> {

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    //	    private static String url = "http://api.androidhive.info/contacts/";

    // JSON Node names
    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_MOBILE = "mobile";
    private static final String TAG_PHONE_HOME = "home";
    private static final String TAG_PHONE_OFFICE = "office";

    // contacts JSONArray
    JSONArray contacts = null;
    Activity activity;
    Context context;

    String url;
    int METHODTYPE = 0;
    int METHODPID = 0;
    IServerResponse iserverresponse;

    List<NameValuePair> listparam = new ArrayList<NameValuePair>();

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;
    HomeFragment profileFragment;
    AppCheckService appCheckService;
//	FragmentChangePassword fragmentchangepass;
//	FragmentHome fragmenthome;
//	FragmentProfile_User fragmentprofileuser;
//	FragmentProfile_Doctor fragmentprofiledoctor;
//	FragmentSearch fragmentSearch;


    public ServiceResponceAsyc(Activity activity, List<NameValuePair> params, String api, int methodtype, int MethodPId) {
        // TODO Auto-generated constructor stub
        this.context = activity;
        this.listparam = params;
        this.url = api;
        this.METHODTYPE = methodtype;
        this.METHODPID = MethodPId;
        iserverresponse = (IServerResponse) activity;
    }


    public ServiceResponceAsyc(HomeFragment profileFragment,
                               Activity activity, List<NameValuePair> params, String api, int methodtype, int MethodPId) {
        // TODO Auto-generated constructor stub
        this.profileFragment = profileFragment;
        this.context = activity;
        this.listparam = params;
        this.url = api;
        this.METHODTYPE = methodtype;
        this.METHODPID = MethodPId;
        iserverresponse = (IServerResponse) profileFragment;
    }

    public ServiceResponceAsyc(AppCheckService appservice, Context activity,
                               List<NameValuePair> params, String api, int methodtype, int MethodPId) {
        // TODO Auto-generated constructor stub
        this.appCheckService = appservice;
        this.context = activity;
        this.listparam = params;
        this.url = api;
        this.METHODTYPE = methodtype;
        this.METHODPID = MethodPId;
        iserverresponse = (IServerResponse) profileFragment;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog

        if (!((Activity) context).isFinishing()) {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url, METHODTYPE, listparam);

        Log.d("Response: ", "> " + jsonStr);
        if (jsonStr != null) {
            iserverresponse.serverResponse(jsonStr, METHODPID);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */

    }


}
