package xyz.yxwzyyk.bandwagoncontrol.views;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import xyz.yxwzyyk.bandwagoncontrol.R;
import xyz.yxwzyyk.bandwagoncontrol.app.DialogCallBack;
import xyz.yxwzyyk.bandwagoncontrol.db.Host;

/**
 * Created by yyk on 12/13/15.
 */
public class DeleteHostDialog {

    private Context mContext;
    private AlertDialog mAlertDialog;
    private DialogCallBack mCallBack;
    private Host mHost;

    public DeleteHostDialog(Context context) {
        mContext = context;
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setCancelable(false);
        mBuilder.setTitle(R.string.dialog_host_main_remove_title);
        mBuilder.setPositiveButton(R.string.dialog_host_main_ok, (dialog, which) -> {
            mCallBack.onCallBack(true);
        });
        mBuilder.setNegativeButton(R.string.dialog_host_main_cancel, (dialog, which) -> {
            if (mCallBack != null) mCallBack.onCallBack(false);
        });
        mAlertDialog = mBuilder.create();
        mHost = new Host();
    }

    public void setCallBack(DialogCallBack callBack) {
        mCallBack = callBack;
    }

    public void show() {
        mAlertDialog.show();
    }
}
