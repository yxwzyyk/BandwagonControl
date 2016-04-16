package xyz.yxwzyyk.bandwagoncontrol.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.utils.ItemListener

/**
 * Created by yyk on 4/16/16.
 */
class OSAdapter(context: Context, list: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mContext: Context
    private val mList: List<String>

    init {
        mList = list
        mContext = context
    }

    var mListener: ItemListener? = null

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.item_os, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as VH).title.text = mList[position]
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView

        init {
            title = itemView.findViewById(R.id.os_title) as TextView

            itemView.setOnClickListener { v ->
                mListener?.onClick(v, adapterPosition)
            }

            itemView.setOnLongClickListener { v ->
                mListener?.onLongClick(v, adapterPosition)
                true
            }
        }
    }
}