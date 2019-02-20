package RCT;

import RCT.Config;
import RCT.Status;
import RCT.Tank;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "unit")
public class Unit {
    private String gsm_number;
    private Tank tank;
    private Status status;
    private Config config;

    public Unit() {
    }

    public Unit(String gsm_number, Tank tank, Status status, Config config) {
        this.gsm_number = gsm_number;
        this.tank = tank;
        this.status = status;
        this.config = config;
    }

    public String getGsm_number() {
        return gsm_number;
    }

    @XmlAttribute(name = "gsm_number")
    public void setGsm_number(String gsm_number) {
        this.gsm_number = gsm_number;
    }

    public Tank getTank() {
        return tank;
    }

    @XmlElement(name = "tank")
    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Status getStatus() {
        return status;
    }

    @XmlElement(name = "status")
    public void setStatus(Status status) {
        this.status = status;
    }

    public Config getConfig() {
        return config;
    }

    @XmlElement(name = "config")
    public void setConfig(Config config) {
        this.config = config;
    }
}
