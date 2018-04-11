package com.thinkbig.veon.dao;

public class L2_DPI {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getHasParsingErrors() {
        return hasParsingErrors;
    }

    public void setHasParsingErrors(String hasParsingErrors) {
        this.hasParsingErrors = hasParsingErrors;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getUplink_traffic() {
        return uplink_traffic;
    }

    public void setUplink_traffic(String uplink_traffic) {
        this.uplink_traffic = uplink_traffic;
    }

    public String getDownlink_traffic() {
        return downlink_traffic;
    }

    public void setDownlink_traffic(String downlink_traffic) {
        this.downlink_traffic = downlink_traffic;
    }

    public String getStartTimeMilSec() {
        return startTimeMilSec;
    }

    public void setStartTimeMilSec(String startTimeMilSec) {
        this.startTimeMilSec = startTimeMilSec;
    }

    public String getProtocol_category() {
        return protocol_category;
    }

    public void setProtocol_category(String protocol_category) {
        this.protocol_category = protocol_category;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getImeisv() {
        return imeisv;
    }

    public void setImeisv(String imeisv) {
        this.imeisv = imeisv;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getRat_type() {
        return rat_type;
    }

    public void setRat_type(String rat_type) {
        this.rat_type = rat_type;
    }

    public String id;
    public String srcId;
    public String hasParsingErrors;
    public String msisdn;
    public String uplink_traffic;
    public String downlink_traffic;
    public String startTimeMilSec;
    public String protocol_category;
    public String application;
    public String imeisv;
    public String imsi;
    public String rat_type;

    public L2_DPI(String id, String srcId, String hasParsingErrors, String msisdn, String uplink_traffic, String downlink_traffic, String startTimeMilSec, String protocol_category, String application, String imeisv, String imsi, String rat_type) {
        this.id = id;
        this.srcId = srcId;
        this.hasParsingErrors = hasParsingErrors;
        this.msisdn = msisdn;
        this.uplink_traffic = uplink_traffic;
        this.downlink_traffic = downlink_traffic;
        this.startTimeMilSec = startTimeMilSec;
        this.protocol_category = protocol_category;
        this.application = application;
        this.imeisv = imeisv;
        this.imsi = imsi;
        this.rat_type = rat_type;
    }

    public L2_DPI() {
    }
}
