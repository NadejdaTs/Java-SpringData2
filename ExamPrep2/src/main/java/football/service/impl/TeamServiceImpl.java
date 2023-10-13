package football.service.impl;

import football.models.dto.ImportTeamDTO;
import football.models.entity.Team;
import football.models.entity.Town;
import football.repository.TeamRepository;
import football.repository.TownRepository;
import football.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final Path teamPathFile = Path.of("src", "main", "resources", "files", "json", "teams.json");

    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(teamPathFile);
    }

    @Override
    public String importTeams() throws IOException {
        String json = this.readTeamsFileContent();
        ImportTeamDTO[] importTeamDTOS = this.gson.fromJson(json, ImportTeamDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportTeamDTO importTeamDTO : importTeamDTOS) {
            Set<ConstraintViolation<ImportTeamDTO>> validationErrors = this.validator.validate(importTeamDTO);

            if(validationErrors.isEmpty()){
                Optional<Team> optTeam = this.teamRepository.findByName(importTeamDTO.getName());
                if(optTeam.isEmpty()){

                    Team team = this.mapper.map(importTeamDTO, Team.class);
                    Optional<Town> town = this.townRepository.findByName(importTeamDTO.getTownName());

                    team.setTown(town.get());
                    this.teamRepository.save(team);

                    result.add("Successfully imported Team " + team);
                }else{
                    result.add("Invalid team");
                }
            }else{
                result.add("Invalid team");
            }
        }

        return String.join(System.lineSeparator(), result);
    }
}
