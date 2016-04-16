package xyz.yxwzyyk.bandwagoncontrol.bean;

import java.util.List;

/**
 * Created by yyk on 12/13/15.
 */
public class Info {
    /**
     * status : running
     * hostname : default.hostname
     * load_average : 0.00/0.00/0.00
     * nproc : 9
     * nproc_b : 100
     * kmemsize : 1648513
     * kmemsize_b : 58720256
     * privvmpages : 1995
     * privvmpages_b : 114688
     * oomguarpages : 1681
     * oomguarpages_b : 28672
     * numtcpsock : 54
     * numtcpsock_b : 830
     * numfile : 186
     * numfile_b : 4480
     * swappages : 660
     * swappages_b : 6144
     * physpages : 2436
     * physpages_l : 28672
     */

    private VzStatusEntity vz_status;
    /**
     * occupied_kb : 957272
     * softlimit_kb : 3145728
     * hardlimit_kb : 3303424
     * occupied_inodes : 46645
     * softlimit_inodes : 3072000
     * hardlimit_inodes : 3225600
     */

    private VzQuotaEntity vz_quota;
    /**
     * vz_status : {"status":"running","hostname":"default.hostname","load_average":"0.00/0.00/0.00","nproc":"9","nproc_b":"100","kmemsize":"1648513","kmemsize_b":"58720256","privvmpages":"1995","privvmpages_b":"114688","oomguarpages":"1681","oomguarpages_b":"28672","numtcpsock":"54","numtcpsock_b":"830","numfile":"186","numfile_b":"4480","swappages":"660","swappages_b":"6144","physpages":"2436","physpages_l":"28672"}
     * vz_quota : {"occupied_kb":"957272","softlimit_kb":"3145728","hardlimit_kb":"3303424","occupied_inodes":"46645","softlimit_inodes":"3072000","hardlimit_inodes":"3225600"}
     * is_cpu_throttled :
     * ssh_port : 2100
     * hostname : default.hostname
     * node_ip : 45.78.0.20
     * node_alias : v410
     * node_location : US, California
     * location_ipv6_ready : true
     * plan : micro96
     * plan_monthly_data : 214748364800
     * plan_disk : 3221225472
     * plan_ram : 117440512
     * plan_swap : 25165824
     * plan_max_ipv6s : 3
     * os : debian-7.0-x86
     * email : yxwzyyk@gmail.com
     * data_counter : 29965207131
     * data_next_reset : 1450760400
     * ip_addresses : ["45.78.11.170","2607:8700:101:d54c::"]
     * rdns_api_available : true
     * ptr : {"45.78.11.170":null,"2607:8700:101:d54c::":""}
     * suspended : false
     * error : 0
     */

    private String is_cpu_throttled;
    private int ssh_port;
    private String hostname;
    private String node_ip;
    private String node_alias;
    private String node_location;
    private boolean location_ipv6_ready;
    private String plan;
    private long plan_monthly_data;
    private long plan_disk;
    private int plan_ram;
    private int plan_swap;
    private int plan_max_ipv6s;
    private String os;
    private String email;
    private long data_counter;
    private long data_next_reset;
    private boolean rdns_api_available;
    private boolean suspended;
    private int error;
    private List<String> ip_addresses;

    public void setVz_status(VzStatusEntity vz_status) {
        this.vz_status = vz_status;
    }

    public void setVz_quota(VzQuotaEntity vz_quota) {
        this.vz_quota = vz_quota;
    }

    public void setIs_cpu_throttled(String is_cpu_throttled) {
        this.is_cpu_throttled = is_cpu_throttled;
    }

