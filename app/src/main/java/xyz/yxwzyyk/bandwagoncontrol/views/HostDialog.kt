package xyz.yxwzyyk.bandwagoncontrol.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.utils.DialogCallBack
import xyz.yxwzyyk.bandwagoncontrol.utils.TextFiler

/**
 * Created by yyk on 4/8/16.
 */
class HostDialog(context: Context, title: Int) : View.OnClickListener {

    private var mInputTitle: TextInputLayout? = null
    private var mInputId: TextInputLayout? = null
    private var mInputKey: TextInputLayout? = null
    private var mButtonOk: Button? = null
    private var mButtonCancel: Button? = null
    private var mButtonNotice: Button? = null

    private val mContext: Context
    private val mAlertDialog: AlertDialog

    var dialogCallBack: DialogCallBack? = null

    init {
        mContext = context
        val mBuilder = AlertDialog.Builder(mContext)
        mBuilder.setTitle(title)
        mBuilder.setView(setView())
        mAlertDialog = mBuilder.create()
    }

    fun setHost(host: Host) {
        mInputId?.editText?.setText(host.veid)
        mInputTitle?.editText?.setText(host.title)
        mInputKey?.editText?.setText(host.key)
    }

    fun getHost(): Host {
        val host = Host(mInputTitle?.editText?.text.toString(), mInputId?.editText?.text.toString(), mInputKey?.editText?.text.toString())
        return host
    }

    fun show() {
        mAlertDialog.show()
    }

    fun dismiss() {
        mAlertDialog.dismiss()
    }

    private fun setView(): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_host, null)

        mInputTitle = view.findViewById(R.id.dialog_host_textInput_title) as TextInputLayout?
        mInputId = view.findViewById(R.id.dialog_host_textInput_id) as TextInputLayout?
        mInputKey = view.findViewById(R.id.dialog_host_textInput_key) as TextInputLayout?

        mButtonOk = view.findViewById(R.id.dialog_host_button_ok) as Button?
        mButtonCancel = view.findViewById(R.id.dialog_host_button_cancel) as Button?
        mButtonNotice = view.findViewById(R.id.dialog_host_notice) as Button?

        mButtonOk?.setOnClickListener(this)
        mButtonCancel?.setOnClickListener(this)
        mButtonNotice?.setOnClickListener(this)

        TextFiler(mContext).addTextChangedListener(mInputTitle!!)
        TextFiler(mContext).addTextChangedListener(mInputId!!)
        TextFiler(mContext).addTextChangedListener(mInputKey!!)
        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dialog_host_notice -> {
                val uri = Uri.parse("https://bandwagonhost.com/clientarea.php?action=products");
                val intent = Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
            R.id.dialog_host_button_cancel -> {
                dialogCallBack?.callBack(false)
            }
            R.id.dialog_host_button_ok -> {
                if(mInputTitle?.editText?.text?.toString()?.isEmpty()!!) {
                    mInputTitle!!.isErrorEnabled = true
                    mInputTitle!!.error = mContext.getString(R.string.text_input_null)
                    return
                }
                if(mInputId?.editText?.text?.toString()?.isEmpty()!!) {
                    mInputId!!.isErrorEnabled = true
                    mInputId!!.error = mContext.getString(R.string.text_input_null)
                    return
                }
                if(mInputKey?.editText?.text?.toString()?.isEmpty()!!) {
                    mInputKey!!.isErrorEnabled = true
                    mInputKey!!.error = mContext.getString(R.string.text_input_null)
                    return
                }


                dialogCallBack?.callBack(true)
            }
        }
    }
}