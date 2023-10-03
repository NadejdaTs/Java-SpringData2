package springDataJsonEx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springDataJsonEx.productshop.entities.categories.CategoryStatsDTO;
import springDataJsonEx.productshop.entities.categories.XMLCategoryStatList;
import springDataJsonEx.productshop.entities.categories.XMLCategoryStatsDTO;
import springDataJsonEx.productshop.entities.products.ProductsWithoutBuyerDTO;
import springDataJsonEx.productshop.entities.users.UserWithSoldProductsDTO;
import springDataJsonEx.productshop.services.ProductsService;
import springDataJsonEx.productshop.services.SeedService;
import springDataJsonEx.productshop.services.UserService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductsService productsService;
    private final Gson gson;
    private UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductsService productsService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedAll();
        /*this.productsService.getProductsInPriceRangeForSell(500, 1000)
                .forEach(dto -> System.out.println(dto.getSeller()));*/
        //productsBetweenPriceWithoutBuyer();
        //getUsersWithSoldProducts();
        /*getCategoryStats();
        this.userService.getUserWithSoldProductsOrderByCount();*/

        //xmlMarshallDemo();
        //xmlDemo();

        List<CategoryStatsDTO> result = getCategoryStats();
    }

    private void xmlMarshallDemo() throws JAXBException, IOException {
        List<CategoryStatsDTO> result = getCategoryStats();

        List<XMLCategoryStatsDTO> xmlResult = result.stream()
                .map(XMLCategoryStatsDTO::new)
                .collect(Collectors.toList());

        XMLCategoryStatList xmlCategoryStatList = new XMLCategoryStatList(xmlResult);

        XMLCategoryStatsDTO first = new XMLCategoryStatsDTO(result.get(0));
        //JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsDTO.class);
        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatList.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //marshaller.marshal(first, System.out);
        //marshaller.marshal(xmlResult, System.out); //XMLCategoryStatsDTO
        marshaller.marshal(xmlCategoryStatList, System.out);

        /*File writer = new File("resources/stats.xml");
        writer.createNewFile();
        marshaller.marshal(xmlCategoryStatList, writer);*/
    }

    private void xmlDemo() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<category>\n" +
                    "<name>Hop</name>\n" +
                    "<product-count>68</product-count>\n" +
                    "<averagePrice>836.95</averagePrice>\n" +
                    "<totalRevenue>56912.80</totalRevenue>\n" +
                "</category>\n";

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        XMLCategoryStatsDTO result = (XMLCategoryStatsDTO) unmarshaller.unmarshal(inputStream);
        System.out.println(result);
    }

    private List<CategoryStatsDTO> getCategoryStats() {
        return this.productsService.getCategoryStatistics();
        //String json = this.gson.toJson(categoryStatistics);
        //System.out.println(json);

    }

    private void getUsersWithSoldProducts() {
        List<UserWithSoldProductsDTO> userWithSoldProducts = this.userService.getUserWithSoldProducts();
        String json = this.gson.toJson(userWithSoldProducts);
        System.out.println(json);
    }

    private void productsBetweenPriceWithoutBuyer() {
        List<ProductsWithoutBuyerDTO> productsForSell = this.productsService.getProductsInPriceRangeForSell(500, 1000);
        String json = this.gson.toJson(productsForSell);
        System.out.println(json);
    }
}
