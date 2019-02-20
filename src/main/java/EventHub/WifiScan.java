package EventHub;

public class WifiScan {
    private String MAC;
    private int rssi;

    public WifiScan(String hex, int _rssi)
    {
        this.MAC = TransformMACNumbertoMACAdress(TransformHexStringToLong(hex)).toLowerCase();
        this.rssi = _rssi;
    }

    private long TransformHexStringToLong(String hexStr)
    {
        long hex = 0;
        hex = Integer.parseInt(hexStr, 16);
        return hex;
    }

    private String TransformMACNumbertoMACAdress(long macNumber)
    {
//        byte[] macBytes = BitConverter.GetBytes(macNumber);
//        macBytes = macBytes.Reverse().Skip(2).ToArray();
//        return BitConverter.ToString(macBytes).Replace("-", ":");
        return null;
    }

    public String ByteArrayToString(byte[] bytes)
    {
//        return BitConverter.ToString(bytes.Reverse().ToArray()).Replace("-","");
        return null;
    }
    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
