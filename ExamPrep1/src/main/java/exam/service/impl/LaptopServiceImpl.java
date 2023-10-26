package exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.model.laptops.Laptop;
import exam.model.laptops.LaptopImportDTO;
import exam.model.shops.Shop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        List<Laptop> laptops = this.laptopRepository.findAllOrderCpuSpeedDescRamDescStorageDescMacAddress();

        StringBuilder sb = new StringBuilder();
        for (Laptop laptop : laptops) {
            sb.append(laptop.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
