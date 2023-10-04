package springXmlEx.productshop.services;

import springXmlEx.productshop.entities.products.ExportProductsInRangeDTO;

public interface ProductsService {
    ExportProductsInRangeDTO getInRange(float from, float to);
}

