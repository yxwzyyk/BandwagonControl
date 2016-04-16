package xyz.yxwzyyk.bandwagoncontrol.utils

/**
 * Created by yyk on 4/9/16.
 */
object HttpURL {
    val HOST_URL = "https://api.kiwivm.it7.net/v1/"
    val GET_INFO = "getLiveServiceInfo"
    val REBOOT = "restart"
    val STOP = "stop"
    val START = "start"
    val BASIC_SHELL = "basicShell/exec"
    val RESET_ROOT_PASSWORD = "resetRootPassword"
    val GET_AVAILABLE_OS = "getAvailableOS"
    val REINSTALL_OS = "reinstallOS"
}

object Constant {
    val GOTO_SHELL_FRAGMENT = 1
    val GOTI_REINSTALL_OS_FRAGMENT = 2
}

