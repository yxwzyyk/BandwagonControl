package xyz.yxwzyyk.bandwagoncontrol.host;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;

import xyz.yxwzyyk.bandwagoncontrol.utils.Tools;

/**
 * Created by yyk on 12/4/15.
 */
public class HostInfo {

    private Context mContext;
    private Info mInfo;

    public HostInfo(Context context, Info info) {
        mContext = context;
        mInfo = info;
    }

    public String getPlan() {
        return mInfo.getPlan();
    }

    public String getHostname() {
        return mInfo.getHostname();
    }

    public String getLocation() {
        return mInfo.getNode_location();
    }

    public String getOs() {
        return mInfo.getOs();
    }

    public String getSSHPort() {
        return String.valueOf(mInfo.getSsh_port());
    }

    public String getIP() {
        String ip = "";
        for (int i = 0; i < mInfo.getIp_addresses().size(); i++) {
            ip += mInfo.getIp_addresses().get(i);
            if ((i + 1) != mInfo.getIp_addresses().size()) ip += "\n";
        }
        return ip;
    }

    public String getStatus() {
        return mInfo.getVz_status().getStatus();
    }

    public String getCpu() {
        return mInfo.getVz_status().getNproc() + " processes " + "LA:" + mInfo.getVz_status().getLoad_average();
    }

    public String getResets() {
        String date = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date += "Resets:" + dateFormat.format(mInfo.getData_next_reset() * 1000);
        return date;
    }

    public String getRam() {
        return Tools.bytesTokb(mInfo.getPlan_ram());
    }

    public String getRamUse() {
        if (mInfo.getVz_status().getOomguarpages().equals("-"))
            mInfo.getVz_status().setOomguarpages("0");
        return Tools.bytesTokb(Long.parseLong(mInfo.getVz_status().getOomguarpages()) * 4 * 1024);
    }

    public int getRamPercentage() {
        double ram;
        double a = Long.parseLong(mInfo.getVz_status().getOomguarpages()) * 4 * 1024;
        if (mInfo.getVz_status().getOomguarpages().equals("-"))
            mInfo.getVz_status().setOomguarpages("0");
        double b = mInfo.getPlan_ram();
        ram = (a / b) * 100;
        return (int) ram;
    }

    public String getSwap() {
        return Tools.bytesTokb(mInfo.getPlan_swap());
    }

    public String getSwapUse() {
        if (mInfo.getVz_status().getSwappages().equals("-")) mInfo.getVz_status().setSwappages("0");
        return Tools.bytesTokb(Long.parseLong(mInfo.getVz_status().getSwappages()) * 4 * 1024);
    }

    public int getSwapPercentage() {
        double swap;
        double a = Long.parseLong(mInfo.getVz_status().getSwappages()) * 4 * 1024;
        if (mInfo.getVz_status().getSwappages().equals("-")) mInfo.getVz_status().setSwappages("0");
        double b = mInfo.getPlan_swap();
        swap = (a / b) * 100;
        return (int) swap;
    }

    public String getDisk() {
        return Tools.bytesTokb(mInfo.getPlan_disk());
    }

    public String getDiskUse() {
        if (mInfo.getVz_quota().getOccupied_kb().equals("-"))
            mInfo.getVz_quota().setOccupied_kb("0");
        return Tools.bytesTokb(Long.parseLong(mInfo.getVz_quota().getOccupied_kb()) * 1024);
    }

    public int getDiskPercentage() {
        double disk;
        double a = Long.parseLong(mInfo.getVz_quota().getOccupied_kb()) * 1024;
        if (mInfo.getVz_quota().getOccupied_kb().equals("-"))
            mInfo.getVz_quota().setOccupied_kb("0");
        double b = mInfo.getPlan_disk();
        disk = (a / b) * 100;
        return (int) disk;
    }

    public String getBandwidth() {
        return Tools.bytesTokb(mInfo.getPlan_monthly_data());
    }

    public String getBandwidthUse() {
        return Tools.bytesTokb(mInfo.getData_counter());
    }

    public int getBandwidthPercentage() {
        double bandwidth;
        double a = mInfo.getData_counter();
        double b = mInfo.getPlan_monthly_data();
        bandwidth = (a / b) * 100;
        return (int) bandwidth;
    }


}
