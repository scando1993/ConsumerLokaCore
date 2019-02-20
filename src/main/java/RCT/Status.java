package RCT;

import javax.print.DocFlavor;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "status")
public class Status {
    private String msg_number;

    private String bat_level;

    private String gsm_signal;

    private String system_bat;

    private String modem_bat;

    private String modem_time;

    private String sensor_time;

    private String tstamp;

    public String getTstamp() {
        return tstamp;
    }

    public Status() {
    }

    public Status(String msg_number, String bat_level, String system_bat, String modem_bat, String modem_time, String sensor_time, String tstamp) {
        this.msg_number = msg_number;
        this.bat_level = bat_level;
        this.system_bat = system_bat;
        this.modem_bat = modem_bat;
        this.modem_time = modem_time;
        this.sensor_time = sensor_time;
        this.tstamp = tstamp;
    }

    public String getGsm_signal() {
        return gsm_signal;
    }

    @XmlElement(name = "gsm_signal")
    public void setGsm_signal(String gsm_signal) {
        this.gsm_signal = gsm_signal;
    }

    @XmlAttribute(name = "tstamp")
    public void setTstamp(String tstamp) {
        this.tstamp = tstamp;
    }

    public String getMsg_number() {
        return msg_number;
    }

    @XmlElement(name = "msg_number")
    public void setMsg_number(String msg_number) {
        this.msg_number = msg_number;
    }

    public String getBat_level() {
        return bat_level;
    }

    @XmlElement(name = "bat_level")
    public void setBat_level(String bat_level) {
        this.bat_level = bat_level;
    }

    public String getSystem_bat() {
        return system_bat;
    }

    @XmlElement(name = "system_bat")
    public void setSystem_bat(String system_bat) {
        this.system_bat = system_bat;
    }

    public String getModem_bat() {
        return modem_bat;
    }

    @XmlElement(name = "modem_bat")
    public void setModem_bat(String modem_bat) {
        this.modem_bat = modem_bat;
    }

    public String getModem_time() {
        return modem_time;
    }

    @XmlElement(name = "modem_time")
    public void setModem_time(String modem_time) {
        this.modem_time = modem_time;
    }

    public String getSensor_time() {
        return sensor_time;
    }

    @XmlElement(name = "sensor_time")
    public void setSensor_time(String sensor_time) {
        this.sensor_time = sensor_time;
    }
}
