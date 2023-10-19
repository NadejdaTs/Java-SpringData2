package softuni.exam.models.dto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDTO {
    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "agent")
    private List<AgentDTO> agents;

    @XmlElement(name = "apartment")
    private List<ApartmentIdDTO> apartments;

    private String publishedOn;

    public ImportOfferDTO() {}

    public ImportOfferDTO(BigDecimal price, List<AgentDTO> agents, List<ApartmentIdDTO> apartments, String publishedOn) {
        this.price = price;
        this.agents = agents;
        this.apartments = apartments;
        this.publishedOn = publishedOn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<AgentDTO> getAgent() {
        return Collections.unmodifiableList(agents);
    }

    public List<ApartmentIdDTO> getId() {
        return Collections.unmodifiableList(apartments);
    }

    public String getPublishedOn() {
        return publishedOn;
    }
}
