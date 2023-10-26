package football.service.impl;

import football.models.dto.ImportPlayerDTO;
import football.models.dto.ImportPlayerRootDTO;
import football.models.entity.Player;
import football.models.entity.Stat;
import football.models.entity.Team;
import football.models.entity.Town;
import football.repository.PlayerRepository;
import football.repository.StatRepository;
import football.repository.TeamRepository;
import football.repository.TownRepository;
import football.service.PlayerService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    private TownRepository townRepository;
    private StatRepository statRepository;
    private final Path playerPathFile = Path.of("src", "main", "resources", "files", "xml", "players.xml");
    private final ModelMapper mapper;
    private TypeMap<ImportPlayerDTO, Player> dtoToPlayerMapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository, TownRepository townRepository, StatRepository statRepository) throws JAXBException {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
        this.mapper = new ModelMapper();
        Converter<String, LocalDate> toLocalDate = s ->
                s.getSource() == null
                        ? null
                        : LocalDate.parse(s.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        mapper.addConverter(toLocalDate);

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        TypeMap<ImportPlayerDTO, Player> typeMap = this.mapper.createTypeMap(ImportPlayerDTO.class, Player.class);
        this.dtoToPlayerMapper = typeMap.addMappings(map -> map.using(toLocalDate)
                .map(ImportPlayerDTO::getBirthDate, Player::setBirthDate));

        JAXBContext context = JAXBContext.newInstance(ImportPlayerRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }


    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(playerPathFile);
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {
        ImportPlayerRootDTO playerDTOs = (ImportPlayerRootDTO) this.unmarshaller.unmarshal(new FileReader(playerPathFile.toAbsolutePath().toString()));

        return playerDTOs.getPlayers().stream()
                .map(this::importPlayer)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importPlayer(ImportPlayerDTO dto){
        Set<ConstraintViolation<ImportPlayerDTO>> errors = this.validator.validate(dto);
        if(!errors.isEmpty()){
            return "Invalid Stat";
        }

        Optional<Player> optPlayer = this.playerRepository.findByEmail(dto.getEmail());

        if(optPlayer.isPresent()){
            return "Invalid Stat";
        }

        Optional<Team> optTeam = this.teamRepository.findByName(dto.getTeam().getName());
        Optional<Town> optTown = this.townRepository.findByName(dto.getTown().getName());
        Optional<Stat> optStat = this.statRepository.findById(dto.getStatId().getId());

        Player player = this.dtoToPlayerMapper.map(dto);
        player.setTeam(optTeam.get());
        player.setTown(optTown.get());
        player.setStat(optStat.get());

        this.playerRepository.save(player);
        return "Successfully imported Player " + player.getFirstName() + " " + player.getLastName() + " - " +
                player.getPosition().toString();
    }

    @Override
    public String exportBestPlayers() {
        LocalDate before = LocalDate.of(2003,1,1);
        LocalDate after = LocalDate.of(1995,1,1);

        List<Player> players = this.playerRepository
                .findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(after, before);

        return players.stream()
                .map(Player::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
