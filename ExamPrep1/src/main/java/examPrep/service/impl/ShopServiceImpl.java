package examPrep.service.impl;

import examPrep.model.entities.shops.ImportShopDTO;
import examPrep.model.entities.shops.ImportShopsDTO;
import examPrep.model.entities.shops.Shop;
import examPrep.repository.ShopRepository;
import examPrep.service.ShopService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final ModelMapper mapper;

    private final Path shopsPath = Path.of("src", "main", "resources", "files", "xml", "shops.xml");

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return String.join(System.lineSeparator(), Files.readAllLines(shopsPath));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        List<String> result = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(ImportShopsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportShopsDTO shops = (ImportShopsDTO) unmarshaller.unmarshal(new FileReader(shopsPath.toAbsolutePath().toString()));

        for (ImportShopDTO sh : shops.getShops()) {
            if(sh.isValid()){
                Optional<Shop> optShop = this.shopRepository.findByName(sh.getName());
                if(optShop.isPresent()){
                    result.add("Shop already exist");
                }else{
                    Shop shop = this.mapper.map(sh, Shop.class);
                    this.shopRepository.save(shop);
                    result.add("Saved shop " + shop.getName());
                }
            }else{
                result.add("Invalid shop");
            }
        }
        return String.join(System.lineSeparator(), result);
    }
}
