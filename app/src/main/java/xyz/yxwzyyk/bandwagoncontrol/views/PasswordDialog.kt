package xyz.yxwzyyk.bandwagoncontrol.views

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.utils.TextFiler

/**
 * Created by yyk on 4/16/16.
 */
class PasswordDialog(context: Context, password: String) {
    private val mContext: Context
    private val mAlertDialog: AlertDialog
    private val mPassword: String

    init {
        mContext = context
        mPassword = password
        val mBuilder = AlertDialog.Builder(mContext)
        mBuilder.setTitle(R.string.dialog_password_title)
        mBuilder.setCancelable(false)
        mBuilder.setPositiveButton(R.string.dialog_password_ok,null)
        mBuilder.setView(setView())
        mAlertDialog = mBuilder.create()
    }

    fun show() {
        mAlertDialog.show()
    }

    private fun setView(): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_password, null)

        val password = view.findViewById(R.id.dialog_password) as TextInputLayout?

        password?.editText?.setText(mPassword)

        return view
    }


}