package xyz.yxwzyyk.bandwagoncontrol.bean

/**
 * Created by yyk on 4/10/16.
 */
class Shell {
    var type: Int = 0
    var message: String = ""
    var head: String = ""

    constructor(type: Int, head: String, message: String) {
        this.type = type
        this.message = message
        this.head = head
    }

    object Type {
        val SEND = 1
        val ACCEPT = 2
    }
}
