package xyz.yxwzyyk.bandwagoncontrol.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_shell.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.adapters.ShellAdapter
import xyz.yxwzyyk.bandwagoncontrol.bean.Exec
import xyz.yxwzyyk.bandwagoncontrol.bean.Shell
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.utils.HostRequest
import xyz.yxwzyyk.bandwagoncontrol.utils.ShellListener
import java.util.*

/**
 * Created by yyk on 4/10/16.
 */
class ShellFragment(host: Host) : Fragment() {
    private val mHost: Host
    private val mList: ArrayList<Shell>


    private var mPath = "[root /]#  "
    private var mAdapter: ShellAdapter? = null
    private val mHostRequest: HostRequest

    init {
        mHost = host
        mList = ArrayList()
        mList.add(Shell(Shell.Type.SEND, mPath, ""))
        mHostRequest = HostRequest(mHost.veid, mHost.key)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_shell, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        shell_recyclerView.layoutManager = LinearLayoutManager(context)

        mAdapter = ShellAdapter(context, mList)
        mAdapter?.mListener = object : ShellListener {
            override fun onKey(text: String) {
                mList[mList.size - 1].head += text
                mAdapter?.notifyDataSetChanged()

                async() {
                    var json = mHostRequest.basicShell(text)
                    var gson = Gson()
                    var exec = gson.fromJson(json, Exec::class.java)

                    uiThread {
                        if (exec == null) {
                            mList.add(Shell(Shell.Type.ACCEPT, "", "Network Error!"))
                        } else {
                            mList.add(Shell(Shell.Type.ACCEPT, "", exec.message))
                        }
                        mList.add(Shell(Shell.Type.SEND, mPath, ""))
                        mAdapter?.notifyDataSetChanged()
                        shell_recyclerView.scrollToPosition(mAdapter?.itemCount!! - 1);
                    }
                }

            }

        }
        shell_recyclerView.adapter = mAdapter
        shell_recyclerView.setOnTouchListener { view, event ->
            if(event.action == MotionEvent.ACTION_UP)
            mAdapter?.putInput()
            false
        }
    }
}