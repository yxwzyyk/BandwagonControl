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
import kotlinx.android.synthetic.main.fragment_locations.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.adapters.LocationsAdapter
import xyz.yxwzyyk.bandwagoncontrol.bean.Locations
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.utils.HostRequest
import xyz.yxwzyyk.bandwagoncontrol.utils.ItemListener

/**
 * Created by yyk on 4/16/16.
 */
class LocationsFragment(host: Host) : Fragment() {
    private var mHost: Host
    private val mHostRequest: HostRequest

    init {
        mHost = host
        mHostRequest = HostRequest(mHost.veid, mHost.key)
    }

    private var mAdapter: LocationsAdapter? = null
    private var mList: List<String>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        locations_progressBar_loading.visibility = View.VISIBLE
        locations_textView_message.visibility = View.VISIBLE
        locations_recyclerView.visibility = View.GONE
        locations_textView_message.setText(R.string.locations_load)
        async() {
            val json = mHostRequest.getLocations()
            val locations = Gson().fromJson(json, Locations::class.java)
            uiThread {
                if (locations.error == 0) {
                    locations_progressBar_loading.visibility = View.GONE
                    locations_textView_message.visibility = View.GONE
                    locations_recyclerView.visibility = View.VISIBLE

                    mList = locations.locations

                    mAdapter = LocationsAdapter(context, locations)
                    locations_recyclerView.layoutManager = LinearLayoutManager(context)
                    locations_recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(context).size(1).build())

                    mAdapter?.mListener = object : ItemListener {
                        override fun onMoreClick(view: View, position: Int) {

                        }

                        override fun onLongClick(view: View, position: Int) {
                            showPopupMenu(view, position)
                        }

                        override fun onClick(view: View, position: Int) {
                            showPopupMenu(view, position)
                        }
                    }

                    locations_recyclerView.adapter = mAdapter

                } else {
                    locations_progressBar_loading.visibility = View.GONE
                    locations_textView_message.setText(R.string.locations_error)
                }
            }
        }
    }

    fun showPopupMenu(v: View, position: Int) {
        var popupMenu = PopupMenu(context, v);
        popupMenu.menuInflater.inflate(R.menu.popup_locations_menu, popupMenu.menu);
        popupMenu.setOnMenuItemClickListener { item ->
            mHostRequest.setLocations(mList!![position])
            context.toast(R.string.locations_migration)
            true
        }
        popupMenu.show(); // 显示弹出菜单
    }
}