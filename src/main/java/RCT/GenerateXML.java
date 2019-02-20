package RCT;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class GenerateXML {
    private File file;

    public GenerateXML(File file) {
        this.file = file;
    }

    public void createNewDocument(LokaTelemetry lokaTelemetry){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Telemetry.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(lokaTelemetry, file);
        }catch(JAXBException e){
            e.printStackTrace();
        }



    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
