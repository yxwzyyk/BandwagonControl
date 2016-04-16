package xyz.yxwzyyk.bandwagoncontrol.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.utils.DialogCallBack
import xyz.yxwzyyk.bandwagoncontrol.utils.TextFiler
import xyz.yxwzyyk.bandwagoncontrol.utils.Tools

/**
 * Created by yyk on 4/8/16.
 */
class AboutDialog(context: Context) {

    private val mContext: Context
    private val mAlertDialog: AlertDialog


    init {
        mContext = context
        val mBuilder = AlertDialog.Builder(mContext)
        mBuilder.setTitle(R.string.about_title)
        mBuilder.setView(setView())
        mAlertDialog = mBuilder.create()
    }

    fun show(){
        mAlertDialog.show()
    }


    private fun setView(): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_about, null)
        var versionView = view.findViewById(R.id.about_version) as TextView?

        val version = Tools.getVersionName(mContext)
        val str = mContext.getText(R.string.about_version)
        versionView?.text = "$str$version"

        return view
    }

}