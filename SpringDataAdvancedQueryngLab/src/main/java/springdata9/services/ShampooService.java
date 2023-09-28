package springdata9.services;

import springdata9.entities.Shampoo;
import springdata9.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> selectBySize(Size size);

    //List<Shampoo> selectBySizeOrLabelId(Size size, int labelId);

    List<Shampoo> selectMoreExpensiveThan(BigDecimal bigDecimal);

    int countPriceLowerThan(BigDecimal price);

    //List<Shampoo> selectByIngredientsCount(long count);

    List<Shampoo> selectBySizeOrLabelId(Size size, int labelId);
    List<Shampoo> findBySizeOrderById(Size size);
}
