package com.kevadiyakrunalk.snackbar;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;

class Snack implements Parcelable {
    final String mMessage;
    final String mActionMessage;
    final int mActionIcon;
    final short mDuration;
    final ColorStateList mBtnTextColor;
    final ColorStateList mBackgroundColor;
    final int mHeight;
    Typeface mTypeface;

    Snack(String message, String actionMessage, int actionIcon,
          short duration, ColorStateList textColor,
          ColorStateList backgroundColor, int height, Typeface typeFace) {

        mMessage = message;
        mActionMessage = actionMessage;
        mActionIcon = actionIcon;
        mDuration = duration;
        mBtnTextColor = textColor;
        mBackgroundColor = backgroundColor;
        mHeight = height;
        mTypeface = typeFace;
    }

    Snack(Parcel p) {
        mMessage = p.readString();
        mActionMessage = p.readString();
        mActionIcon = p.readInt();
        mDuration = (short) p.readInt();
        mBtnTextColor = p.readParcelable(p.getClass().getClassLoader());
        mBackgroundColor = p.readParcelable(p.getClass().getClassLoader());
        mHeight = p.readInt();
        mTypeface = (Typeface) p.readValue(p.getClass().getClassLoader());
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mMessage);
        out.writeString(mActionMessage);
        out.writeInt(mActionIcon);
        out.writeInt((int) mDuration);
        out.writeParcelable(mBtnTextColor, 0);
        out.writeParcelable(mBackgroundColor, 0);
        out.writeInt(mHeight);
        out.writeValue(mTypeface);
    }

    public int describeContents() {
        return 0;
    }

    public static final Creator<Snack> CREATOR = new Creator<Snack>() {
        public Snack createFromParcel(Parcel in) {
            return new Snack(in);
        }

        public Snack[] newArray(int size) {
            return new Snack[size];
        }
    };
}