    public void setSsh_port(int ssh_port) {
        this.ssh_port = ssh_port;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setNode_ip(String node_ip) {
        this.node_ip = node_ip;
    }

    public void setNode_alias(String node_alias) {
        this.node_alias = node_alias;
    }

    public void setNode_location(String node_location) {
        this.node_location = node_location;
    }

    public void setLocation_ipv6_ready(boolean location_ipv6_ready) {
        this.location_ipv6_ready = location_ipv6_ready;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setPlan_monthly_data(long plan_monthly_data) {
        this.plan_monthly_data = plan_monthly_data;
    }

    public void setPlan_disk(long plan_disk) {
        this.plan_disk = plan_disk;
    }

    public void setPlan_ram(int plan_ram) {
        this.plan_ram = plan_ram;
    }

    public void setPlan_swap(int plan_swap) {
        this.plan_swap = plan_swap;
    }

    public void setPlan_max_ipv6s(int plan_max_ipv6s) {
        this.plan_max_ipv6s = plan_max_ipv6s;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setData_counter(long data_counter) {
        this.data_counter = data_counter;
    }

    public void setData_next_reset(int data_next_reset) {
        this.data_next_reset = data_next_reset;
    }

    public void setRdns_api_available(boolean rdns_api_available) {
        this.rdns_api_available = rdns_api_available;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setIp_addresses(List<String> ip_addresses) {
        this.ip_addresses = ip_addresses;
    }

    public VzStatusEntity getVz_status() {
        return vz_status;
    }

    public VzQuotaEntity getVz_quota() {
        return vz_quota;
    }

    public String getIs_cpu_throttled() {
        return is_cpu_throttled;
    }

    public int getSsh_port() {
        return ssh_port;
    }

    public String getHostname() {
        return hostname;
    }

    public String getNode_ip() {
        return node_ip;
    }

    public String getNode_alias() {
        return node_alias;
    }

    public String getNode_location() {
        return node_location;
    }

    public boolean isLocation_ipv6_ready() {
        return location_ipv6_ready;
    }

    public String getPlan() {
        return plan;
    }

    public long getPlan_monthly_data() {
        return plan_monthly_data;
    }

    public long getPlan_disk() {
        return plan_disk;
    }

    public int getPlan_ram() {
        return plan_ram;
    }

    public int getPlan_swap() {
        return plan_swap;
    }

    public int getPlan_max_ipv6s() {
        return plan_max_ipv6s;
    }

    public String getOs() {
        return os;
    }

    public String getEmail() {
        return email;
    }

    public long getData_counter() {
        return data_counter;
    }

    public long getData_next_reset() {
        return data_next_reset;
    }

    public boolean isRdns_api_available() {
        return rdns_api_available;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public int getError() {
        return error;
    }

    public List<String> getIp_addresses() {
        return ip_addresses;
    }

    public static class VzStatusEntity {
        private String status;
        private String hostname;
        private String load_average;
        private String nproc;
        private String nproc_b;
        private String kmemsize;
        private String kmemsize_b;
        private String privvmpages;
        private String privvmpages_b;
        private String oomguarpages;
        private String oomguarpages_b;
        private String numtcpsock;
        private String numtcpsock_b;
        private String numfile;
        private String numfile_b;
        private String swappages;
        private String swappages_b;
        private String physpages;
        private String physpages_l;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public void setLoad_average(String load_average) {
            this.load_average = load_average;
        }

        public void setNproc(String nproc) {
            this.nproc = nproc;
        }

        public void setNproc_b(String nproc_b) {
            this.nproc_b = nproc_b;
        }

        public void setKmemsize(String kmemsize) {
            this.kmemsize = kmemsize;
        }

        public void setKmemsize_b(String kmemsize_b) {
            this.kmemsize_b = kmemsize_b;
        }

        public void setPrivvmpages(String privvmpages) {
            this.privvmpages = privvmpages;
        }

        public void setPrivvmpages_b(String privvmpages_b) {
            this.privvmpages_b = privvmpages_b;
        }

        public void setOomguarpages(String oomguarpages) {
            this.oomguarpages = oomguarpages;
        }

        public void setOomguarpages_b(String oomguarpages_b) {
            this.oomguarpages_b = oomguarpages_b;
        }

        public void setNumtcpsock(String numtcpsock) {
            this.numtcpsock = numtcpsock;
        }

        public void setNumtcpsock_b(String numtcpsock_b) {
            this.numtcpsock_b = numtcpsock_b;
        }

        public void setNumfile(String numfile) {
            this.numfile = numfile;
        }

        public void setNumfile_b(String numfile_b) {
            this.numfile_b = numfile_b;
        }

        public void setSwappages(String swappages) {
            this.swappages = swappages;
        }

        public void setSwappages_b(String swappages_b) {
            this.swappages_b = swappages_b;
        }

        public void setPhyspages(String physpages) {
            this.physpages = physpages;
        }

        public void setPhyspages_l(String physpages_l) {
            this.physpages_l = physpages_l;
        }

        public String getStatus() {
            return status;
        }

        public String getHostname() {
            return hostname;
        }

        public String getLoad_average() {
            return load_average;
        }

        public String getNproc() {
            return nproc;
        }

        public String getNproc_b() {
            return nproc_b;
        }

        public String getKmemsize() {
            return kmemsize;
        }

        public String getKmemsize_b() {
            return kmemsize_b;
        }

        public String getPrivvmpages() {
            return privvmpages;
        }

        public String getPrivvmpages_b() {
            return privvmpages_b;
        }

        public String getOomguarpages() {
            return oomguarpages;
        }

        public String getOomguarpages_b() {
            return oomguarpages_b;
        }

        public String getNumtcpsock() {
            return numtcpsock;
        }

        public String getNumtcpsock_b() {
            return numtcpsock_b;
        }

        public String getNumfile() {
            return numfile;
        }

        public String getNumfile_b() {
            return numfile_b;
        }

        public String getSwappages() {
            return swappages;
        }

        public String getSwappages_b() {
            return swappages_b;
        }

        public String getPhyspages() {
            return physpages;
        }

        public String getPhyspages_l() {
            return physpages_l;
        }
    }

    public static class VzQuotaEntity {
        private String occupied_kb;
        private String softlimit_kb;
        private String hardlimit_kb;
        private String occupied_inodes;
        private String softlimit_inodes;
        private String hardlimit_inodes;

        public void setOccupied_kb(String occupied_kb) {
            this.occupied_kb = occupied_kb;
        }

        public void setSoftlimit_kb(String softlimit_kb) {
            this.softlimit_kb = softlimit_kb;
        }

        public void setHardlimit_kb(String hardlimit_kb) {
            this.hardlimit_kb = hardlimit_kb;
        }

        public void setOccupied_inodes(String occupied_inodes) {
            this.occupied_inodes = occupied_inodes;
        }

        public void setSoftlimit_inodes(String softlimit_inodes) {
            this.softlimit_inodes = softlimit_inodes;
        }

        public void setHardlimit_inodes(String hardlimit_inodes) {
            this.hardlimit_inodes = hardlimit_inodes;
        }

        public String getOccupied_kb() {
            return occupied_kb;
        }

        public String getSoftlimit_kb() {
            return softlimit_kb;
        }

        public String getHardlimit_kb() {
            return hardlimit_kb;
        }

        public String getOccupied_inodes() {
            return occupied_inodes;
        }

        public String getSoftlimit_inodes() {
            return softlimit_inodes;
        }

        public String getHardlimit_inodes() {
            return hardlimit_inodes;
        }
    }
}
