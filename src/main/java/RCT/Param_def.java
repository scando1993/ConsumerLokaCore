package RCT;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "param_def")
public class Param_def {
    private String tstamp;
    private String fulfill_alarm;
    private String leak_alarm;
    private String overfill_level;
    private String alarm_level;
    private String alarm_level2;
    private String cyclic_message;
    private String mobile_request;
    private String bat_alarm;
    private String error_alarm;

    public String getTstamp() {
        return tstamp;
    }

    @XmlAttribute(name = "tstamp")
    public void setTstamp(String tstamp) {
        this.tstamp = tstamp;
    }

    public String getFulfill_alarm() {
        return fulfill_alarm;
    }

    @XmlElement(name = "fulfill_alarm")
    public void setFulfill_alarm(String fulfill_alarm) {
        this.fulfill_alarm = fulfill_alarm;
    }

    public String getLeak_alarm() {
        return leak_alarm;
    }

    @XmlElement(name = "leak_alarm")
    public void setLeak_alarm(String leak_alarm) {
        this.leak_alarm = leak_alarm;
    }

    public String getOverfill_level() {
        return overfill_level;
    }

    @XmlElement(name = "overfill_level")
    public void setOverfill_level(String overfill_level) {
        this.overfill_level = overfill_level;
    }

    public String getAlarm_level() {
        return alarm_level;
    }

    @XmlElement(name = "alarm_level")
    public void setAlarm_level(String alarm_level) {
        this.alarm_level = alarm_level;
    }

    public String getAlarm_level2() {
        return alarm_level2;
    }

    @XmlElement(name = "alarm_level")
    public void setAlarm_level2(String alarm_level2) {
        this.alarm_level2 = alarm_level2;
    }

    public String getCyclic_message() {
        return cyclic_message;
    }

    @XmlElement(name = "cyclic_message")
    public void setCyclic_message(String cyclic_message) {
        this.cyclic_message = cyclic_message;
    }

    public String getMobile_request() {
        return mobile_request;
    }

    @XmlElement(name = "mobile_request")
    public void setMobile_request(String mobile_request) {
        this.mobile_request = mobile_request;
    }

    public String getBat_alarm() {
        return bat_alarm;
    }

    @XmlElement(name = "bat_alarm")
    public void setBat_alarm(String bat_alarm) {
        this.bat_alarm = bat_alarm;
    }

    public String getError_alarm() {
        return error_alarm;
    }

    @XmlElement(name = "error_alarm")
    public void setError_alarm(String error_alarm) {
        this.error_alarm = error_alarm;
    }
}
