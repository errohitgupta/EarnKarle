package com.ect.earnkarle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ect.earnkarle.servercommunication.IServerResponse;
import com.ect.earnkarle.webservice.ServiceHandler;
import com.ect.earnkarle.webservice.ServiceResponceAsyc;
import com.ect.earnkarle.webservice.WebServiceConstants;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC on 3/7/2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener, IServerResponse {

    private EditText et_userid = null;
    private EditText et_password = null;
    private TextView tv_login = null;
    private TextView tv_register = null;
    private TextView tv_forgot = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        intilize();

    }

    private void intilize() {

        et_userid = (EditText) findViewById(R.id.et_userid);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        setonclick();
    }

    private void setonclick() {

        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forgot.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_login:
                if (validateFields()) {
//                    callhomescreen();
                    ServiceLoginCall();
                }

                break;
            case R.id.tv_register:
                callregistrationscreen();
                break;
            case R.id.tv_forgot:
                callforgotpasswordscreen();
                break;
        }

    }

    private void callhomescreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void callregistrationscreen() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);

    }

    private void callforgotpasswordscreen() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);

    }

    private boolean validateFields() {
        /*Constant.Email = et_userid.getText().toString();
        Constant.Password = et_password.getText().toString();

        if (Constant.Email.length() == 0 || Constant.Email == null
                || Constant.Email.equalsIgnoreCase("")) {
            Toast.makeText(this, StandardPopUp.USERID, Toast.LENGTH_SHORT).show();
            return false;
        }  else if (Constant.Password.length() == 0 || Constant.Password == null
                || Constant.Password.equalsIgnoreCase("")) {

            Toast.makeText(this, StandardPopUp.PASSWORD, Toast.LENGTH_SHORT).show();
            return false;
        }*/

        return true;

    }

    private void ServiceLoginCall() {
        // TODO Auto-generated method stub
        //		service_flage = 1;
        String api = WebServiceConstants.getMethodUrl(WebServiceConstants.METHOD_LOGIN);
        //		String api ="http://gooddoc.in/api/v2/registerUser.php";
        List<NameValuePair> requestParaList = new ArrayList<NameValuePair>();

        /*requestParaList.add(new BasicNameValuePair("username", Constant.Email));
        requestParaList.add(new BasicNameValuePair("password",Constant.Password));*/
//        requestParaList.add(new BasicNameValuePair("wallet_amount",Constant.Password));


        Log.e("param", "..." + requestParaList);
        ServiceResponceAsyc park = new ServiceResponceAsyc(this, requestParaList, api, ServiceHandler.POST, WebServiceConstants.PID_LOGIN);
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

                case WebServiceConstants.PID_LOGIN:

                    if (respons != null) {
                        System.out.println("respons PID_GETDOCTORLIST = " + respons.toString());

                        try {
                            //					JSONArray jsonarray = new JSONArray(respons);
                            JSONObject _jOb = new JSONObject(respons);
                            JSONObject dataobj = _jOb.getJSONObject("data");
                            String _message = dataobj.getString("message");
                            String _status = dataobj.getString("success");

                            if (_status.equalsIgnoreCase("true")) {
                                /*Constant.UserId = dataobj.getString("uid");
                                Constant.Wallet_Account = dataobj.getString("wallet_amount");*/
                                callhomescreen();
                            } else {
                                Toast.makeText(LoginActivity.this, _message, Toast.LENGTH_SHORT).show();
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

}
