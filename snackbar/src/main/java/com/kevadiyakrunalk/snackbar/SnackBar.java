package com.kevadiyakrunalk.snackbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

public class SnackBar {
    public enum SnackBarType {
        NONE, SUCCESS, WARNING, ERROR, INFO, DEFAULT, CONFUSING
    }

    private SuccessView successToastView;
    private WarningView warningToastView;
    private ErrorView errorToastView;
    private InfoView infoToastView;
    private DefaultView defaultToastView;
    private ConfusingView confusingToastView;

    public static final short LONG_SNACK = 5000;
    public static final short MED_SNACK = 3500;
    public static final short SHORT_SNACK = 2000;
    public static final short PERMANENT_SNACK = 0;

    private SnackContainer mSnackContainer;
    private View mParentView;

    private OnMessageClickListener mClickListener;
    private OnVisibilityChangeListener mVisibilityChangeListener;

    public interface OnMessageClickListener {

        void onMessageClick(Parcelable token);
    }

    public interface OnVisibilityChangeListener {

        /**
         * Gets called when a message is shown
         *
         * @param stackSize the number of messages left to show
         */
        void onShow(int stackSize);

        /**
         * Gets called when a message is hidden
         *
         * @param stackSize the number of messages left to show
         */
        void onHide(int stackSize);
    }

    public SnackBar(Activity activity, SnackBarType type) {
        ViewGroup container = (ViewGroup) activity.findViewById(android.R.id.content);

        View snackLayout = null;

        if(type == SnackBarType.NONE) {
            snackLayout = activity.getLayoutInflater().inflate(R.layout.sb_none_snack, container, false);
        } else if(type == SnackBarType.SUCCESS) {
            snackLayout = activity.getLayoutInflater().inflate(R.layout.sb_success_snack, container, false);
        } else if(type == SnackBarType.WARNING) {
            snackLayout = activity.getLayoutInflater().inflate(R.layout.sb_warning_snack, container, false);
        } else if(type == SnackBarType.ERROR) {
            snackLayout = activity.getLayoutInflater().inflate(R.layout.sb_error_snack, container, false);
        } else if(type == SnackBarType.INFO) {
            snackLayout = activity.getLayoutInflater().inflate(R.layout.sb_info_snack, container, false);
        } else if(type == SnackBarType.DEFAULT) {
            snackLayout = activity.getLayoutInflater().inflate(R.layout.sb_default_snack, container, false);
        } else if(type == SnackBarType.CONFUSING) {
            snackLayout = activity.getLayoutInflater().inflate(R.layout.sb_confusing_snack, container, false);
        }

        init(container, snackLayout, type);
    }

