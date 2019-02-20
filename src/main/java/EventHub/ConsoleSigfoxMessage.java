package EventHub;

import com.thoughtcreator.iot.api.ApiClient;
import com.thoughtcreator.iot.api.ApiManager;
import com.thoughtcreator.iot.api.exceptions.ConnectionFailedException;
import com.thoughtcreator.iot.api.exceptions.InvalidUsernameOrPasswordException;
import com.thoughtcreator.iot.api.exceptions.UnauthorizedAccessException;
import com.thoughtcreator.iot.api.messages.*;
import com.thoughtcreator.iot.api.terminal.Terminal;
import com.thoughtcreator.iot.api.terminal.TerminalEventHandler;


import java.io.InputStream;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class ConsoleSigfoxMessage implements TerminalEventHandler, ApiClient {
    public static Map<Long,Terminal> terminals = new HashMap<Long,Terminal>(1);
    public static ApiManager apiManager = ApiManager.instance;
    public final static ConsoleSigfoxMessage console = new ConsoleSigfoxMessage();
    private static Integer msgCounter = 0;


    private SigfoxMessage sigfoxMessage = null;
    public int received = 0;
    private String timestamp;
    private int sequenceNumber;
    private String device;
    private String family;

    static List<Long> terminalIds = new ArrayList<Long>(1);

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Usage: <family> <server> <token> <device_id>[,<device_id 2>[...,<device_id n>]]");
            System.out.println("Example: test1 core.loka.systems hsajk217809-asd109u 123456789,123456788");
            return;
        }


        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutting down Loka Demo.");
                try {
                    Iterator<Map.Entry<Long, Terminal>> it = terminals.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<Long,Terminal> e = it.next();
                        if (e.getValue() != null) {
                            apiManager.removeTerminal(e.getValue());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        String[] terminalSet = args[3].split(Pattern.quote(","));
        for (String terminal : terminalSet) {
            terminalIds.add(Long.parseLong(terminal));
        }

        try {
            apiManager.login("https://" + args[1], args[2]);
        } catch (URISyntaxException e1) {
            System.out.println("Error un URI!!!");
            e1.printStackTrace();
            return;
        } catch (UnauthorizedAccessException e1) {
            System.out.println("Unauthorized !!");
            e1.printStackTrace();
            return;
        } catch (ConnectionFailedException e1) {
            System.out.println("Connection failed !!");
            e1.printStackTrace();
            return;
        } catch (InvalidUsernameOrPasswordException e1) {
            System.out.println("Error user failed !!");
            e1.printStackTrace();
            return;
        }


        try {
            for (Long terminalId : terminalIds) {
                Terminal t = new Terminal(terminalId);
                console.family = args[0];
                t.setEventHandler(console);
                apiManager.addTerminal(t);
                terminals.put(terminalId, t);
            }
            apiManager.startReceivingEvents(console);

            while (true) {
                Thread.sleep(500);
            }

        } catch (Exception e) {
            System.out.println("Exception " + e.toString());
            return;
        }
    }

    public void onRegister(Terminal terminal, ControlMessage message) {
        this.sigfoxMessage = new SigfoxMessage();
        System.out.println("Received Register from " + terminal.getId());
    }

    public void onGpio(Terminal terminal, GpioMessage message) {
        System.out.println("Received GPIO value from " + terminal.getId() + " in port " + message.getGpio().getPort() + " with value " + message.getGpio().getValue());
    }

    public void onDigital(Terminal terminal, DigitalMessage message) {
        System.out.println("Received digital message from " + terminal.getId() + " with value " + message.getDigital().getValue());
    }

    public void onAnalog(Terminal terminal, AnalogMessage message) {
        switch (message.getAnalog().getPort())
        {
            case 101:{

            }break;
            case 102: {
                if (this.sigfoxMessage != null){
                    this.sigfoxMessage.setTemperature((float) message.getAnalog().getValue());
                }
            }break;
            case 103: {

            }break;
            case 200:{
                if (this.sigfoxMessage != null){
                    this.sigfoxMessage.setBattery((float) message.getAnalog().getValue());
                }
            }break;
            default:{
            }
        }
        System.out.println("Received analog value from " + terminal.getId() + " in port " + message.getAnalog().getPort() + " with value " + message.getAnalog().getValue());

    }

    public void onGps(Terminal terminal, GpsMessage message) {
        System.out.println("Received GPS value from " + terminal.getId() + " with latitude " + message.getGps().getLatitude() + " and longitude " + message.getGps().getLongitude());

    }

    public void onUnknownMessage(Terminal terminal, String message) {
        System.out.println("UNKNOWN MESSAGE ("+ message + ") from terminal " + terminal);
        System.out.println(message);

    }

    public void onError(Exception ex) {
        System.out.println("ERROR " + ex.getMessage());
        ex.printStackTrace();

    }

    public void onUnknownTerminal(long id) {
        System.out.println("UNKNOWN TERMINAL " + id);

    }

    public void onWifi(Terminal terminal, WifiMessage message) {
        System.out.println("Received Wifi message: " + message.toString());
        List<WifiMessage.Wifi> wifiList = message.getWifi();
        switch (wifiList.size()){
            case 1:{
                WifiMessage.Wifi wifiMessage = wifiList.get(0);
                this.sigfoxMessage.setMAC1(wifiMessage.getMac());
                this.sigfoxMessage.setRss1(wifiMessage.getRssi());
                this.sigfoxMessage.setMAC2(wifiMessage.getMac());
                this.sigfoxMessage.setRss2(wifiMessage.getRssi());
                this.sigfoxMessage.setMAC3(wifiMessage.getMac());
                this.sigfoxMessage.setRss3(wifiMessage.getRssi());
            }break;
            case 2:{
                WifiMessage.Wifi wifiMessage = wifiList.get(0);
                WifiMessage.Wifi wifiMessage1 = wifiList.get(1);
                this.sigfoxMessage.setMAC1(wifiMessage.getMac());
                this.sigfoxMessage.setRss1(wifiMessage.getRssi());
                this.sigfoxMessage.setMAC2(wifiMessage1.getMac());
                this.sigfoxMessage.setRss2(wifiMessage1.getRssi());
                this.sigfoxMessage.setMAC3(wifiMessage.getMac());
                this.sigfoxMessage.setRss3(wifiMessage.getRssi());
            }break;
            case 3:{
                WifiMessage.Wifi wifiMessage = wifiList.get(0);
                WifiMessage.Wifi wifiMessage1 = wifiList.get(1);
                WifiMessage.Wifi wifiMessage2 = wifiList.get(2);
                this.sigfoxMessage.setMAC1(wifiMessage.getMac());
                this.sigfoxMessage.setRss1(wifiMessage.getRssi());
                this.sigfoxMessage.setMAC2(wifiMessage1.getMac());
                this.sigfoxMessage.setRss2(wifiMessage1.getRssi());
                this.sigfoxMessage.setMAC3(wifiMessage2.getMac());
                this.sigfoxMessage.setRss3(wifiMessage2.getRssi());
            }break;
            default:{

            }
        }
    }

    public void onNetworkInformation(Terminal terminal,
                                     NetworkInformationMessage message) {
        if(message.getNetworkInformation().getMessage().length() >= 12) {
            this.sequenceNumber = (int) message.getNetworkInformation().getSequenceNumber();
            this.device = message.getSrc();
            this.sendDataToEventHub();
        }
        System.out.println("Received NetworkInformation message: " + message.toString());

    }

    public void onLocation(Terminal terminal, LocationMessage message) {

        System.out.println("Received Location value from " + terminal.getId() + " with latitude " + message.getLocation().getLatitude() + " and longitude " + message.getLocation().getLongitude());
    }

    public boolean sendDataToEventHub(){
        EventHub eventHub = new EventHub();
        System.out.println(Long.toString(System.currentTimeMillis()/1000));
        EventHubMessage message1 = new EventHubMessage(this.device,
                this.sequenceNumber,
                this.family,
                System.currentTimeMillis()/1000,
                Integer.parseInt(this.sigfoxMessage.byte0(), 16),
                this.sigfoxMessage.generateFrame0());
        System.out.println(message1.toString());
        boolean event1 = eventHub.sendMessage(message1);

        EventHubMessage message2 = new EventHubMessage(this.device,
                ++this.sequenceNumber,
                this.family,
                System.currentTimeMillis()/1000,
                Integer.parseInt(this.sigfoxMessage.byte0(), 16),
                this.sigfoxMessage.generateFrame1());
        System.out.println(message2.toString());
        boolean event2 = eventHub.sendMessage(message2);

        this.sigfoxMessage = null;
        if (event1 && event2) {
            System.out.println("Object message generated correctly");
            return true;
        }
        return false;
    }
}
