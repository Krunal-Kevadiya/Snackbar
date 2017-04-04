package com.krunal.snackbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.kevadiyakrunalk.snackbar.SnackBar;

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
                        .withMessage("Krunal")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
            }
        });
        findViewById(R.id.btn_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.SUCCESS)
                        .withMessage("Krunal")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
            }
        });
        findViewById(R.id.btn_warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.WARNING)
                        .withMessage("Krunal")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
            }
        });
        findViewById(R.id.btn_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.ERROR)
                        .withMessage("Krunal")
                        .withGravity(Gravity.TOP)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
            }
        });
        findViewById(R.id.btn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.INFO)
                        .withMessage("Krunal")
                        .withGravity(Gravity.BOTTOM)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
            }
        });
        findViewById(R.id.btn_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.DEFAULT)
                        .withMessage("Krunal")
                        .withGravity(Gravity.BOTTOM)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
            }
        });
        findViewById(R.id.btn_confuse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackBar = new SnackBar.Builder(MainActivity.this, SnackBar.SnackBarType.CONFUSING)
                        .withMessage("Krunal")
                        .withGravity(Gravity.BOTTOM)
                        .withActionMessageId(R.string.action)
                        .withDuration(SnackBar.LONG_SNACK)
                        .show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSnackBar.clear();
    }
}
