package xyz.yxwzyyk.bandwagoncontrol.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.adapters.HostAdapter
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.db.HostTable
import xyz.yxwzyyk.bandwagoncontrol.db.database
import xyz.yxwzyyk.bandwagoncontrol.utils.DialogCallBack
import xyz.yxwzyyk.bandwagoncontrol.utils.FragmentCallBack
import xyz.yxwzyyk.bandwagoncontrol.utils.ItemListener
import xyz.yxwzyyk.bandwagoncontrol.utils.parseList
import xyz.yxwzyyk.bandwagoncontrol.views.HostDialog
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class ListFragment : Fragment() {
    val TAG = this.javaClass.name

    private var mList: List<Host>? = null
    private var mAdapter: HostAdapter? = null

    var callback: FragmentCallBack? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        mList = context.database.use {
            select(HostTable.NAME).parseList { Host(HashMap(it)) }
        }

        mAdapter = HostAdapter(context, mList!!)

        list_recyclerView.layoutManager = LinearLayoutManager(context)
        list_recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(context).size(1).build())

        mAdapter?.mListener = object : ItemListener {
            override fun onClick(view: View, position: Int) {
                callback?.callBack(mList?.get(position)!!)
            }

            override fun onLongClick(view: View, position: Int) {
                view.post {
                    run {
                        showPopupMenu(view, position)
                    }
                }
            }

            override fun onMoreClick(view: View, position: Int) {
                view.post {
                    run {
                        showPopupMenu(view, position)
                    }
                }
            }

        }
        list_recyclerView.adapter = mAdapter

        list_recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) {
                    list_fab.show(true)
                } else if (dy > 0) {
                    list_fab.hide(true)
                }
            }
        })


        list_fab.setOnClickListener { v ->
            val dialog = HostDialog(context, R.string.dialog_host_title_add)
            dialog.dialogCallBack = object : DialogCallBack {
                override fun callBack(b: Boolean) {
                    if (b) {
                        val host = dialog.getHost()
                        host._id = context.database.use {
                            insert(
                                    HostTable.NAME,
                                    HostTable.TITLE to host.title,
                                    HostTable.VEID to host.veid,
                                    HostTable.KEY to host.key
                            )
                        }
                        (mList as ArrayList).add(host)
                        mAdapter?.notifyItemInserted(mAdapter?.itemCount!!)
                    }
                    dialog.dismiss()
                }
            }
            dialog.show()

        }
    }

    fun showPopupMenu(v: View, position: Int) {

        var popupMenu = PopupMenu(context, v);
        popupMenu.menuInflater.inflate(R.menu.popup_list_menu, popupMenu.menu);
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_list_update -> {
                    val dialog = HostDialog(context, R.string.dialog_host_title_update)
                    dialog.setHost(mList?.get(position)!!)
                    dialog.dialogCallBack = object : DialogCallBack {
                        override fun callBack(b: Boolean) {
                            if (b) {
                                val host = dialog.getHost()
                                context.database.use {
                                    update(HostTable.NAME,
                                            HostTable.TITLE to host.title,
                                            HostTable.VEID to host.veid,
                                            HostTable.KEY to host.key)
                                            .where("_id = {id}", "id" to (mList?.get(position) as Host)._id).exec()
                                }
                                dialog.dismiss()
                                (mList?.get(position) as Host).title = host.title
                                (mList?.get(position) as Host).veid = host.veid
                                (mList?.get(position) as Host).key = host.key
                                mAdapter?.notifyItemChanged(position)
                            }
                            dialog.dismiss()
                        }
                    }
                    dialog.show()
                }
                R.id.menu_list_remove -> {
                    context.database.use {
                        delete(HostTable.NAME, "_id = {_id}", HostTable.ID to mList?.get(position)?._id!!)
                    }
                    (mList as ArrayList).removeAt(position)
                    mAdapter?.notifyItemRemoved(position)
                }
            }
            true
        }
        popupMenu.show(); // 显示弹出菜单
    }

}
