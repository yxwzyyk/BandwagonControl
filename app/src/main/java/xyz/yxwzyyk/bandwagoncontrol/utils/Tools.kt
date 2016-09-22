package xyz.yxwzyyk.bandwagoncontrol.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

import java.math.BigDecimal

/**
 * Created by yyk on 4/10/16.
 */
object Tools {
    /**
     * 获取应用版本号
     * @param context
     * *
     * @return
     */
    fun getVersionName(context: Context): String {
        // 获取packagemanager的实例
        val packageManager = context.packageManager
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        var packInfo: PackageInfo? = null
        try {
            packInfo = packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        val version = packInfo!!.versionName
        return version
    }

    /**
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节)和gb
     * @param bytes
     * *
     * @return
     */
    fun getPrintSize(number: Long): String {
        var size = number
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return (size).toString() + "B"
        } else {
            size = size / 1024
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return (size).toString() + "KB"
        } else {
            size = size / 1024
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100
            return ((size / 100)).toString() + "." + ((size % 100)).toString() + "MB"
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024
            return ((size / 100)).toString() + "." + ((size % 100)).toString() + "GB"
        }
    }
}
