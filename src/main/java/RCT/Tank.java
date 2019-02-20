package RCT;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tank")
public class Tank {
    private String id;

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    private Data data;

    private Param_def param_def;

    public Data getData() {
        return data;
    }

    @XmlElement(name = "data")
    public void setData(Data data) {
        this.data = data;
    }

    public Param_def getParam_def() {
        return param_def;
    }

    @XmlElement(name = "param_def")
    public void setParam_def(Param_def param_def) {
        this.param_def = param_def;
    }
}
