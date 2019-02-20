package RCT;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "level_status_comm_F1")

public class LokaTelemetry {
    private String src;
    private String dst;
    private String timestamp;
    private long temperature;
}
