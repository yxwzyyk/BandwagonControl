package xyz.yxwzyyk.bandwagoncontrol.db;

/**
 * Created by yyk on 12/2/15.
 */
public class Host {
    public int _id;
    public String title;
    public String veid;
    public String key;

    public Host() {

    }

    public Host(String title, String veid, String key) {
        this.title = title;
        this.veid = veid;
        this.key = key;
    }
}
