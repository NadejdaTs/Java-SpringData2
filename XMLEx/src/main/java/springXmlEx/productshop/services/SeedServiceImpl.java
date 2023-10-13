package springXmlEx.productshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springXmlEx.productshop.entities.categories.Category;
import springXmlEx.productshop.entities.categories.CategoriesImportDTO;
import springXmlEx.productshop.entities.products.Product;
import springXmlEx.productshop.entities.products.ProductImportDTO;
import springXmlEx.productshop.entities.products.ProductsImportDTO;
import springXmlEx.productshop.entities.users.User;
import springXmlEx.productshop.entities.users.UsersImportDTO;
import springXmlEx.repositories.CategoryRepository;
import springXmlEx.repositories.ProductRepository;
import springXmlEx.repositories.UserRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    private static final Path USERS_XML_PATH = Path.of("src", "main", "resources", "productshop", "users.xml");
    private static final Path CATEGORIES_XML_PATH = Path.of("src", "main", "resources", "productshop", "categories.xml");
    private static final Path PRODUCTS_XML_PATH = Path.of("src", "main", "resources", "productshop", "products.xml");

    //private final String USERS_XML_PATH = "src/main/resources/springXmlEx.productshop/users.xml";
    //private final String CATEGORIES_XML_PATH = "src/main/resources/springXmlEx.productshop/categories.xml";
    //private final String PRODUCTS_XML_PATH = "src/main/resources/springXmlEx.productshop/products.xml";


    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(UsersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader xmlReader = Files.newBufferedReader(USERS_XML_PATH);
        UsersImportDTO importDTOs = (UsersImportDTO) unmarshaller.unmarshal(xmlReader);

        List<User> entities = importDTOs.getUsers().stream()
                .map(dto -> this.mapper.map(dto, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(entities);
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CategoriesImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        //FileReader xmlReader = new FileReader(CATEGORIES_XML_PATH);
        FileReader xmlReader = new FileReader(CATEGORIES_XML_PATH.toAbsolutePath().toString());
        CategoriesImportDTO importDTOs = (CategoriesImportDTO) unmarshaller.unmarshal(xmlReader);

        List<Category> entities = importDTOs.getCategories().stream()
                .map(cn -> new Category(cn.getName()))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(entities);
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader xmlReader = Files.newBufferedReader(PRODUCTS_XML_PATH);
        ProductsImportDTO importDTOs = (ProductsImportDTO) unmarshaller.unmarshal(xmlReader);

        List<Product> entities = importDTOs.getProducts().stream()
                .map(dto -> new Product(dto.getName(), dto.getPrice()))
                .map(this::setRandomCategories)
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .collect(Collectors.toList());

        this.productRepository.saveAll(entities);
    }

    private Product setRandomCategories(Product product) {
        Random random = new Random();
        long categoriesDBCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesDBCount);

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomId = random.nextInt((int) categoriesDBCount) + 1;
            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            categories.add(randomCategory.get());
        }

        product.setCategories(categories);
        return product;
    }

    private Product setRandomBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0){
            return product;
        }

        Optional<User> buyer = getRandomUser();
        product.setBuyer(buyer.get());

        return product;
    }

    private Product setRandomSeller(Product product) {
        Optional<User> seller = getRandomUser();
        product.setSeller(seller.get());

        return product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count();

        //0...4 + 1 = 1...5
        int randomUserId = new Random().nextInt((int) usersCount) + 1;
        Optional<User> user = this.userRepository.findById(randomUserId);
        return user;
    }
}
