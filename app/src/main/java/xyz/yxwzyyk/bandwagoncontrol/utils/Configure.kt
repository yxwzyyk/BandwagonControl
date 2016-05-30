package xyz.yxwzyyk.bandwagoncontrol.utils

/**
 * Created by yyk on 4/9/16.
 */
object HttpURL {
    val HOST_URL = "https://api.64clouds.com/v1/"
    val GET_INFO = "getLiveServiceInfo"
    val REBOOT = "restart"
    val STOP = "stop"
    val START = "start"
    val BASIC_SHELL = "basicShell/exec"
    val RESET_ROOT_PASSWORD = "resetRootPassword"
    val GET_AVAILABLE_OS = "getAvailableOS"
    val REINSTALL_OS = "reinstallOS"
    val GET_LOCATIONS = "migrate/getLocations"
    val SET_LOCATIONS = "migrate/start"
}

object Constant {
    val GOTO_SHELL_FRAGMENT = 1
    val GOTO_REINSTALL_OS_FRAGMENT = 2
    val GOTO_LOCATIONS_FRAGMENT = 3
}

