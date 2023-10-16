package productshop.services.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import productshop.entities.categories.CategoryProductDTO;
import productshop.entities.categories.CategoryProductsDTO;
import productshop.entities.products.ExportProductsInRangeDTO;
import productshop.entities.products.Product;
import productshop.entities.products.ProductWithAttributesDTO;
import productshop.entities.users.User;
import productshop.repositories.CategoryRepository;
import productshop.repositories.ProductRepository;
import productshop.services.api.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final TypeMap<Product, ProductWithAttributesDTO> productToDtoMapping;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();

        Converter<User, String> userToFullNameConverter =
                context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ProductWithAttributesDTO> typeMap =
                this.mapper.createTypeMap(Product.class, ProductWithAttributesDTO.class);

        this.productToDtoMapping = typeMap.addMappings(map ->
                map.using(userToFullNameConverter)
                        .map(Product::getSeller, ProductWithAttributesDTO::setSeller));
    }

    @Override
    public ExportProductsInRangeDTO getInRange(float from, float to) {
        BigDecimal rangeFrom = BigDecimal.valueOf(from);
        BigDecimal rangeTo = BigDecimal.valueOf(to);

        List<Product> products = this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(rangeFrom, rangeTo);

        List<ProductWithAttributesDTO> dtos = products.stream()
                .map(p -> this.productToDtoMapping.map(p))
                .collect(Collectors.toList());

        return new ExportProductsInRangeDTO(dtos);
    }

    @Override
    public CategoryProductDTO findAllOrderByNumberOfProduct() {
        List<CategoryProductsDTO> dtos = this.productRepository.findAllOrderByNumberOfProduct();
        return new CategoryProductDTO(dtos);
    }
}
