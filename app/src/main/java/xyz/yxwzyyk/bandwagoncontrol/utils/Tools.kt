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
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节)
     * @param bytes
     * *
     * @return
     */
    fun bytesTokb(bytes: Long): String {
        val filesize = BigDecimal(bytes)
        val megabyte = BigDecimal(1024 * 1024)
        var returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).toFloat()
        if (returnValue > 1)
            return "$returnValue MB"
        val kilobyte = BigDecimal(1024)
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).toFloat()
        return "$returnValue KB"
    }
}
