package co.za.lulamile.find.methods;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Methods {

    private Context mContext;

    public Methods(Context context){
        this.mContext = context;
    }

    public boolean checkIfAlreadyHavePermission() {
        int result = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result != PackageManager.PERMISSION_GRANTED;
    }

    public String covertTimeToText(String dataDate) {

        String convTime = "Moments ago";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
            Date pasTime = dateFormat.parse(dataDate);

            Date nowTime = new Date();

            assert pasTime != null;
            long dateDiff = nowTime.getTime() - pasTime.getTime();

            long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
            long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
            long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

            if (second < 0) {
                if (second > -60) {
                    long mSec = (-1 * second);
                    convTime = mSec + "s";
                } else if (minute > -60) {
                    long mMin = (-1 * minute);
                    convTime = mMin + "min";
                } else if (hour > -24) {
                    long mH = (-1 * hour);
                    convTime = mH + "h";
                } else if (day > -7) {
                    long mDay = (-1 * day);
                    convTime = mDay + "d";
                } else {
                    if (day > -29 && day < -7) {
                        long mW = ((-1 * day) / 7);
                        convTime = mW + "w ";
                    } else if (day < -29 && day > -360) {
                        long mM = ((-1 * day) / 30);
                        convTime = mM + "M";
                    } else {
                        long mY = ((-1 * day) / 360);
                        convTime = mY + "y";
                    }
                }
            } else if (second < 60) {
                convTime = second + "s ago";
            } else if (minute < 60) {
                convTime = minute + "min ago ";
            } else if (hour < 24) {
                convTime = hour + "h ago";
            } else if (day >= 7) {
                if (day > 29 && day < 360) {
                    convTime = (day / 30) + "M ago";
                } else if (day > 359) {
                    convTime = (day / 360) + "y ago";
                } else {
                    convTime = (day / 7) + "w ago";
                }
            } else {
                convTime = day + "d ";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Log.e("ConvertTimeE", Objects.requireNonNull(e.getMessage()));
            }
        }
        return convTime;
    }
}

