package carDealer.entities.parts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsImportDTO {
    @XmlElement(name = "part")
    private List<PartImportDTO> parts;

    public List<PartImportDTO> getParts() {
        return Collections.unmodifiableList(parts);
    }
}
