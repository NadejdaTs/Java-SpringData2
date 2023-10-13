package examPrep.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import examPrep.model.entities.customers.Customer;
import examPrep.model.entities.customers.ImportCustomerTownNameDTO;
import examPrep.model.entities.customers.ImportCustomersDTO;
import examPrep.model.entities.laptops.Laptop;
import examPrep.model.entities.laptops.LaptopImportDTO;
import examPrep.model.entities.shops.Shop;
import examPrep.model.entities.towns.Town;
import examPrep.repository.LaptopRepository;
import examPrep.repository.ShopRepository;
import examPrep.repository.TownRepository;
import examPrep.service.LaptopService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    private LaptopRepository laptopRepository;
    private ShopRepository shopRepository;

    private final Path laptopsPath = Path.of("src", "main", "resources", "files", "json", "laptops.json");

    private final Gson gson;
    private ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        this.mapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return String.join(System.lineSeparator(), Files.readAllLines(laptopsPath));
    }

    @Override
    public String importLaptops() throws IOException {
        FileReader reader = new FileReader(laptopsPath.toAbsolutePath().toString());

        LaptopImportDTO[] laptopDTOS = this.gson.fromJson(reader, LaptopImportDTO[].class);

//        TypeMap<LaptopImportDTO, Laptop> typeMap = this.mapper.createTypeMap(LaptopImportDTO.class, Laptop.class);
        for (LaptopImportDTO laptopDTO : laptopDTOS) {
            if(laptopDTO.validate()){
                Set<ConstraintViolation<LaptopImportDTO>> validationErrors = this.validator.validate(laptopDTO);
                Laptop laptop = this.mapper.map(laptopDTO, Laptop.class);;

                if(validationErrors.isEmpty()){
                    Optional<Shop> optShop = this.shopRepository.findByName(laptopDTO.getShop().getName());
                    laptop.setShop(optShop.get());
                    this.laptopRepository.save(laptop);
                }else{
                    System.out.printf("Invalid laptop with mac address %s!%n", laptopDTO.getMacAddress());
                }
            }else{
                System.out.println("Invalid laptop!");
            }
        }
        return "Successfully imported laptops!";
    }

    @Override
    public String exportBestLaptops() {
        return null;
    }
}
