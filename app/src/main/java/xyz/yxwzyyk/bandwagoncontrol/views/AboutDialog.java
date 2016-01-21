package xyz.yxwzyyk.bandwagoncontrol.views;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import xyz.yxwzyyk.bandwagoncontrol.R;
import xyz.yxwzyyk.bandwagoncontrol.utils.Tools;

/**
 * Created by yyk on 12/4/15.
 */
public class AboutDialog {
    private Context mContext;
    private AlertDialog.Builder mBuilder;

    public AboutDialog(Context context){
        mContext = context;
    }

    public AboutDialog builder() {
        mBuilder = new AlertDialog.Builder(mContext);
        String title = mContext.getString(R.string.app_name) + " V" + Tools.getVersionName(mContext);
        mBuilder.setTitle(title);
        mBuilder.setMessage(R.string.dialog_host_main_about);
        mBuilder.setPositiveButton(R.string.dialog_host_main_ok, (dialog, which) -> {

        });
        return this;
    }

    public void show() {
        if (mBuilder != null) {
            mBuilder.show();
        }
    }
}
