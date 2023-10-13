package football.models.dto;

import football.models.entity.PlayerPosition;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayerDTO {
    @XmlElement(name = "first-name")
    @Size(min = 2)
    private String firstName;
    @XmlElement(name = "last-name")
    @Size(min = 2)
    private String lastName;
    @XmlElement
    @Email
    private String email;
    @XmlElement(name = "birth-date")
    private String birthDate;
    @XmlElement
    private PlayerPosition position;

    @XmlElement(name = "town")
    private NameDTO town;
    @XmlElement(name = "team")
    private NameDTO team;

    @XmlElement(name = "stat")
    private StatIdDTO statId;

    public ImportPlayerDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public NameDTO getTown() {
        return town;
    }

    public NameDTO getTeam() {
        return team;
    }

    public StatIdDTO getStatId() {
        return statId;
    }
}
