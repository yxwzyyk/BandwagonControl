package xyz.yxwzyyk.bandwagoncontrol.utils

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher

import xyz.yxwzyyk.bandwagoncontrol.R

/**
 * Created by yyk on 4/8/16.
 */
class TextFiler(private val mContext: Context) : TextWatcher {

    private var mTextInputLayout: TextInputLayout? = null

    fun addTextChangedListener(textInputLayout: TextInputLayout) {
        mTextInputLayout = textInputLayout
        mTextInputLayout!!.editText!!.addTextChangedListener(this)
    }


    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.length <= 0) {
            mTextInputLayout!!.isErrorEnabled = true
            mTextInputLayout!!.error = mContext.getString(R.string.text_input_null)
        } else {
            mTextInputLayout!!.isErrorEnabled = false
        }
    }

    override fun afterTextChanged(s: Editable) {

    }
}