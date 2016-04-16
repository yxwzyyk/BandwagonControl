package xyz.yxwzyyk.bandwagoncontrol.bean;

import java.util.List;

/**
 * Created by yyk on 4/16/16.
 */
public class Locations {
    public static final String USCA_2 = "USCA_2";
    public static final String USCA_FMT = "USCA_FMT";
    public static final String USAZ_2 = "USAZ_2";
    public static final String USFL_2 = "USFL_2";
    public static final String EUNL_3 = "EUNL_3";


    /**
     * error : 0
     * currentLocation : USCA_2
     * locations : ["USCA_2","USCA_FMT","USAZ_2","USFL_2","EUNL_3"]
     * descriptions : {"USCA_2":"US: Los Angeles, California (IPv4+IPv6)","USCA_FMT":"US: Fremont, California (IPv4+IPv6)","USAZ_2":"US: Phoenix, Arizona (IPv4+IPv6)","USFL_2":"US: Jacksonville, Florida (IPv4+IPv6)","EUNL_3":"EU: Amsterdam, Netherlands (IPv4+IPv6)"}
     */

    private int error;
    private String currentLocation;
    /**
     * USCA_2 : US: Los Angeles, California (IPv4+IPv6)
     * USCA_FMT : US: Fremont, California (IPv4+IPv6)
     * USAZ_2 : US: Phoenix, Arizona (IPv4+IPv6)
     * USFL_2 : US: Jacksonville, Florida (IPv4+IPv6)
     * EUNL_3 : EU: Amsterdam, Netherlands (IPv4+IPv6)
     */

    private DescriptionsBean descriptions;
    private List<String> locations;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public DescriptionsBean getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(DescriptionsBean descriptions) {
        this.descriptions = descriptions;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public static class DescriptionsBean {
        private String USCA_2;
        private String USCA_FMT;
        private String USAZ_2;
        private String USFL_2;
        private String EUNL_3;

        public String getUSCA_2() {
            return USCA_2;
        }

        public void setUSCA_2(String USCA_2) {
            this.USCA_2 = USCA_2;
        }

        public String getUSCA_FMT() {
            return USCA_FMT;
        }

        public void setUSCA_FMT(String USCA_FMT) {
            this.USCA_FMT = USCA_FMT;
        }

        public String getUSAZ_2() {
            return USAZ_2;
        }

        public void setUSAZ_2(String USAZ_2) {
            this.USAZ_2 = USAZ_2;
        }

        public String getUSFL_2() {
            return USFL_2;
        }

        public void setUSFL_2(String USFL_2) {
            this.USFL_2 = USFL_2;
        }

        public String getEUNL_3() {
            return EUNL_3;
        }

        public void setEUNL_3(String EUNL_3) {
            this.EUNL_3 = EUNL_3;
        }
    }
}
