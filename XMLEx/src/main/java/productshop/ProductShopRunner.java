package productshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productshop.entities.categories.CategoryNameDTO;
import productshop.entities.categories.CategoryProductDTO;
import productshop.entities.categories.CategoryProductsDTO;
import productshop.entities.products.ExportProductsInRangeDTO;
import productshop.entities.users.ExportSellersDTO;
import productshop.entities.users.UsersWithSoldProductsDTO;
import productshop.services.api.CategoryService;
import productshop.services.api.ProductService;
import productshop.services.api.SeedService;
import productshop.services.api.UserService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productsService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productsService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        System.out.println("Successfully seed data in DB!");
//        Query 1
//        productsInRange();
//        Query 2
//        findUserWithSoldProducts();
//        Query 3
//        getCategoryProducts();
//        Query 4
//        getUsersWithSoldProductsExtended();
    }

    private void getUsersWithSoldProductsExtended() throws JAXBException {
        UsersWithSoldProductsDTO users = this.userService.findAllWithSoldProductsOrderBySoldProductAndLastName();

        JAXBContext context = JAXBContext.newInstance(UsersWithSoldProductsDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(users, System.out);
    }

    private void getCategoryProducts() throws JAXBException {
        CategoryProductDTO allOrderByNumberOfProduct = this.productsService.findAllOrderByNumberOfProduct();

        JAXBContext context = JAXBContext.newInstance(CategoryProductDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(allOrderByNumberOfProduct, System.out);
    }

    private void productsInRange() throws JAXBException {
        ExportProductsInRangeDTO inRange = this.productsService.getInRange(500, 1000);

        JAXBContext context = JAXBContext.newInstance(ExportProductsInRangeDTO.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(inRange, System.out);
    }

    private void findUserWithSoldProducts() throws JAXBException {
        ExportSellersDTO result = this.userService.findAllWithSoldProducts();

        JAXBContext context = JAXBContext.newInstance(ExportSellersDTO.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(result, System.out);
    }
}
