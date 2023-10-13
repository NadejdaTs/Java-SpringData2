package football.service.impl;

import football.models.dto.ImportStatDTO;
import football.models.dto.ImportStatRootDTO;
import football.models.entity.Stat;
import football.repository.StatRepository;
import football.service.StatService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final Path statPathFile = Path.of("src", "main", "resources", "files", "xml", "stats.xml");
    private final ModelMapper mapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;

    @Autowired
    public StatServiceImpl(StatRepository statRepository) throws JAXBException {
        this.statRepository = statRepository;
        this.mapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        JAXBContext context = JAXBContext.newInstance(ImportStatRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }


    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(statPathFile);
    }

    @Override
    public String importStats() throws FileNotFoundException, JAXBException {
        ImportStatRootDTO statDTOs = (ImportStatRootDTO) this.unmarshaller.unmarshal(new FileReader(statPathFile.toAbsolutePath().toString()));

        return statDTOs.getStats().stream()
                .map(this::importStat)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importStat(ImportStatDTO dto){
        Set<ConstraintViolation<ImportStatDTO>> errors = this.validator.validate(dto);
        if(!errors.isEmpty()){
            return "Invalid Stat";
        }

        Optional<Stat> optStat = this.statRepository.findByShootingAndPassingAndEndurance(
                dto.getShooting(), dto.getPassing(), dto.getEndurance());

        if(optStat.isPresent()){
            return "Invalid Stat";
        }

        Stat stat = this.mapper.map(dto, Stat.class);
        this.statRepository.save(stat);
        return "Successfully imported Stat " + stat;
    }
}
