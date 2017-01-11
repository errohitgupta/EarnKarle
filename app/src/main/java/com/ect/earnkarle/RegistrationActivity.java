package com.ect.earnkarle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ABC on 3/7/2016.
 */
public class RegistrationActivity extends Activity implements View.OnClickListener {

    //    private ImageView left_iv        = null;
//    private TextView hedder_tv       = null;
//    private ImageView right_iv       = null;
//    private LinearLayout left_layut  = null;
//    private LinearLayout right_layut = null;
    private EditText et_username = null;
    //    private EditText et_password       = null;
//    private EditText et_confirmpassword       = null;
    private EditText et_email = null;
    private EditText et_phoneno = null;
    private EditText et_address = null;
    private TextView tv_register = null;
    private AlertDialog.Builder alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);
        alertDialog = new AlertDialog.Builder(this);
        intilize();

    }

    private void intilize() {
        et_username = (EditText) findViewById(R.id.et_username);
//        et_password       = (EditText)findViewById(R.id.et_password);
//        et_confirmpassword  = (EditText)findViewById(R.id.et_confirmpassword);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phoneno = (EditText) findViewById(R.id.et_phoneno);
        // et_address  = (EditText)findViewById(R.id.et_address);
        tv_register = (TextView) findViewById(R.id.tv_register);
        setonclick();
    }

    private void setonclick() {

        tv_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_layut:
                this.finish();
                break;
            case R.id.tv_register:
                /*if (validateFields()) {
                    showregistersucessDialog();
                }*/
                break;

        }

    }

    private void callloginscreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void showregistersucessDialog() {

        //final Chat chat = (Chat) chatAdpater.getItem(position);
        alertDialog.setTitle("");

        alertDialog.setMessage("Registeration successful");
        //	alertDialog.setIcon(R.drawable.dialog_icon);


        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        callloginscreen();
                        //finish();
                        //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

                    }
                });
        alertDialog.show();
    }
   /* private boolean validateFields() {

        Constant.Username = et_username.getText().toString();
       // Constant.Password = et_password.getText().toString();
       // Constant.ConfirmPassword = et_confirmpassword.getText().toString();
        Constant.Email = et_email.getText().toString();
        Constant.Phonenumber = et_phoneno.getText().toString();
       // Constant.Address = et_address.getText().toString();

        if (Constant.Username.length() == 0 || Constant.Username == null || Constant.Username.equalsIgnoreCase(""))
        {

            Toast.makeText(this, StandardPopUp.USERNAME, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Constant.Username.contains(" "))
        {

            Toast.makeText(this, StandardPopUp.USERNAMESPACE, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Constant.Username.length()<6){
            Toast.makeText(this, StandardPopUp.USERNAMELENGTH, Toast.LENGTH_SHORT).show();
            return false;
        }
//     else  if (Constant.Password.length() == 0 || Constant.Password == null || Constant.Password.equalsIgnoreCase(""))
//        {
//
//            Toast.makeText(this, StandardPopUp.PASSWORD, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        else  if (Constant.ConfirmPassword.length() == 0 || Constant.ConfirmPassword == null || Constant.ConfirmPassword.equalsIgnoreCase(""))
//        {
//
//            Toast.makeText(this, StandardPopUp.PASSWORD, Toast.LENGTH_SHORT).show();
//            return false;
//        }

        else if(Constant.Email.length()==0 || Constant.Email==null || Constant.Email.equalsIgnoreCase("")){

            Toast.makeText(this, StandardPopUp.EMAIL, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!Constant.checkEmail(Constant.Email)) {
            Toast.makeText(this, StandardPopUp.EMAILVALIDATION, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Constant.Phonenumber.length() == 0 || Constant.Phonenumber == null || Constant.Phonenumber.equalsIgnoreCase(""))
        {

            Toast.makeText(this, StandardPopUp.PHONENO, Toast.LENGTH_SHORT).show();
            return false;
        }
//        else if (Constant.Address.length() == 0 || Constant.Address == null || Constant.Address.equalsIgnoreCase(""))
//        {
//
//            Toast.makeText(this, StandardPopUp.ADDRESS, Toast.LENGTH_SHORT).show();
//            return false;
//        }

        return true;

    }*/
}
