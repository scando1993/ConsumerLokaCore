package RCT;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class Data {
    private Alarm alarm;

    public Data() {
    }

    public Data(Alarm alarm) {
        this.alarm = alarm;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    @XmlElement(name = "alarm")
    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }
}
