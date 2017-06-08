package com.krunal.snackbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.kevadiyakrunalk.snackbar.SnackBar;
import com.kevadiyakrunalk.snackbar.SnackToast;

public class MainActivity extends AppCompatActivity {

    private SnackBar mSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_none).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.NONE)
                        .withMessage("Hi!, I am developer.")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
                SnackToast.makeText(MainActivity.this, "Hi!, I am developer.", Toast.LENGTH_LONG, Gravity.BOTTOM, SnackToast.SnackToastType.NONE);
            }
        });
        findViewById(R.id.btn_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.SUCCESS)
                        .withMessage("Hi!, I am developer.")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
                SnackToast.makeText(MainActivity.this, "Hi!, I am developer.", Toast.LENGTH_LONG, Gravity.BOTTOM, SnackToast.SnackToastType.SUCCESS);
            }
        });
        findViewById(R.id.btn_warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.WARNING)
                        .withMessage("Hi!, I am developer.")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
                SnackToast.makeText(MainActivity.this, "Hi!, I am developer.", Toast.LENGTH_LONG, Gravity.BOTTOM, SnackToast.SnackToastType.WARNING);
            }
        });
        findViewById(R.id.btn_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.ERROR)
                        .withMessage("Hi!, I am developer.")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
                SnackToast.makeText(MainActivity.this, "Hi!, I am developer.", Toast.LENGTH_LONG, Gravity.BOTTOM, SnackToast.SnackToastType.ERROR);
            }
        });
        findViewById(R.id.btn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.INFO)
                        .withMessage("Hi!, I am developer.")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
                SnackToast.makeText(MainActivity.this, "Hi!, I am developer.", Toast.LENGTH_LONG, Gravity.BOTTOM, SnackToast.SnackToastType.INFO);
            }
        });
        findViewById(R.id.btn_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.DEFAULT)
                        .withMessage("Hi!, I am developer.")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
                SnackToast.makeText(MainActivity.this, "Hi!, I am developer.", Toast.LENGTH_LONG, Gravity.BOTTOM, SnackToast.SnackToastType.DEFAULT);
            }
        });
        findViewById(R.id.btn_confuse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.CONFUSING)
                        .withMessage("Hi!, I am developer.")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
                SnackToast.makeText(MainActivity.this, "Hi!, I am developer.", Toast.LENGTH_LONG, Gravity.BOTTOM, SnackToast.SnackToastType.CONFUSING);
            }
        });
        findViewById(R.id.btn_connected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.CONNECTED)
                        .withMessage("Hi!, I am developer.")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
                SnackToast.makeText(MainActivity.this, "Hi!, I am developer.", Toast.LENGTH_LONG, Gravity.BOTTOM, SnackToast.SnackToastType.CONNECTED);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSnackBar.clear();
    }
}
