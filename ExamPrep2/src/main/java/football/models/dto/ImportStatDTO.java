package football.models.dto;

import jakarta.validation.constraints.Positive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportStatDTO {
    @XmlElement
    @Positive
    private float passing;
    @XmlElement
    @Positive
    private float shooting;
    @XmlElement
    @Positive
    private float endurance;

    public ImportStatDTO() {}

    public float getPassing() {
        return passing;
    }

    public float getShooting() {
        return shooting;
    }

    public float getEndurance() {
        return endurance;
    }
}
