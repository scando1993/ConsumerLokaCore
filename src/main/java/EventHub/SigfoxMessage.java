package EventHub;

public class SigfoxMessage {
    private static int id;
    private int battery;
    private String MAC1;
    private String MAC2;
    private String MAC3;
    private float temperature;
    private int rss1;
    private int rss2;
    private int rss3;

    public SigfoxMessage() {
        this.battery = 7;
    }


    public SigfoxMessage(int id, int battery, String MAC1, String MAC2, String MAC3, float temperature, int rss1, int rss2, int rss3) {
        id = id;
        this.battery = battery;
        this.MAC1 = MAC1;
        this.MAC2 = MAC2;
        this.MAC3 = MAC3;
        this.temperature = temperature;
        this.rss1 = rss1;
        this.rss2 = rss2;
        this.rss3 = rss3;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(float batt) {
        this.battery = Math.round((float)( (batt * 7.0) / 100.0));
        if (this.battery < 0)
            this.battery = 0;
    }

    public String getMAC1() {
        return MAC1;
    }

    public void setMAC1(String MAC1) {
        this.MAC1 = MAC1.replace(":","");
    }

    public String getMAC2() {
        return MAC2;
    }

    public void setMAC2(String MAC2) {
        this.MAC2 = MAC2.replace(":","");
    }

    public String getMAC3() {
        return MAC3;
    }

    public void setMAC3(String MAC3) {
        this.MAC3 = MAC3.replace(":","");
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getRss1() {
        return rss1;
    }

    public void setRss1(int rss1) {
        this.rss1 = rss1;
    }

    public int getRss2() {
        return rss2;
    }

    public void setRss2(int rss2) {
        this.rss2 = rss2;
    }

    public int getRss3() {
        return rss3;
    }

    public void setRss3(int rss3) {
        this.rss3 = rss3;
    }

    public String byte0(){
        if (id > 31)
            id = 0;

        int byte0 = (this.battery * 32 + id);
//        return Integer.toHexString(byte0);
        return String.format("%02X", byte0);
    }

    public String generateFrame0(){
        String payload;


        payload = byte0();
        payload += this.MAC1;
        payload += this.MAC2.substring(0,10);
        id++;
        return payload;
    }

    public String generateFrame1(){
        String payload;

        payload = byte0();
        payload += this.MAC2.substring(10, 12);
        payload += this.MAC3;
        payload += String.format("%02X", (byte) this.rss1);
        payload += String.format("%02X", (byte) this.rss2);
        payload += String.format("%02X", (byte) this.rss3);
        payload += String.format("%02X", (byte) Math.round(this.temperature));
//        payload += Integer.toHexString(Math.round(this.temperature));
        id++;
        return payload;
    }
}
