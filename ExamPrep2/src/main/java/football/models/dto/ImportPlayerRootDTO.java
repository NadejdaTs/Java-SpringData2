package football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayerRootDTO {
    @XmlElement(name = "player")
    private List<ImportPlayerDTO> players;

    public ImportPlayerRootDTO() {}

    public ImportPlayerRootDTO(List<ImportPlayerDTO> players) {
        this.players = players;
    }

    public List<ImportPlayerDTO> getPlayers() {
        return players;
    }
}
