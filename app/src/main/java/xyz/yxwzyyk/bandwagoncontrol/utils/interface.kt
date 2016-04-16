package xyz.yxwzyyk.bandwagoncontrol.utils

import android.view.View

/**
 * Created by yyk on 3/23/16.
 */
interface ItemListener {
    fun onClick(view: View, position: Int)
    fun onLongClick(view: View, position: Int)
    fun onMoreClick(view: View, position: Int)
}

interface ShellListener {
    fun onKey(text: String)
}

interface DialogCallBack {
    fun callBack(b: Boolean)
}

interface FragmentCallBack {
    fun callBack(data: Any)
}
