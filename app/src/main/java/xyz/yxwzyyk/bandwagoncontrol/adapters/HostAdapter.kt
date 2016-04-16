package xyz.yxwzyyk.bandwagoncontrol.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.db.Host
import xyz.yxwzyyk.bandwagoncontrol.utils.ItemListener

/**
 * Created by yyk on 3/23/16.
 */
class HostAdapter(context: Context, list: List<Host>) : RecyclerView.Adapter<HostAdapter.VH>() {
    private val mList: List<Host>
    private val mContext: Context

    init {
        mList = list
        mContext = context
    }

    var mListener: ItemListener? = null


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH? {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.item_host, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH?, position: Int) {
        holder?.title?.text = mList[position].title
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var more: ImageView

        init {
            title = itemView.findViewById(R.id.item_title) as TextView
            more = itemView.findViewById(R.id.item_img_more) as ImageView

            itemView.setOnClickListener { v ->
                mListener?.onClick(v, adapterPosition)
            }

            itemView.setOnLongClickListener { v ->
                mListener?.onLongClick(v, adapterPosition)
                true
            }

            more.setOnClickListener { v ->
                mListener?.onMoreClick(v, adapterPosition)
            }
        }
    }
}