package softuni.exam.models.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportApartmentDTO {
    @XmlElement(name = "apartment")
    private List<ApartmentDTO> apartments;

    public ImportApartmentDTO() {}

    public ImportApartmentDTO(List<ApartmentDTO> apartments) {
        this.apartments = new ArrayList<>();
    }

    public List<ApartmentDTO> getApartments() {
        return Collections.unmodifiableList(apartments);
    }

    public void setApartments(List<ApartmentDTO> apartments) {
        this.apartments = apartments;
    }
}
