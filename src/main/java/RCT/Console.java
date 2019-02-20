package RCT;

import com.thoughtcreator.iot.api.ApiClient;
import com.thoughtcreator.iot.api.ApiManager;
import com.thoughtcreator.iot.api.exceptions.ConnectionFailedException;
import com.thoughtcreator.iot.api.exceptions.InvalidUsernameOrPasswordException;
import com.thoughtcreator.iot.api.exceptions.UnauthorizedAccessException;
import com.thoughtcreator.iot.api.messages.*;
import com.thoughtcreator.iot.api.terminal.Terminal;
import com.thoughtcreator.iot.api.terminal.TerminalEventHandler;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class Console implements TerminalEventHandler, ApiClient {
    public static Map<Long,Terminal> terminals = new HashMap<Long,Terminal>(1);
    public static ApiManager apiManager = ApiManager.instance;
    public final static Console console = new Console();
    private static Integer msgCounter = 0;

    private LevelStatusCommF1 levelStatusCommF1 = new LevelStatusCommF1();
    private Tank tank = new Tank();
    private Unit unit = new Unit();
    private Param_def param_def = new Param_def();
    private Status status = new Status();
    private Config config = new Config();
    private Data data = new Data();

    public int received = 0;

    static List<Long> terminalIds = new ArrayList<Long>(1);
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Usage: <server> <token> <device_id>[,<device_id 2>[...,<device_id n>]]");
            System.out.println("Example: core.loka.systems hsajk217809-asd109u 123456789,123456788");
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
                } catch (Exception e) { }
            }
        });

//        console.setParamDefs();
//        console.setStatus();
//        console.setConfig();
//
//        console.unit.setStatus(console.status);
//        console.unit.setConfig(console.config);
//        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
//        console.param_def.setTstamp(LocalDateTime.now().format(dtf));
//        console.status.setTstamp(LocalDateTime.now().format(dtf));
//        console.tank.setId("Loka");
//        console.tank.setParam_def(console.param_def);
//        console.tank.setData(console.data);
//        console.unit.setTank(console.tank);
//        console.unit.setGsm_number("+593991255666");
//        ArrayList<RCT.Unit> units = new ArrayList<>();
//        units.add(console.unit);
//        RCT.Units units1 = new RCT.Units(units);
//        console.levelStatusCommF1.setUnits(units1);

//        RCT.Alarm alarm = new RCT.Alarm();
//        alarm.setTstamp(LocalDateTime.now().toString());
//        alarm.setGas_level(Double.toString(102));
//        alarm.setType("1");
//        console.data.setAlarm(alarm);
//
//        try{
//            JAXBContext jaxbContext = JAXBContext.newInstance( RCT.LevelStatusCommF1.class );
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//            jaxbMarshaller.marshal( console.levelStatusCommF1, new File( "simple.xml" ) );
//            jaxbMarshaller.marshal( console.levelStatusCommF1, System.out );
//        }catch (JAXBException e){
//
//        }


        String[] terminalSet = args[2].split(Pattern.quote(","));
        for (String terminal : terminalSet) {
            terminalIds.add(Long.parseLong(terminal));
        }

        try {
            apiManager.login("https://" + args[0], args[1]);
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

    private void setParamDefs(){
        this.param_def.setFulfill_alarm("1");
        this.param_def.setLeak_alarm("1");
        this.param_def.setOverfill_level("88");
        this.param_def.setAlarm_level("20");
        this.param_def.setAlarm_level2("30");
        this.param_def.setCyclic_message("0");
        this.param_def.setMobile_request("1");
        this.param_def.setBat_alarm("1");
        this.param_def.setError_alarm("0");
    }

    private void setStatus(){
        this.status.setMsg_number(msgCounter.toString());
        this.status.setGsm_signal("");
        this.status.setSystem_bat("");
        this.status.setModem_bat("");
        this.status.setSensor_time("");
        this.status.setBat_level("");
    }

    private void setConfig(){
        this.config.setSid("100000");
        this.config.setSw_version("3.6");
        this.config.setComm_mode("0");
        this.config.setAcquisition_frequency("1");
        this.config.setSend_frequency("24");
        this.config.setWakeup_frequency("24");
        this.config.setSend_time("07:00");
        this.config.setOnline_time("2");
        this.config.setMax_sms("5");
        this.config.setGsm_server("+593985045918");
    }

    public void onRegister(Terminal terminal, ControlMessage message) {

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
                msgCounter++;
                Alarm alarm = new Alarm();
                alarm.setTstamp(LocalDateTime.now().toString());
                alarm.setGas_level(Double.toString(message.getAnalog().getValue()));
                alarm.setType("1");
                console.data.setAlarm(alarm);
            }break;
            case 103: {
                this.status.setBat_level(Double.toString(message.getAnalog().getValue()));
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
    }


    public void onNetworkInformation(Terminal terminal,
                                     NetworkInformationMessage message) {
//        int cont = 0;
//        double avg = 0;
//        for (NetworkInformationMessage.NetworkInformation.SigfoxBaseStation
//                baseStation : message.networkInformation.getSigfoxBaseStationList() ){
//            cont += Math.abs(baseStation.getSignalStrength());
//        }
//
//        avg = cont / message.networkInformation.getSigfoxBaseStationList().size();
//
//        this.status.setGsm_signal(Double.toString(avg));
        System.out.println("Received NetworkInformation message: " + message.toString());

    }

    public void onLocation(Terminal terminal, LocationMessage message) {
//        if(this.tank.getData().getAlarm() != null) {
//            try {
////                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//                JAXBContext jaxbContext = JAXBContext.newInstance(RCT.LevelStatusCommF1.class);
//                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
////                jaxbMarshaller.marshal(console.levelStatusCommF1,
////                        new File("C:\\bmpsites\\dev\\data\\rctdb1\\Incoming\\" + LocalDateTime.now().format(dtf) + ".xml");
//                jaxbMarshaller.marshal(console.levelStatusCommF1,
//                        new File(LocalDateTime.now().format(dtf) + ".xml"));
//                jaxbMarshaller.marshal(console.levelStatusCommF1, System.out);
//            } catch (JAXBException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println("Received Location value from " + terminal.getId() + " with latitude " + message.getLocation().getLatitude() + " and longitude " + message.getLocation().getLongitude());
    }
}
