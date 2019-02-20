package RCT;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "units")
public class Units {
    private List<Unit> units;

    public Units() {
    }

    public Units(List<Unit> units) {
        this.units = units;
    }

    public List<Unit> getUnits() {
        return units;
    }

    @XmlElement(name = "unit")
    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public void add(Unit unit){
        if (this.units == null){
            this.units = new ArrayList<Unit>();
            this.units.add(unit);
        }
    }
}
