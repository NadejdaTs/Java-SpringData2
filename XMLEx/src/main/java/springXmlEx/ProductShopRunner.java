package springXmlEx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springXmlEx.productshop.entities.products.ExportProductsInRangeDTO;
import springXmlEx.productshop.entities.users.ExportSellersDTO;
import springXmlEx.productshop.entities.users.ExportUserWithSoldProductsDTO;
import springXmlEx.productshop.services.ProductsService;
import springXmlEx.productshop.services.SeedService;
import springXmlEx.productshop.services.UserService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductsService productsService;
    private UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        1. Products
//        Qiery 1
//        this.productsInRange();
        this.findUserWithSoldProducts();
    }

    private void findUserWithSoldProducts() throws JAXBException {
        ExportSellersDTO result = this.userService.findAllWithSoldProducts();

        JAXBContext context = JAXBContext.newInstance(ExportSellersDTO.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(result, System.out);
    }

    private void productsInRange() throws JAXBException {
        ExportProductsInRangeDTO inRange = this.productsService.getInRange(500, 1000);

        JAXBContext context = JAXBContext.newInstance(ExportProductsInRangeDTO.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(inRange, System.out);
    }

}
