package xyz.yxwzyyk.bandwagoncontrol.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_reinstall_os.*
import org.jetbrains.anko.async
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.adapters.OSAdapter
import xyz.yxwzyyk.bandwagoncontrol.bean.AvailableOS
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.utils.HostRequest
import xyz.yxwzyyk.bandwagoncontrol.utils.ItemListener

/**
 * Created by yyk on 4/16/16.
 */
class ReinstallOSFragment(host: Host) : Fragment() {
    private var mHost: Host
    private val mHostRequest: HostRequest

    init {
        mHost = host
        mHostRequest = HostRequest(mHost.veid, mHost.key)
    }

    private var mAdapter: OSAdapter? = null
    private var mList: List<String>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_reinstall_os, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        context.longToast(R.string.os_warning)
        reinstallOS_progressBar_loading.visibility = View.VISIBLE
        reinstallOS_textView_message.visibility = View.VISIBLE
        reinstallOS_recyclerView.visibility = View.GONE
        reinstallOS_textView_message.setText(R.string.os_load)
        async() {
            val json = mHostRequest.getAvailableOS()
            val availableOS = Gson().fromJson(json, AvailableOS::class.java)
            uiThread {
                if (availableOS.error == 0) {

                    reinstallOS_progressBar_loading.visibility = View.GONE
                    reinstallOS_textView_message.visibility = View.GONE
                    reinstallOS_recyclerView.visibility = View.VISIBLE

                    mList = availableOS.templates
                    mAdapter = OSAdapter(context, mList!!)
                    reinstallOS_recyclerView.layoutManager = LinearLayoutManager(context)
                    reinstallOS_recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(context).size(1).build())

                    mAdapter?.mListener = object : ItemListener {
                        override fun onMoreClick(view: View, position: Int) {

                        }

                        override fun onLongClick(view: View, position: Int) {
                            context.longToast(R.string.os_warning)
                            showPopupMenu(view, position)
                        }

                        override fun onClick(view: View, position: Int) {
                            context.longToast(R.string.os_warning)
                            showPopupMenu(view, position)
                        }
                    }

                    reinstallOS_recyclerView.adapter = mAdapter
                } else {
                    reinstallOS_progressBar_loading.visibility = View.GONE
                    reinstallOS_textView_message.setText(R.string.os_error)
                }
            }
        }
    }

    fun showPopupMenu(v: View, position: Int) {
        var popupMenu = PopupMenu(context, v);
        popupMenu.menuInflater.inflate(R.menu.popup_os_menu, popupMenu.menu);
        popupMenu.setOnMenuItemClickListener { item ->
            var os = mList!![position]
            var install = getText(R.string.os_install)
            mHostRequest.reinstallOS(os)
            context.toast("$install$os")
            true
        }
        popupMenu.show(); // 显示弹出菜单
    }
}