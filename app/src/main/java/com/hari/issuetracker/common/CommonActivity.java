package com.hari.issuetracker.common;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

import com.hari.issuetracker.R;

/**
 * Created by sivaavatar on 2/18/2018.
 */

public class CommonActivity extends AppCompatActivity {
    public static boolean isNetworkEnabled(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() == null ? false : cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public boolean retrieveNetworkCheck() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("No internet available");
        builder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();
        return true;
    }
}
