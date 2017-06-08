package com.kevadiyakrunalk.snackbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

public class SnackToast {
    static SuccessView successToastView;
    static WarningView warningToastView;
    static ErrorView errorToastView;
    static InfoView infoToastView;
    static DefaultView defaultToastView;
    static ConfusingView confusingToastView;
    static ConnectedView connectedToastView;

    public enum SnackToastType {
        NONE, SUCCESS, WARNING, ERROR, INFO, DEFAULT, CONFUSING, CONNECTED
    }

    public static Toast makeText(Context context, String msg, int length, int gravity, SnackToastType type) {
        Toast toast = new Toast(context);

        if(type == SnackToastType.NONE) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_none_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
        } else if(type == SnackToastType.SUCCESS) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_success_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            successToastView = (SuccessView) snackLayout.findViewById(R.id.successView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            successToastView.startAnim();
        } else if(type == SnackToastType.WARNING) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_warning_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            warningToastView = (WarningView) snackLayout.findViewById(R.id.warningView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            SpringSystem springSystem = SpringSystem.create();
            final Spring spring = springSystem.createSpring();
            spring.setCurrentValue(1.8);
            SpringConfig config = new SpringConfig(40, 5);
            spring.setSpringConfig(config);
            spring.addListener(new SimpleSpringListener() {

                @Override
                public void onSpringUpdate(Spring spring) {
                    float value = (float) spring.getCurrentValue();
                    float scale = (float) (0.9f - (value * 0.5f));

                    warningToastView.setScaleX(scale);
                    warningToastView.setScaleY(scale);
                }
            });
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    spring.setEndValue(0.4f);
                }
            });
            t.start();
        } else if(type == SnackToastType.ERROR) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_error_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            errorToastView = (ErrorView) snackLayout.findViewById(R.id.errorView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            errorToastView.startAnim();
        } else if(type == SnackToastType.INFO) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_info_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            infoToastView = (InfoView) snackLayout.findViewById(R.id.infoView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            infoToastView.startAnim();
        } else if(type == SnackToastType.DEFAULT) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_default_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            defaultToastView = (DefaultView) snackLayout.findViewById(R.id.defaultView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            defaultToastView.startAnim();
        } else if(type == SnackToastType.CONFUSING) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_confusing_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            confusingToastView = (ConfusingView) snackLayout.findViewById(R.id.confusingView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            confusingToastView.startAnim();
        } else if(type == SnackToastType.CONNECTED) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_connected_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            connectedToastView = (ConnectedView) snackLayout.findViewById(R.id.connectedView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            connectedToastView.startAnim();
        }
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(length);
        toast.show();
        return toast;
    }

    public static Toast makeText(Context context, int msg, int length, int gravity, SnackToastType type) {
        Toast toast = new Toast(context);

        if(type == SnackToastType.NONE) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_none_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
        } else if(type == SnackToastType.SUCCESS) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_success_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            successToastView = (SuccessView) snackLayout.findViewById(R.id.successView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            successToastView.startAnim();
        } else if(type == SnackToastType.WARNING) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_warning_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            warningToastView = (WarningView) snackLayout.findViewById(R.id.warningView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            SpringSystem springSystem = SpringSystem.create();
            final Spring spring = springSystem.createSpring();
            spring.setCurrentValue(1.8);
            SpringConfig config = new SpringConfig(40, 5);
            spring.setSpringConfig(config);
            spring.addListener(new SimpleSpringListener() {

                @Override
                public void onSpringUpdate(Spring spring) {
                    float value = (float) spring.getCurrentValue();
                    float scale = (float) (0.9f - (value * 0.5f));

                    warningToastView.setScaleX(scale);
                    warningToastView.setScaleY(scale);
                }
            });
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    spring.setEndValue(0.4f);
                }
            });
            t.start();
        } else if(type == SnackToastType.ERROR) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_error_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            errorToastView = (ErrorView) snackLayout.findViewById(R.id.errorView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            errorToastView.startAnim();
        } else if(type == SnackToastType.INFO) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_info_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            infoToastView = (InfoView) snackLayout.findViewById(R.id.infoView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            infoToastView.startAnim();
        } else if(type == SnackToastType.DEFAULT) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_default_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            defaultToastView = (DefaultView) snackLayout.findViewById(R.id.defaultView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            defaultToastView.startAnim();
        } else if(type == SnackToastType.CONFUSING) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_confusing_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            confusingToastView = (ConfusingView) snackLayout.findViewById(R.id.confusingView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            confusingToastView.startAnim();
        } else if(type == SnackToastType.CONNECTED) {
            View snackLayout = LayoutInflater.from(context).inflate(R.layout.sb_connected_snack, null, false);
            snackLayout.findViewById(R.id.snackButton).setVisibility(View.GONE);
            connectedToastView = (ConnectedView) snackLayout.findViewById(R.id.connectedView);
            TextView messageView = (TextView) snackLayout.findViewById(R.id.snackMessage);
            messageView.setText(msg);
            toast.setView(snackLayout);
            connectedToastView.startAnim();
        }
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(length);
        toast.show();
        return toast;
    }
}