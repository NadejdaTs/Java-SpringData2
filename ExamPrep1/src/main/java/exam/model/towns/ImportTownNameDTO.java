package exam.model.towns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTownNameDTO {
    @XmlElement(name = "name")
    private TownNameDTO name;

    public ImportTownNameDTO() {}

    public TownNameDTO getName() {
        return name;
    }
}
