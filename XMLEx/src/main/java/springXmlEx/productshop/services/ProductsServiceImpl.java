package springXmlEx.productshop.services;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springXmlEx.productshop.entities.products.Product;
import springXmlEx.productshop.entities.products.ExportProductsInRangeDTO;
import springXmlEx.productshop.entities.products.ProductWithAttributesDTO;
import springXmlEx.productshop.entities.users.User;
import springXmlEx.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService{
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final TypeMap<Product, ProductWithAttributesDTO> productToDtoMapping;

    @Autowired
    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();

        Converter<User, String> userToFullNameConverter =
                context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ProductWithAttributesDTO> typeMap =
                this.mapper.createTypeMap(Product.class, ProductWithAttributesDTO.class);

        this.productToDtoMapping = typeMap.addMappings(map ->
                map.using(userToFullNameConverter)
                   .map(Product::getSeller, ProductWithAttributesDTO::setSeller));

        this.mapper.addConverter(userToFullNameConverter);
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
}
