package xyz.yxwzyyk.bandwagoncontrol.db

import java.util.*

/**
 * Created by yyk on 3/23/16.
 */
class Host(var map: MutableMap<String, Any?>) {

    var _id: Long by map
    var title: String by map
    var veid: String by  map
    var key: String by map


    constructor(title: String, veid: String, key: String) : this(HashMap()) {
        this.title = title
        this.veid = veid
        this.key = key
    }
}