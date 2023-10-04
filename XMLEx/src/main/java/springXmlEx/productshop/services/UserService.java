package springXmlEx.productshop.services;

import springXmlEx.productshop.entities.users.ExportSellersDTO;

public interface UserService {

    ExportSellersDTO findAllWithSoldProducts();

}
