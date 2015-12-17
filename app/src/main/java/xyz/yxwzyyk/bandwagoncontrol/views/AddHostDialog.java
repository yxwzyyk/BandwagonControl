package xyz.yxwzyyk.bandwagoncontrol.views;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import xyz.yxwzyyk.bandwagoncontrol.R;
import xyz.yxwzyyk.bandwagoncontrol.app.DialogCallBack;
import xyz.yxwzyyk.bandwagoncontrol.db.Host;
import xyz.yxwzyyk.bandwagoncontrol.utils.TextFiler;

/**
 * Created by yyk on 12/13/15.
 */
public class AddHostDialog implements View.OnClickListener {
    private TextInputLayout mInputTitle;
    private TextInputLayout mInputId;
    private TextInputLayout mInputKey;
    private Button mButtonOk;
    private Button mButtonCancel;

    private Context mContext;
    private AlertDialog mAlertDialog;
    private DialogCallBack mCallBack;
    private Host mHost;

    public AddHostDialog(Context context) {
        mContext = context;
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setCancelable(false);
        mBuilder.setTitle(R.string.dialog_host_main_add_title);
        mBuilder.setView(setView());
        mAlertDialog = mBuilder.create();
        mHost = new Host();
    }
    public void setCallBack(DialogCallBack callBack) {
        mCallBack = callBack;
    }

    public void show() {
        mAlertDialog.show();
    }

    public Host getHost() {
        return mHost;
    }

    private View setView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_host_main, null);
        mInputTitle = (TextInputLayout) view.findViewById(R.id.dialog_host_main_textInput_title);
        mInputId = (TextInputLayout) view.findViewById(R.id.dialog_host_main_textInput_id);
        mInputKey = (TextInputLayout) view.findViewById(R.id.dialog_host_main_textInput_key);
        mButtonOk = (Button) view.findViewById(R.id.dialog_host_main_button_ok);
        mButtonCancel = (Button) view.findViewById(R.id.dialog_host_main_button_cancel);

        mButtonOk.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);

        new TextFiler(mContext).addTextChangedListener(mInputTitle);
        new TextFiler(mContext).addTextChangedListener(mInputId);
        new TextFiler(mContext).addTextChangedListener(mInputKey);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_host_main_button_ok) {
            String title = mInputTitle.getEditText().getText().toString().trim();
            String id = mInputId.getEditText().getText().toString().trim();
            String key = mInputKey.getEditText().getText().toString().trim();
            if (title.isEmpty()) {
                mInputTitle.setErrorEnabled(true);
                mInputTitle.setError(mContext.getString(R.string.dialog_host_main_null));
                return;
            } else if (id.isEmpty()) {
                mInputId.setErrorEnabled(true);
                mInputId.setError(mContext.getString(R.string.dialog_host_main_null));
                return;
            } else if (key.isEmpty()) {
                mInputKey.setErrorEnabled(true);
                mInputKey.setError(mContext.getString(R.string.dialog_host_main_null));
                return;
            }
            mHost.title = title;
            mHost.veid = id;
            mHost.key = key;
            mCallBack.onCallBack(true);
            mAlertDialog.dismiss();
        } else {
            mAlertDialog.dismiss();
            mCallBack.onCallBack(false);
        }
    }
}
