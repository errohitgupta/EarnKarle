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
public class ForgotPasswordActivity extends Activity implements View.OnClickListener {

    private EditText et_email = null;
    private TextView tv_send = null;

    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass_screen);
        alertDialog = new AlertDialog.Builder(this);
        intilize();

    }

    private void intilize() {

        et_email = (EditText) findViewById(R.id.et_email);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_send.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_send:
                showpasswordsentsucessDialog();
                break;

        }

    }

    private void callloginscreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void showpasswordsentsucessDialog() {

        //final Chat chat = (Chat) chatAdpater.getItem(position);
        alertDialog.setTitle("");

        alertDialog.setMessage("Password sent to your email id");
        //	alertDialog.setIcon(R.drawable.dialog_icon);


        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        callloginscreen();

                    }
                });
        alertDialog.show();
    }
}
