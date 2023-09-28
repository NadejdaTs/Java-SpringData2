package springdata9.services;

import springdata9.entities.Label;
import springdata9.entities.Shampoo;
import springdata9.entities.Size;
import springdata9.repositories.LabelRepository;
import springdata9.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        //Optional<Label> label = this.labelRepository.findById((long)labelId);
        //return this.shampooRepository.findBySizeOrLabelOrderByPriceAsc(label);
        //return this.shampooRepository.findBySizeOrLabelOrderByPriceAsc(size, labelId);
        return null;
    }

    @Override
    public List<Shampoo> selectMoreExpensiveThan(BigDecimal price) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceAsc(price);
    }

    @Override
    public int countPriceLowerThan(BigDecimal price) {
        return this.shampooRepository.countByPriceLessThan(price);
    }

    /*@Override
    public List<Shampoo> selectByIngredientsCount(long count) {
        return this.shampooRepository.findByIngredientCountLessThan(count);
    }*/

    @Override
    public List<Shampoo> findBySizeOrderById(Size size) {
        return this.shampooRepository.findBySizeOrderById(size);
    }

}
