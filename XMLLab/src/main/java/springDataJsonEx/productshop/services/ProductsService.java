package springDataJsonEx.productshop.services;

import springDataJsonEx.productshop.entities.categories.CategoryStatsDTO;
import springDataJsonEx.productshop.entities.products.ProductsWithoutBuyerDTO;

import java.util.List;

public interface ProductsService {
    List<ProductsWithoutBuyerDTO> getProductsInPriceRangeForSell(float from, float to);

    List<CategoryStatsDTO> getCategoryStatistics();
}

