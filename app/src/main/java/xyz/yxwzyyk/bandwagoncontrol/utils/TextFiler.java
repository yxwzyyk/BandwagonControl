package xyz.yxwzyyk.bandwagoncontrol.utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import xyz.yxwzyyk.bandwagoncontrol.R;

/**
 * Created by yyk on 12/13/15.
 */
public class TextFiler implements TextWatcher{

    private TextInputLayout mTextInputLayout;
    private Context mContext;

    public TextFiler(Context context) {
        mContext = context;
    }

    public void addTextChangedListener(TextInputLayout textInputLayout) {
        mTextInputLayout = textInputLayout;
        mTextInputLayout.getEditText().addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() <= 0) {
            mTextInputLayout.setErrorEnabled(true);
            mTextInputLayout.setError(mContext.getString(R.string.dialog_host_main_null));
        } else {
            mTextInputLayout.setErrorEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
