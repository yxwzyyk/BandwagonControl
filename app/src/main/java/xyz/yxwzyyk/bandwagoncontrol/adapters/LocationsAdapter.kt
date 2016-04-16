package xyz.yxwzyyk.bandwagoncontrol.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import xyz.yxwzyyk.bandwagoncontrol.R
import xyz.yxwzyyk.bandwagoncontrol.bean.Locations
import xyz.yxwzyyk.bandwagoncontrol.utils.ItemListener

/**
 * Created by yyk on 4/16/16.
 */
class LocationsAdapter(context: Context, locations: Locations) : RecyclerView.Adapter<LocationsAdapter.VH>() {
    private val mLocations: Locations
    private val mContext: Context

    init {
        mLocations = locations
        mContext = context
    }

    var mListener: ItemListener? = null


    override fun getItemCount(): Int {
        return mLocations.locations.size
    }

    override fun onBindViewHolder(holder: LocationsAdapter.VH?, position: Int) {
        var list = mLocations.locations
        var title = list[position]
        var descriptions = ""
        holder?.title?.text = title
        when (title) {
            Locations.USCA_2 -> {
                descriptions = mLocations.descriptions.uscA_2
            }
            Locations.USCA_FMT -> {
                descriptions = mLocations.descriptions.uscA_FMT
            }
            Locations.USAZ_2 -> {
                descriptions = mLocations.descriptions.usaZ_2
            }
            Locations.USFL_2 -> {
                descriptions = mLocations.descriptions.usfL_2
            }
            Locations.EUNL_3 -> {
                descriptions = mLocations.descriptions.eunL_3
            }
        }
        holder?.descriptions?.text = descriptions

        if (mLocations.currentLocation.equals(title)) {
            holder?.image?.visibility = View.VISIBLE
            holder?.itemView?.setOnClickListener(null)
            holder?.itemView?.setOnLongClickListener(null)
        } else {
            holder?.image?.visibility = View.GONE
            holder?.itemView?.setOnClickListener { v ->
                mListener?.onClick(v, position)
            }
            holder?.itemView?.setOnLongClickListener { v ->
                mListener?.onClick(v, position)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LocationsAdapter.VH? {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.item_locations, parent, false)
        return VH(view)
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var descriptions: TextView
        internal var image: ImageView

        init {
            title = itemView.findViewById(R.id.location_title) as TextView
            descriptions = itemView.findViewById(R.id.location_descriptions) as TextView
            image = itemView.findViewById(R.id.location_image) as ImageView
        }
    }
}