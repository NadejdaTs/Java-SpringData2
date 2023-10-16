package productshop.services.api;

import productshop.entities.users.ExportSellersDTO;
import productshop.entities.users.UsersWithSoldProductsDTO;

public interface UserService {

    ExportSellersDTO findAllWithSoldProducts();

    UsersWithSoldProductsDTO findAllWithSoldProductsOrderBySoldProductAndLastName();
}