    public SnackBar(Context context, View v, SnackBarType type) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sb_snack_container, ((ViewGroup) v));
        View snackLayout = null;

        if(type == SnackBarType.NONE) {
            snackLayout = inflater.inflate(R.layout.sb_none_snack, ((ViewGroup) v), false);
        } else if(type == SnackBarType.SUCCESS) {
            snackLayout = inflater.inflate(R.layout.sb_success_snack, ((ViewGroup) v), false);
        } else if(type == SnackBarType.WARNING) {
            snackLayout = inflater.inflate(R.layout.sb_warning_snack, ((ViewGroup) v), false);
        } else if(type == SnackBarType.ERROR) {
            snackLayout = inflater.inflate(R.layout.sb_error_snack, ((ViewGroup) v), false);
        } else if(type == SnackBarType.INFO) {
            snackLayout = inflater.inflate(R.layout.sb_info_snack, ((ViewGroup) v), false);
        } else if(type == SnackBarType.DEFAULT) {
            snackLayout = inflater.inflate(R.layout.sb_default_snack, ((ViewGroup) v), false);
        } else if(type == SnackBarType.CONFUSING) {
            snackLayout = inflater.inflate(R.layout.sb_confusing_snack, ((ViewGroup) v), false);
        }
        init((ViewGroup) v, snackLayout, type);
    }

    private void init(ViewGroup container, View v, SnackBarType type) {
        mSnackContainer = (SnackContainer) container.findViewById(R.id.snackContainer);
        if (mSnackContainer == null) {
            mSnackContainer = new SnackContainer(container);
        }

        mParentView = v;
        TextView snackBtn = (TextView) v.findViewById(R.id.snackButton);
        snackBtn.setOnClickListener(mButtonListener);

        if(type == SnackBarType.SUCCESS) {
            successToastView = (SuccessView) v.findViewById(R.id.successView);
        } else if(type == SnackBarType.WARNING) {
            warningToastView = (WarningView) v.findViewById(R.id.warningView);
        } else if(type == SnackBarType.ERROR) {
            errorToastView = (ErrorView) v.findViewById(R.id.errorView);
        } else if(type == SnackBarType.INFO) {
            infoToastView = (InfoView) v.findViewById(R.id.infoView);
        } else if(type == SnackBarType.DEFAULT) {
            defaultToastView = (DefaultView) v.findViewById(R.id.defaultView);
        } else if(type == SnackBarType.CONFUSING) {
            confusingToastView = (ConfusingView) v.findViewById(R.id.confusingView);
        }
    }

    public static class Builder {

        private SnackBar mSnackBar;
        private SnackBarType mType;

        private Context mContext;
        private String mMessage;
        private String mActionMessage;
        private int mActionIcon = 0;
        private short mDuration = MED_SNACK;
        private ColorStateList mTextColor;
        private ColorStateList mBackgroundColor;
        private int mHeight;
        private boolean mClear;
        private boolean mAnimateClear;
        private Typeface mTypeFace;
        private int mGravity;

        /**
         * Constructs a new SnackBar
         *
         * @param activity the activity to inflate into
         */
        public Builder(Activity activity, SnackBarType type) {
            this.mType = type;
            mGravity = Gravity.BOTTOM;
            mContext = activity.getApplicationContext();
            mSnackBar = new SnackBar(activity, type);
        }

        /**
         * Constructs a new SnackBar
         *
         * @param context the context used to obtain resources
         * @param v       the view to inflate the SnackBar into
         */
        public Builder(Context context, View v, SnackBarType type) {
            this.mType = type;
            mContext = context;
            mGravity = Gravity.BOTTOM;
            mSnackBar = new SnackBar(context, v, type);
        }

        /**
         * Sets the message to display on the SnackBar
         *
         * @param message the literal string to display
         * @return this builder
         */
        public Builder withMessage(String message) {
            mMessage = message;
            return this;
        }

        /**
         * Sets the message to display on the SnackBar
         *
         * @param messageId the resource id of the string to display
         * @return this builder
         */
        public Builder withMessageId(int messageId) {
            mMessage = mContext.getString(messageId);
            return this;
        }

        /**
         * Sets the message to display as the action message
         *
         * @param actionMessage the literal string to display
         * @return this builder
         */
        public Builder withActionMessage(String actionMessage) {
            mActionMessage = actionMessage;
            return this;
        }

        /**
         * Sets the message to display as the action message
         *
         * @param actionMessageResId the resource id of the string to display
         * @return this builder
         */
        public Builder withActionMessageId(int actionMessageResId) {
            if (actionMessageResId > 0) {
                mActionMessage = mContext.getString(actionMessageResId);
            }

            return this;
        }

        /**
         * Sets the action icon
         *
         * @param id the resource id of the icon to display
         * @return this builder
         */
        public Builder withActionIconId(int id) {
            mActionIcon = id;
            return this;
        }

        public Builder withStyle(Style style) {
            mTextColor = getActionTextColor(style);
            return this;
        }

        /**
         * Sets the duration to show the message
         *
         * @param duration the number of milliseconds to show the message
         * @return this builder
         */
        public Builder withDuration(Short duration) {
            mDuration = duration;
            return this;
        }

        /**
         * Sets the {@link ColorStateList} for the action message
         *
         * @param colorId the
         * @return this builder
         */
        public Builder withTextColorId(int colorId) {
            ColorStateList color = mContext.getResources().getColorStateList(colorId);
            mTextColor = color;
            return this;
        }

        /**
         * Sets the {@link ColorStateList} for the SnackBar background
         *
         * @param colorId the SnackBar Background color
         * @return this builder
         */
        public Builder withBackgroundColorId(int colorId) {
            ColorStateList color = mContext.getResources().getColorStateList(colorId);
            mBackgroundColor = color;
            return this;
        }

        /**
         * Sets the height for SnackBar
         *
         * @param height the height of SnackBar
         * @return this builder
         */
        public Builder withSnackBarHeight(int height) {
            mHeight = height;
            return this;
        }

        /**
         * Sets the OnClickListener for the action button
         *
         * @param onClickListener the listener to inform of click events
         * @return this builder
         */
        public Builder withOnClickListener(OnMessageClickListener onClickListener) {
            mSnackBar.setOnClickListener(onClickListener);
            return this;
        }

        /**
         * Sets the visibilityChangeListener for the SnackBar
         *
         * @param visibilityChangeListener the listener to inform of visibility changes
         * @return this builder
         */
        public Builder withVisibilityChangeListener(OnVisibilityChangeListener visibilityChangeListener) {
            mSnackBar.setOnVisibilityChangeListener(visibilityChangeListener);
            return this;
        }

        /**
         * Clears all of the queued SnackBars, animates the message being hidden
         *
         * @return this builder
         */
        public Builder withClearQueued() {
            return withClearQueued(true);
        }

        /**
         * Clears all of the queued SnackBars
         *
         * @param animate whether or not to animate the messages being hidden
         * @return this builder
         */
        public Builder withClearQueued(boolean animate) {
            mAnimateClear = animate;
            mClear = true;
            return this;
        }

        /**
         * Sets the Typeface for the SnackBar
         *
         * @param typeFace the typeface to apply to the SnackBar
         * @return this builder
         */
        public Builder withTypeFace(Typeface typeFace) {
            mTypeFace = typeFace;
            return this;
        }

        public Builder withGravity(int gravity) {
            mGravity = gravity;
            return this;
        }
        /**
         * Shows the first message in the SnackBar
         *
         * @return the SnackBar
         */
        public SnackBar show() {
            Snack message = new Snack(mMessage,
                    (mActionMessage != null ? mActionMessage.toUpperCase() : null),
                    mActionIcon,
                    mDuration,
                    mTextColor != null ? mTextColor : getActionTextColor(Style.DEFAULT),
                    mBackgroundColor != null ? mBackgroundColor :
                            (mType != SnackBarType.NONE) ?
                                mContext.getResources().getColorStateList(R.color.sb_snack_bkgnd_white) :
                                mContext.getResources().getColorStateList(R.color.sb_snack_bkgnd_gray),
                    mHeight != 0 ? mHeight : 0,
                    mTypeFace);

            if (mClear) {
                mSnackBar.clear(mAnimateClear);
            }

            mSnackBar.showMessage(message, mType, mGravity);

            return mSnackBar;
        }

        private ColorStateList getActionTextColor(Style style) {
            switch (style) {
                case ALERT:
                    return mContext.getResources().getColorStateList(R.color.sb_button_text_color_red);
                case INFO:
                    return mContext.getResources().getColorStateList(R.color.sb_button_text_color_yellow);
                case CONFIRM:
                    return mContext.getResources().getColorStateList(R.color.sb_button_text_color_green);
                case DEFAULT:
                    return mContext.getResources().getColorStateList(R.color.sb_default_button_text_color);
                default:
                    return mContext.getResources().getColorStateList(R.color.sb_default_button_text_color);
            }
        }
    }

    private void showMessage(Snack message, SnackBarType type, int gravity) {
        if(type == SnackBarType.SUCCESS) {
            successToastView.startAnim();
        } else if(type == SnackBarType.WARNING) {
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
        } else if(type == SnackBarType.ERROR) {
            errorToastView.startAnim();
        } else if(type == SnackBarType.INFO) {
            infoToastView.startAnim();
        } else if(type == SnackBarType.DEFAULT) {
            defaultToastView.startAnim();
        } else if(type == SnackBarType.CONFUSING) {
            confusingToastView.startAnim();
        }

        mSnackContainer.showSnack(message, mParentView, mVisibilityChangeListener);
        ((FrameLayout.LayoutParams) mParentView.getLayoutParams()).gravity = gravity;
        mParentView.invalidate();
    }

    /**
     * Calculates the height of the SnackBar
     *
     * @return the height of the SnackBar
     */
    public int getHeight() {
        mParentView.measure(View.MeasureSpec.makeMeasureSpec(mParentView.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(mParentView.getHeight(), View.MeasureSpec.AT_MOST));
        return mParentView.getMeasuredHeight();
    }

    /**
     * Getter for the SnackBars parent view
     *
     * @return the parent view
     */
    public View getContainerView() {
        return mParentView;
    }

    private final View.OnClickListener mButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mClickListener != null && mSnackContainer.isShowing()) {
                mClickListener.onMessageClick(mSnackContainer.peek());
            }
            mSnackContainer.hide();
        }
    };

    private SnackBar setOnClickListener(OnMessageClickListener listener) {
        mClickListener = listener;
        return this;
    }

    private SnackBar setOnVisibilityChangeListener(OnVisibilityChangeListener listener) {
        mVisibilityChangeListener = listener;
        return this;
    }

    /**
     * Clears all of the queued messages
     *
     * @param animate whether or not to animate the messages being hidden
     */
    public void clear(boolean animate) {
        mSnackContainer.clearSnacks(animate);
    }

    /**
     * Clears all of the queued messages
     */
    public void clear() {
        clear(true);
    }

    /**
     * Hides all snacks
     */
    public void hide() {
        mSnackContainer.hide();
        clear();
    }

    /**
     * All snacks will be restored using the view from this Snackbar
     */
    public void onRestoreInstanceState(Bundle state) {
        mSnackContainer.restoreState(state, mParentView);
    }

    public Bundle onSaveInstanceState() {
        return mSnackContainer.saveState();
    }

    public enum Style {
        DEFAULT,
        ALERT,
        CONFIRM,
        INFO
    }
}
