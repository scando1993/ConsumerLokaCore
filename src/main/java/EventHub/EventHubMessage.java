package EventHub;

public class EventHubMessage {
    private String device;
    private int seqNumber;
    private String family;
    private long time;
    private int messageId;
    private String rawData;

    public EventHubMessage(String device, int seqNumber, String family, long time, int messageId, String rawData) {
        this.device = device;
        this.seqNumber = seqNumber;
        this.family = family;
        this.time = time;
        this.messageId = messageId;
        this.rawData = rawData;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    @Override
    public String toString() {
        return "EventHubMessage{" +
                "device='" + device + '\'' +
                ", seqNumber=" + seqNumber +
                ", family='" + family + '\'' +
                ", time=" + time +
                ", messageId=" + messageId +
                ", rawData='" + rawData + '\'' +
                '}';
    }
}
