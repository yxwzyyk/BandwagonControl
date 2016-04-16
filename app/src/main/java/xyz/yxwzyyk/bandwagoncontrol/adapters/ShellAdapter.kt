package xyz.yxwzyyk.bandwagoncontrol.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.bean.Shell
import xyz.yxwzyyk.bandwagoncontrol.utils.ShellListener

/**
 * Created by yyk on 4/10/16.
 */
class ShellAdapter(context: Context, list: List<Shell>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mContext: Context
    private val mList: List<Shell>
    var mListener: ShellListener? = null
    var mInput: EditText? = null

    init {
        mContext = context
        mList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val layoutInflater = LayoutInflater.from(mContext)
        if (viewType == Shell.Type.SEND) {
            val view = layoutInflater.inflate(R.layout.item_shell_send, parent, false)
            return Send(view)
        } else {
            val view = layoutInflater.inflate(R.layout.item_shell_accept, parent, false)
            return Accept(view)
        }
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (mList[position].type) {
            Shell.Type.SEND -> {
                val send = holder as Send
                send.head.text = mList[position].head

                if (position + 1 == mList.size) {
                    send.input.visibility = View.VISIBLE
                    mInput = send.input
                    putInput()
                } else {
                    send.input.visibility = View.GONE
                }
            }
            Shell.Type.ACCEPT -> {
                val accept = holder as Accept
                accept.message.text = mList[position].message
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].type
    }

    fun putInput() = async() {
        Thread.sleep(500)
        uiThread {
            mInput?.isFocusable = true
            mInput?.isFocusableInTouchMode = true
            mInput?.requestFocus()
            val inputManager = mInput?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(mInput, 0);
        }
    }


    inner class Send(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var head: TextView
        internal var input: EditText

        init {
            head = itemView.findViewById(R.id.item_shell_head) as TextView
            input = itemView.findViewById(R.id.item_shell_input) as EditText


            input.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    if (!input.text.isEmpty()) {
                        mListener?.onKey(input.text.toString())
                    }
                    input.setText("")
                }
                false
            }

        }
    }

    inner class Accept(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var message: TextView

        init {
            message = itemView.findViewById(R.id.item_shell_message) as TextView
        }
    }
}