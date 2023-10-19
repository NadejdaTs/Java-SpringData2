package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportAgentDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;

    private Path agentFilePath = Path.of("src", "main", "resources", "files", "json", "agents.json");

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder()
                .create();
        this.mapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(agentFilePath);
    }

    @Override
    public String importAgents() throws IOException {
        String json = readAgentsFromFile();
        ImportAgentDTO[] importAgentDTO = this.gson.fromJson(json, ImportAgentDTO[].class);
        List<String> result = new ArrayList<>();
        for (ImportAgentDTO agentDTO : importAgentDTO) {
            Set<ConstraintViolation<ImportAgentDTO>> validatorError = this.validator.validate(agentDTO);
            if(validatorError.isEmpty()) {
                Optional<Agent> optAgent = this.agentRepository.findByFirstNameOrEmail(agentDTO.getFirstName(), agentDTO.getEmail());
                if (optAgent.isEmpty()) {
                    Agent agent = this.mapper.map(agentDTO, Agent.class);
                    Optional<Town> optTown = this.townRepository.findByName(agentDTO.getTown());
                    agent.setTown(optTown.get());
                    this.agentRepository.save(agent);
                    result.add(String.format("Successfully imported agent - %s %s", agent.getFirstName(), agent.getLastName()));
                } else {
                    result.add("Invalid agent!");
                }
            }else{
                result.add("Invalid agent!");
            }

        }
        return String.join(System.lineSeparator(), result);
    }
}
