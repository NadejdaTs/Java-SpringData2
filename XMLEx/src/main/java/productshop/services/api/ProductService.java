package productshop.services.api;

import productshop.entities.categories.CategoryProductDTO;
import productshop.entities.categories.CategoryProductsDTO;
import productshop.entities.products.ExportProductsInRangeDTO;
import productshop.entities.products.ProductWithAttributesDTO;

import java.util.List;

public interface ProductService {
    ExportProductsInRangeDTO getInRange(float from, float to);
    CategoryProductDTO findAllOrderByNumberOfProduct();
}
