package springdata9.services;

import springdata9.entities.Shampoo;
import springdata9.entities.Size;
import springdata9.repositories.LabelRepository;
import springdata9.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService{
    @Autowired
    private ShampooRepository shampooRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Override
    public List<Shampoo> selectBySize(Size size) {
        return this.shampooRepository.findBySize(size);
    }

    @Override
    public List<Shampoo> selectBySizeOrLabelId(Size size, int labelId) {
        return this.shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, labelId);
    }

    @Override
    public List<Shampoo> selectMoreExpensiveThan(BigDecimal price) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int countPriceLowerThan(BigDecimal price) {
        return this.shampooRepository.countByPriceLessThan(price);
    }

    /*@Override
    public List<Shampoo> selectByIngredientsCount(int count) {
        return this.shampooRepository.findByIngredientCountLessThan(count);
    }*/

    @Override
    public List<Shampoo> findBySizeOrderById(Size size) {
        return this.shampooRepository.findBySizeOrderById(size);
    }

}
