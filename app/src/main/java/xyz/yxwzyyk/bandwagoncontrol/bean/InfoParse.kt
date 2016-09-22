package xyz.yxwzyyk.bandwagoncontrol.bean

import xyz.yxwzyyk.bandwagoncontrol.utils.Tools
import java.text.SimpleDateFormat

/**
 * Created by yyk on 4/10/16.
 */
class InfoParse(val mInfo: Info) {

    val plan: String
        get() = mInfo.plan

    val hostname: String
        get() = mInfo.hostname

    val location: String
        get() = mInfo.node_location

    val os: String
        get() = mInfo.os

    val sshPort: String
        get() = mInfo.ssh_port.toString()

    val ip: String
        get() {
            var ip = ""
            for (i in 0..mInfo.ip_addresses.size - 1) {
                ip += mInfo.ip_addresses[i]
                if (i + 1 != mInfo.ip_addresses.size) ip += "\n"
            }
            return ip
        }

    val status: String
        get() = mInfo.vz_status.status

    val cpu: String
        get() = mInfo.vz_status.nproc + " processes " + "LA:" + mInfo.vz_status.load_average

    val resets: String
        get() {
            var date = ""
            val dateFormat = SimpleDateFormat("yyyy/MM/dd")
            date += "Resets:" + dateFormat.format(mInfo.data_next_reset * 1000)
            return date
        }

    val ram: String
        get() = Tools.getPrintSize(mInfo.plan_ram.toLong())

    val ramUse: String
        get() {
            if (mInfo.vz_status.oomguarpages == "-")
                mInfo.vz_status.oomguarpages = "0"
            return Tools.getPrintSize(java.lang.Long.parseLong(mInfo.vz_status.oomguarpages) * 4 * 1024)
        }

    val ramPercentage: Int
        get() {
            val ram: Double
            val a = java.lang.Long.parseLong(mInfo.vz_status.oomguarpages) * 4 * 1024.toDouble()
            if (mInfo.vz_status.oomguarpages == "-")
                mInfo.vz_status.oomguarpages = "0"
            val b = mInfo.plan_ram.toDouble()
            ram = a / b * 100
            return ram.toInt()
        }

    val swap: String
        get() = Tools.getPrintSize(mInfo.plan_swap.toLong())

    val swapUse: String
        get() {
            if (mInfo.vz_status.swappages == "-") mInfo.vz_status.swappages = "0"
            return Tools.getPrintSize(java.lang.Long.parseLong(mInfo.vz_status.swappages) * 4 * 1024)
        }

    val swapPercentage: Int
        get() {
            val swap: Double
            val a = java.lang.Long.parseLong(mInfo.vz_status.swappages) * 4 * 1024.toDouble()
            if (mInfo.vz_status.swappages == "-") mInfo.vz_status.swappages = "0"
            val b = mInfo.plan_swap.toDouble()
            swap = a / b * 100
            return swap.toInt()
        }

    val disk: String
        get() = Tools.getPrintSize(mInfo.plan_disk)

    val diskUse: String
        get() {
            if (mInfo.vz_quota.occupied_kb == "-")
                mInfo.vz_quota.occupied_kb = "0"
            return Tools.getPrintSize(java.lang.Long.parseLong(mInfo.vz_quota.occupied_kb) * 1024)
        }

    val diskPercentage: Int
        get() {
            val disk: Double
            val a = (java.lang.Long.parseLong(mInfo.vz_quota.occupied_kb) * 1024).toDouble()
            if (mInfo.vz_quota.occupied_kb == "-")
                mInfo.vz_quota.occupied_kb = "0"
            val b = mInfo.plan_disk.toDouble()
            disk = a / b * 100
            return disk.toInt()
        }

    val bandwidth: String
        get() = Tools.getPrintSize(mInfo.plan_monthly_data)

    val bandwidthUse: String
        get() = Tools.getPrintSize(mInfo.data_counter)

    val bandwidthPercentage: Int
        get() {
            val bandwidth: Double
            val a = mInfo.data_counter.toDouble()
            val b = mInfo.plan_monthly_data.toDouble()
            bandwidth = a / b * 100
            return bandwidth.toInt()
        }
}
