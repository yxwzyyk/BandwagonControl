package xyz.yxwzyyk.bandwagoncontrol.activities


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_host.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.bean.Info
import xyz.yxwzyyk.bandwagoncontrol.bean.InfoParse
import xyz.yxwzyyk.bandwagoncontrol.bean.Password
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.utils.Constant
import xyz.yxwzyyk.bandwagoncontrol.utils.FragmentCallBack
import xyz.yxwzyyk.bandwagoncontrol.utils.HostRequest
import xyz.yxwzyyk.bandwagoncontrol.views.PasswordDialog

/**
 * Created by yyk on 2/29/16.
 */

class HostFragment(host: Host) : Fragment() {
    private var mHost: Host
    private val mHostRequest: HostRequest

    init {
        mHost = host
        mHostRequest = HostRequest(mHost.veid, mHost.key)
    }

    var callback: FragmentCallBack? = null
    var mScrollView: NestedScrollView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_host, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mScrollView = view?.findViewById(R.id.host_nestedScrollView) as NestedScrollView?
        initView()
        setAd()
        loadData()
    }

    private fun setAd() {
        var adRequest = AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.visibility = View.GONE;

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                try {
                    adView.visibility = View.VISIBLE;
                } catch (e: Exception) {

                }
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                super.onAdFailedToLoad(errorCode)
                try {
                    adView.visibility = View.GONE;
                } catch (e: Exception) {

                }
            }
        }

    }

    private fun loadData() {
        host_fab.visibility = View.GONE
        host_nestedScrollView.visibility = View.GONE
        host_progressBar_loading.visibility = View.VISIBLE
        host_textView_message.visibility = View.VISIBLE
        host_textView_message.setText(R.string.host_message_load)
        async() {
            val jsonStr = mHostRequest.getInfo()
            var info: Info? = null
            if (jsonStr != null) {
                val gson = Gson()
                info = gson.fromJson(jsonStr, Info::class.java)
            }
            uiThread {
                try {
                    if (info == null) {
                        host_progressBar_loading.visibility = View.INVISIBLE
                        host_textView_message.visibility = View.VISIBLE
                        host_textView_message.setText(R.string.host_message_error)
                    } else if (info?.error != 0) {
                        host_progressBar_loading.visibility = View.INVISIBLE
                        host_textView_message.visibility = View.VISIBLE
                        host_textView_message.setText(R.string.host_message_authenticate)
                    } else {
                        val infoParse = InfoParse(info!!)
                        host_textView_host.text = mHost.title
                        host_textView_plan.text = infoParse.plan
                        host_textView_hostname.text = infoParse.hostname
                        host_textView_location.text = infoParse.location
                        host_textView_os.text = infoParse.os
                        host_textView_ip.text = infoParse.ip
                        host_textView_ssh.text = infoParse.sshPort
                        host_textView_status.text = infoParse.status
                        host_textView_cpu.text = infoParse.cpu
                        host_textView_ram_total.text = infoParse.ram
                        host_textView_ram_use.text = infoParse.ramUse
                        host_progressBar_ram.progress = infoParse.ramPercentage
                        host_textView_swap_total.text = infoParse.swap
                        host_textView_swap_use.text = infoParse.swapUse
                        host_progressBar_swap.progress = infoParse.swapPercentage
                        host_textView_disk_total.text = infoParse.disk
                        host_textView_disk_use.text = infoParse.diskUse
                        host_progressBar_disk.progress = infoParse.diskPercentage
                        host_textView_bandwidth_total.text = infoParse.bandwidth
                        host_textView_bandwidth_use.text = infoParse.bandwidthUse
                        host_progressBar_bandwidth.progress = infoParse.bandwidthPercentage
                        host_textView_resets.text = infoParse.resets
                        host_start.setOnClickListener { v ->
                            async() { mHostRequest.start() }
                            context.toast(R.string.host_toast_start)
                        }
                        host_reboot.setOnClickListener { v ->
                            async() { mHostRequest.reboot() }
                            context.toast(R.string.host_toast_reboot)
                        }
                        host_stop.setOnClickListener { v ->
                            async() { mHostRequest.stop() }
                            context.toast(R.string.host_toast_stop)
                        }
                        host_progressBar_loading.visibility = View.GONE
                        host_fab.visibility = View.VISIBLE
                        host_nestedScrollView.visibility = View.VISIBLE
                        host_textView_message.visibility = View.GONE

                    }
                } catch (e: Exception) {
                }
            }
        }

    }

    private fun initView() {
        host_nestedScrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                if (scrollY < oldScrollY) {
                    host_fab.showMenu(true);
                } else {
                    host_fab.hideMenu(true);
                }
            }

        })
        host_fab_menu_refresh.setOnClickListener { v ->
            loadData()
            host_fab.close(true)
        }


        host_fab_menu_reinstallOS.setOnClickListener { v->
            callback?.callBack(Constant.GOTO_REINSTALL_OS_FRAGMENT)
            host_fab.close(true)
        }

        host_fab_menu_locations.setOnClickListener { v->
            callback?.callBack(Constant.GOTO_LOCATIONS_FRAGMENT)
            host_fab.close(true)
        }

        host_fab_menu_shell.setOnClickListener { v ->
            callback?.callBack(Constant.GOTO_SHELL_FRAGMENT)
            host_fab.close(true)
        }

        host_fab_menu_password.setOnClickListener { v ->
            host_fab.close(true)
            async() {
                val json = mHostRequest.resetRootPassword()
                uiThread {
                    val password = Gson().fromJson(json, Password::class.java)
                    if (password.error == 0) {
                        PasswordDialog(context, password.password).show()
                    } else {
                        PasswordDialog(context, context.getString(R.string.dialog_password_error)).show()
                    }

                }
            }
        }
    }

}