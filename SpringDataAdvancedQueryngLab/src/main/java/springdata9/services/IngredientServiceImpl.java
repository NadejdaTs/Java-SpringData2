package springdata9.services;

import springdata9.entities.Ingredient;
import springdata9.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Override
    public List<Ingredient> selectNameStartWith(String startWith) {
        return this.ingredientRepository.findByNameStartingWith(startWith);
    }

    @Override
    public List<Ingredient> selectInNames(List<String> listOfNames) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(listOfNames);
    }

    @Override
    public int deleteByName(String name) {
        return this.ingredientRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void increasePriceByPercentage(double percent) {
        BigDecimal actualPercent = BigDecimal.valueOf(percent);
        this.ingredientRepository.increasePriceByPercent(actualPercent);
    }
}
