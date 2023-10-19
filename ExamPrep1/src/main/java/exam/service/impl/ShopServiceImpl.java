package exam.service.impl;

import exam.model.shops.ImportShopDTO;
import exam.model.shops.ImportShopsDTO;
import exam.model.shops.Shop;
import exam.model.towns.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
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
    private final TownRepository townRepository;
    private final ModelMapper mapper;

    private final Path shopsPath = Path.of("src", "main", "resources", "files", "xml", "shops.xml");

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
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
                    Optional<Town> optTown = this.townRepository.findByName(sh.getTown().getName());
                    shop.setTown(optTown.get());
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
