package springDataJsonEx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springDataJsonEx.productshop.entities.categories.CategoryStatsDTO;
import springDataJsonEx.productshop.entities.products.ProductsWithoutBuyerDTO;
import springDataJsonEx.productshop.entities.users.User;
import springDataJsonEx.productshop.entities.users.UserWithSoldProductsDTO;
import springDataJsonEx.productshop.entities.users.UsersWithSalesListDto;
import springDataJsonEx.productshop.services.ProductsService;
import springDataJsonEx.productshop.services.SeedService;
import springDataJsonEx.productshop.services.UserService;

import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductsService productsService;
    private final Gson gson;
    private UserService userService;
//    private ModelMapper mapper;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
//        this.mapper = new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        03
//       Query 1
//        productsBetweenPriceWithoutBuyer();
//       Query 2
//        getUsersWithSoldProducts();
//       Query 3
//        getCategoryStats();
//       Query 4
        UsersWithSalesListDto usersAndSoldProducts = this.userService.getUsersAndSoldProducts();
        String json = this.gson.toJson(usersAndSoldProducts);
        System.out.println(json);
    }

    private void getCategoryStats() {
        List<CategoryStatsDTO> categoryStatistics = this.productsService.getCategoryStatistics();
        String json = this.gson.toJson(categoryStatistics);
        System.out.println(json);
    }

    private void getUsersWithSoldProducts() {
//        List<User> userWithSoldProductsOrderByCount = this.userService.getUserWithSoldProductsOrderByCount();
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
