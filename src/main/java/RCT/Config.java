package RCT;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config")

public class Config {

    private String sid;

    private String sw_version;

    private String comm_mode;

    private String acquisition_frequency;

    private String send_frequency;

    private String wakeup_frequency;

    private String send_time;

    private String online_time;

    private String max_sms;

    private String gsm_server;

    private String gsm_unit_number;

    private String gsm_smscenter_number;

    private String gsm_maintenance_number;

    public Config() {
    }

    public Config(String sid, String sw_version, String comm_mode, String acquisition_frequency, String send_frequency, String wakeup_frequency, String send_time, String online_time, String max_sms, String gsm_server, String gsm_unit_number, String gsm_smscenter_number, String gsm_maintenance_number) {
        this.sid = sid;
        this.sw_version = sw_version;
        this.comm_mode = comm_mode;
        this.acquisition_frequency = acquisition_frequency;
        this.send_frequency = send_frequency;
        this.wakeup_frequency = wakeup_frequency;
        this.send_time = send_time;
        this.online_time = online_time;
        this.max_sms = max_sms;
        this.gsm_server = gsm_server;
        this.gsm_unit_number = gsm_unit_number;
        this.gsm_smscenter_number = gsm_smscenter_number;
        this.gsm_maintenance_number = gsm_maintenance_number;
    }

    public String getSid() {
        return sid;
    }

    @XmlElement(name = "sid")
    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSw_version() {
        return sw_version;
    }

    @XmlElement(name = "sw_version")
    public void setSw_version(String sw_version) {
        this.sw_version = sw_version;
    }

    public String getComm_mode() {
        return comm_mode;
    }

    @XmlElement(name = "comm_mode")
    public void setComm_mode(String comm_mode) {
        this.comm_mode = comm_mode;
    }

    public String getAcquisition_frequency() {
        return acquisition_frequency;
    }

    @XmlElement(name = "acquisition_frequency")
    public void setAcquisition_frequency(String acquisition_frequency) {
        this.acquisition_frequency = acquisition_frequency;
    }

    public String getSend_frequency() {
        return send_frequency;
    }

    @XmlElement(name = "send_frequency")
    public void setSend_frequency(String send_frequency) {
        this.send_frequency = send_frequency;
    }

    public String getWakeup_frequency() {
        return wakeup_frequency;
    }

    @XmlElement(name = "wakeup_frequency")
    public void setWakeup_frequency(String wakeup_frequency) {
        this.wakeup_frequency = wakeup_frequency;
    }

    public String getSend_time() {
        return send_time;
    }

    @XmlElement(name = "send_time")
    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getOnline_time() {
        return online_time;
    }

    @XmlElement(name = "online_time")
    public void setOnline_time(String online_time) {
        this.online_time = online_time;
    }

    public String getMax_sms() {
        return max_sms;
    }

    @XmlElement(name = "max_sms")
    public void setMax_sms(String max_sms) {
        this.max_sms = max_sms;
    }

    public String getGsm_server() {
        return gsm_server;
    }

    @XmlElement(name = "gsm_server")
    public void setGsm_server(String gsm_server) {
        this.gsm_server = gsm_server;
    }

    public String getGsm_unit_number() {
        return gsm_unit_number;
    }

    @XmlElement(name = "gsm_unit_number")
    public void setGsm_unit_number(String gsm_unit_number) {
        this.gsm_unit_number = gsm_unit_number;
    }

    public String getGsm_smscenter_number() {
        return gsm_smscenter_number;
    }

    @XmlElement(name = "gsm_smscenter_number")
    public void setGsm_smscenter_number(String gsm_smscenter_number) {
        this.gsm_smscenter_number = gsm_smscenter_number;
    }

    public String getGsm_maintenance_number() {
        return gsm_maintenance_number;
    }

    @XmlElement(name = "gsm_maintenance_number")
    public void setGsm_maintenance_number(String gsm_maintenance_number) {
        this.gsm_maintenance_number = gsm_maintenance_number;
    }
}