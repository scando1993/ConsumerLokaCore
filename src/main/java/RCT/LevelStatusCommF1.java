package RCT;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "level_status_comm_F1")
public class LevelStatusCommF1 {
    private String xmlns;
    private String xmlns_msdata;
    private String xmlns_xsi;
    private Units units;

    public LevelStatusCommF1() {
        this.xmlns = "http://www.R-C-T.biz";
        this.xmlns_msdata = "urn:schemas-microsoft-com:xml-msdata";
        this.xmlns_xsi = "http://www.w3.org/2001/XMLSchema-instance";
    }

    public LevelStatusCommF1(String xmlns, String xmlns_msdata, String xmlns_xsi) {
        this.xmlns = xmlns;
        this.xmlns_msdata = xmlns_msdata;
        this.xmlns_xsi = xmlns_xsi;
    }

    public String getXmlns() {
        return xmlns;
    }

    @XmlAttribute(name = "xmlns")
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getXmlns_msdata() {
        return xmlns_msdata;
    }

    @XmlAttribute(name = "xmlns:msdata")
    public void setXmlns_msdata(String xmlns_msdata) {
        this.xmlns_msdata = xmlns_msdata;
    }

    public String getXmlns_xsi() {
        return xmlns_xsi;
    }

    @XmlAttribute(name = "xmlns:xsi")
    public void setXmlns_xsi(String xmlns_xsi) {
        this.xmlns_xsi = xmlns_xsi;
    }

    public Units getUnits() {
        return units;
    }

    @XmlElement(name = "units")
    public void setUnits(Units units) {
        this.units = units;
    }
}
