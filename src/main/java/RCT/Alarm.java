package RCT;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alarm")
public class Alarm {
    private String tstamp;

    private String gas_level;

    private String type;

    public Alarm() {
    }

    public Alarm(String gas_level, String type) {
        this.gas_level = gas_level;
        this.type = type;
    }

    public Alarm(String tstamp, String gas_level, String type) {
        this.tstamp = tstamp;
        this.gas_level = gas_level;
        this.type = type;
    }

    public String getTstamp() {
        return tstamp;
    }

    @XmlAttribute(name = "tstamp")
    public void setTstamp(String tstamp) {
        this.tstamp = tstamp;
    }

    public String getGas_level() {
        return gas_level;
    }

    @XmlElement(name = "gas_level")
    public void setGas_level(String gas_level) {
        this.gas_level = gas_level;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "type")
    public void setType(String type) {
        this.type = type;
    }
}
